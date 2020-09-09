package digital.overman.michael.schools

import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("dbn") val id: String = "",
    @SerializedName("sat_critical_reading_avg_score") var satRead: String = "",
    @SerializedName("sat_math_avg_score") var satMath: String = "",
    @SerializedName("sat_writing_avg_score") var satWrite: String = ""
)
