package com.android.graduationproject.utils

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.animation.AnimationUtils
import com.android.graduationproject.R

class VibrateView {
    companion object {
        fun vibrate(context: Context, view: View) {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    350, VibrationEffect.DEFAULT_AMPLITUDE
                )
            )


            val animation = AnimationUtils.loadAnimation(context, R.anim.vibrate)
            view.startAnimation(animation)

        }

    }
}