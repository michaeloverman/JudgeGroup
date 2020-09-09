package digital.overman.michael.schools.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import digital.overman.michael.schools.NYCApi
import digital.overman.michael.schools.School
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainViewModel"
class MainViewModel : ViewModel() {

    interface Callbacks {
        fun schoolListReturn()
    }
    var callback: Callbacks? = null

    var schools: List<School> = emptyList()
    private val nycApi: NYCApi

    init {
//        Log.d(TAG, "view madel init...")
        // get schools
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us/resource/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        nycApi = retrofit.create(NYCApi::class.java)

        fetchSchools()
    }

    private fun fetchSchools() {
//        Log.d(TAG, "fetching schools")
        var responseList: List<School>?
        nycApi.fetchSchools().enqueue(object : Callback<List<School>> {
            override fun onResponse(
                call: Call<List<School>>,
                response: Response<List<School>>
            ) {
//                Log.d(TAG, "**********School data received")
                responseList = response.body()
                if (responseList != null) schools = responseList!!
                callback?.schoolListReturn()
            }

            override fun onFailure(call: Call<List<School>>, t: Throwable) {
//                Log.d(TAG, "!!!!!!!! School data fetch failed")
            }
        })
    }

    // Called from Options Menu
    fun sortAlpha() {
        schools = schools.sortedBy { it.name }
    }

    // Called from Options Menu
    fun sortId() {
        schools = schools.sortedBy { it.id }
    }

}
