package com.example.data.model


import com.example.domain.model.Offer
import com.google.gson.annotations.SerializedName

data class OfferDto(
    @SerializedName("button")
    val buttonDto: ButtonDto,
    @SerializedName("id")
    val id: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("title")
    val title: String
)

fun OfferDto.toDomain(): Offer = Offer(buttonModel = buttonDto.toDomain(), id = id, link = link, title = title)

fun Offer.toData(): OfferDto = OfferDto(buttonDto = buttonModel.toData(), id = id, link = link, title = title)