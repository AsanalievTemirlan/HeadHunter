package com.example.headhunter.uiModel


import com.example.domain.model.Offer
import com.google.gson.annotations.SerializedName

data class OfferUi(
    @SerializedName("button")
    val button: ButtonUi? = null,
    @SerializedName("id")
    val id: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("title")
    val title: String
)

fun OfferUi.toDomain(): Offer = Offer(button?.toDomain(), id, link, title)

fun Offer.toUi(): OfferUi = OfferUi(buttonModel?.toUi(), id?: "", link?: "", title?: "")