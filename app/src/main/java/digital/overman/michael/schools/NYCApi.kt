package digital.overman.michael.schools

import retrofit2.http.GET
import retrofit2.http.Query

interface NYCApi {
    @GET("s3k6-pzi2.json/?\$\$app_token=Q7QFJVIcWpyIAZHzCNcQHU8HW")
    fun fetchSchools(): retrofit2.Call<List<School>>

    @GET("f9bf-2cp4.json/?\$\$app_token=Q7QFJVIcWpyIAZHzCNcQHU8HW")
    fun fetchSATs(@Query("dbn") id: String): retrofit2.Call<List<Score>>
}

// https://data.cityofnewyork.us/resource/s3k6-pzi2.json - schools, general
// https://data.cityofnewyork.us/resource/f9bf-2cp4.json - SAT results

