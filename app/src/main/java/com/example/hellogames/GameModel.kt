package com.example.hellogames;

import com.google.gson.annotations.SerializedName;

data class GameModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val urlPicture: String
) {
    constructor() : this(0, "", "")
}
