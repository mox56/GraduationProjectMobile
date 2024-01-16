package com.android.graduationproject.data

import android.media.session.MediaSession.Token

data class LoginResponse(val user: User, val token: Token)
