package com.max.moviedbtask.core.network

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
data class ErrorResponse(
    val status_message: String? = null,
    val status_code: Int? = null,
    val errors: MutableList<String>? = null
)