package com.cheerz.casadelrestau.network.data

data class MiamzLogin(val email: String, val nickname: String)
data class MiamzReqLogin(val session: MiamzReqLoginSession)
data class MiamzReqLoginSession(val email: String, val password: String)
data class MiamzSignUp(val email: String, val nickname: String)
data class MiamzReqSignUp(val user: MiamzReqSignUpUser)
data class MiamzReqSignUpUser(val email: String, val password: String)
