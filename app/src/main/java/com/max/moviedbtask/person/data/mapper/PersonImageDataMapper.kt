package com.max.moviedbtask.person.data.mapper

import com.max.moviedbtask.core.mapper.Mapper
import com.max.moviedbtask.person.data.rest.response.PersonImageResponse

class PersonImageDataMapper : Mapper<PersonImageResponse, String> {
    override fun map(origin: PersonImageResponse) = origin.file_path
}