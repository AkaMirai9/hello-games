package com.example.hellogames

import com.google.gson.annotations.SerializedName

data class GameDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("players") val players: Int,
    @SerializedName("year") val year: Int,
    @SerializedName("url") val url: String,
    @SerializedName("picture") val pictureUrl: String,
    @SerializedName("description_fr") val descFr: String,
    @SerializedName("description_en") val descEn: String,
) {

}