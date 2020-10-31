package com.max.moviedbtask.person.data.rest

import com.max.moviedbtask.core.network.BaseApiClient
import com.max.moviedbtask.person.data.rest.interfaces.IPersonApiClient

object PersonApiClient : BaseApiClient<IPersonApiClient>(IPersonApiClient::class.java)