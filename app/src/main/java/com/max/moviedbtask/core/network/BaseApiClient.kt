package com.max.moviedbtask.core.network

import com.max.moviedbtask.BuildConfig
import com.max.moviedbtask.core.utils.SharedPreferencesManager.getToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseApiClient<T>(private val classT: Class<T>) {

    companion object {
        const val CONNECTION_TIMEOUT: Long = 180L
        const val READ_TIMEOUT: Long = 180L
        const val WRITE_TIMEOUT: Long = 180L
    }

    open fun getApiClient(): T {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor {
            val original = it.request()
            val request = original.newBuilder()
                .addHeader("Authorization", "Bearer " + getToken())
                .addHeader("Accept", "application/json")
                .method(original.method, original.body)
                .build()
            it.proceed(request)
        }

        val client = httpClient
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        val retrofitBuilder = Retrofit.Builder().apply {
            baseUrl(BuildConfig.URL_SERVER)
            client(client)
            addConverterFactory(GsonConverterFactory.create())
        }

        val retrofit = retrofitBuilder.build()
        return retrofit.create(classT)
    }
}