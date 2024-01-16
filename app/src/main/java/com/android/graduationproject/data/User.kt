package com.android.graduationproject.data

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("_id") val id: String, val userName: String)
