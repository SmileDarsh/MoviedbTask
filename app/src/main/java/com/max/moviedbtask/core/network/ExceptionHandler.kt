package com.max.moviedbtask.core.network

import com.google.gson.Gson
import java.lang.Exception
import retrofit2.HttpException

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
fun Exception.errorResponse(): ErrorResponse? {
    if (this is HttpException) {
        if (response() != null) {
            val response = response()!!
            response.errorBody()?.let {
                return Gson().fromJson(
                    it.charStream(), ErrorResponse::class.java
                )
            }
        }
    }
    return null
}

fun Exception.errorInternet(): Boolean {
    message?.let {
        return it.contains("No address associated with hostname")
                || it.contains("connect timed out")
                || it.contains("failed to connect to")
                || it.contains("timeout")
                || it.contains("ECONNRESET")
    }
    return false
}