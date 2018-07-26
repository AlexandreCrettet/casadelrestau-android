package com.cheerz.casadelrestau.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {

    val service: CasaDelRestauService by lazy { buildRadioKingService() }

    private fun buildRadioKingService(): CasaDelRestauService {
        val client = buildRadioKingClient()
        return Retrofit.Builder()
                .baseUrl(CasaDelRestauService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(CasaDelRestauService::class.java)
    }

    private fun buildRadioKingClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(HttpHeadersInterceptor())
                .build()
    }
}
