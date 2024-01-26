package com.android.graduationproject.data

import android.media.session.MediaSession.Token
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    var token: String,
    @SerializedName("user")
    var `user`: User,

) {
    data class User(
        @SerializedName("username")
        var username: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("password")
        var password: String,

    )
}
