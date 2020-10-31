package com.max.moviedbtask.person.data.rest.interfaces

import com.max.moviedbtask.core.network.DataListResponse
import com.max.moviedbtask.person.data.rest.response.PersonDetailsResponse
import com.max.moviedbtask.person.data.rest.response.PersonImageResponse
import com.max.moviedbtask.person.data.rest.response.PersonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IPersonApiClient {
    @GET("person/popular")
    suspend fun getPopularPersons(
        @Query("page") page: Int
    ): DataListResponse<PersonResponse>

    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int
    ): PersonDetailsResponse

    @GET("person/{person_id}/images")
    suspend fun getPersonImages(
        @Path("person_id") personId: Int
    ): DataListResponse<PersonImageResponse>
}