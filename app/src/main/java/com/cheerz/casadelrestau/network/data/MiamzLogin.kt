package com.cheerz.casadelrestau.network.data

data class MiamzLogin(val email: String, val nickname: String)
data class MiamzReqLogin(val email: String, val password: String)
data class MiamzSignUp(val email: String, val nickname: String)
data class MiamzReqSignUp(val email: String, val password: String)
