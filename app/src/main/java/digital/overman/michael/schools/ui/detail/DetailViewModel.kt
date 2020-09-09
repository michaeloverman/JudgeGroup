package digital.overman.michael.schools.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import digital.overman.michael.schools.NYCApi
import digital.overman.michael.schools.School
import digital.overman.michael.schools.Score
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "DetailViewModel"
class DetailViewModel(val school: School) : ViewModel() {

    interface Callbacks {
        fun scoreReturn()
    }
    var callback: Callbacks? = null

    private val nycApi: NYCApi
    var scores: Score? = null

    init {
//        Log.i(TAG, "DetailViewModel init")
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us/resource/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        nycApi = retrofit.create(NYCApi::class.java)

        fetchScores()
    }

    private fun fetchScores() {
//        Log.d(TAG, "fetching SAT scores")
        nycApi.fetchSATs(school.id).enqueue(object : Callback<List<Score>> {
            override fun onResponse(call: Call<List<Score>>, response: Response<List<Score>>) {
//                Log.d(TAG, "************ score data received")
                var responseScore: List<Score>? = response.body()
                if (responseScore != null && responseScore.isNotEmpty()) {
                    scores = responseScore[0]
                    callback?.scoreReturn()
                }
            }

            override fun onFailure(call: Call<List<Score>>, t: Throwable) {
//                Log.d(TAG, "fetch of SAT scores failed")
            }
        })
    }
}
