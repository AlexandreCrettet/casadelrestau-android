package com.cheerz.casadelrestau.network.data

data class MiamzLogin(val email: String, val nickname: String)
data class MiamzReqLogin(val session: MiamzReqLoginSession)
data class MiamzReqLoginSession(val email: String, val password: String)

data class MiamzSignUp(val email: String, val nickname: String)
data class MiamzReqSignUp(val user: MiamzReqSignUpUser)
data class MiamzReqSignUpUser(val email: String, val password: String)

data class MiamzReqPlaceData(
        val id: Int,
        val name: String,
        val address: String,
        val lng: Double,
        val lat: Double,
        val first_image_url: String,
        val second_image_url: String,
        val third_image_url: String,
        val fourth_image_url: String,
        val fifth_image_url: String,
        val place_category_tag: String,
        val user_email: String,
        val reservation_hours: String,
        val user_registered: List<String>

)

data class MiamzReqPlace(val place: MiamzReqPlaceData)

data class MiamzPlace(val lat: Double, val lng: Double, val meter_distance: Int)
