package com.cheerz.casadelrestau.network

import com.cheerz.casadelrestau.user.UserStorage
import okhttp3.Interceptor
import okhttp3.Response

class HttpHeadersInterceptor : Interceptor {
    private val sessionHeaderName = "SESSION-TOKEN"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            UserStorage.retrieveToken()?.let { header(sessionHeaderName, it) }
        }.build()
        val response = chain.proceed(request)
        response.header(sessionHeaderName)?.let { UserStorage.storeToken(it) }
        return response
    }
}
