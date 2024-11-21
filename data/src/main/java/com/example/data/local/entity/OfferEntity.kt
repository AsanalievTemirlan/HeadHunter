package com.example.data.local.entity


import androidx.room.Entity
import com.example.data.network.model.toData
import com.example.domain.model.Offer
import com.google.gson.annotations.SerializedName
@Entity("offer")
data class OfferEntity(
    @SerializedName("button")
    val buttonEntity: ButtonEntity,
    @SerializedName("id")
    val id: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("title")
    val title: String
)

fun OfferEntity.toDomain(): Offer = Offer(buttonModel = buttonEntity.toDomain(), id = id, link = link, title = title)

fun Offer.toEntity(): OfferEntity {
    return OfferEntity(
        buttonEntity = buttonModel?.toEntity() ?: ButtonEntity(""),
        id = id ?: "",
        link = link ?: "",
        title = title ?: ""
    )
}
