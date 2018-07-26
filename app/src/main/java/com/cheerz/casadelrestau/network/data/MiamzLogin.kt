package com.cheerz.casadelrestau.network.data

data class MiamzLogin(val email: String, val nickname: String)
data class MiamzReqLogin(val session: MiamzReqLoginSession)
data class MiamzReqLoginSession(val email: String, val password: String)

data class MiamzSignUp(val email: String, val nickname: String)
data class MiamzReqSignUp(val user: MiamzReqSignUpUser)
data class MiamzReqSignUpUser(val email: String, val password: String)

data class MiamzReqPlaceData(
        val name: String,
        val lng: Float,
        val lat: Float,
        val first_image_url: String,
        val second_image_url: String,
        val third_image_url: String,
        val fourth_image_url: String,
        val fifth_image_url: String,
        val place_category_tag: String,
        val user_emai: String
)

data class MiamzReqPlace(val place: MiamzReqPlaceData)

data class MiamzPlace(val lat: Float, val lng: Float, val meter_distance: Int)
