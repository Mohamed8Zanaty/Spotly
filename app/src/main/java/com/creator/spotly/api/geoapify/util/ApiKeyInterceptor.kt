package com.creator.spotly.api.geoapify.util

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(
    private val apiKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl: HttpUrl = original.url
        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()
        val newRequest = original.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}