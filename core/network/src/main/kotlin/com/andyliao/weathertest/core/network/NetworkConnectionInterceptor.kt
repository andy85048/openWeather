package com.andyliao.weathertest.core.network

import com.andyliao.weathertest.core.common.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(
    private val networkUtils: NetworkUtils
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkUtils.isNetworkAvailable()) {
            throw IOException("No internet connection")
        }
        return chain.proceed(chain.request())
    }
}