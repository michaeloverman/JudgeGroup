package digital.overman.michael.schools

import com.google.gson.annotations.SerializedName

data class School(
    @SerializedName("dbn") val id: String = "",
    @SerializedName("school_name") var name: String = "",
    @SerializedName("phone_number") var phone: String = "",
    @SerializedName("location") var address: String = "",
    @SerializedName("overview_paragraph") var description: String = "",
    var neighborhood: String = "",
    @SerializedName("fax_number") var fax: String = "",
    @SerializedName("school_email") var email: String = "",
    var website: String = "",
    @SerializedName("total_students") var numStudents: String = "",
    var graduation_rate: Double = 0.0
) {
    fun address(): String {
        val parts = address.split("(")
        return parts[0]
    }
    fun graduationRate() = "${(graduation_rate * 100).toInt()}%"
}
