package com.example.data.network.api

import com.example.data.network.model.BaseResponseDto
import com.example.domain.model.BaseResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface HHApiService {

    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r")
    suspend fun getHHData(): Response<BaseResponseDto>
}