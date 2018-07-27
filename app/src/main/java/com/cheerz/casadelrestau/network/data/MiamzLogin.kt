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
        val first_image_url: String?,
        val second_image_url: String?,
        val third_image_url: String?,
        val fourth_image_url: String?,
        val fifth_image_url: String?,
        val place_category_tag: String,
        val user_email: String?,
        val tags: List<String>,
        val events: MutableList<MiamzEvent>
)

data class MiamzReqPlace(val place: MiamzReqPlaceData)

data class MiamzPlace(val lat: Double, val lng: Double, val meter_distance: Int)

data class MiamzEvent(val id: Int, val start_at: String, val stop_at: String, var participants: List<String>)

data class MiamzReqEventWrapper(val event: MiamzReqEvent)
data class MiamzReqEvent(val place_id: Int, val start_at: String, val stop_at: String)
