package com.captaindeer.yasnikas.ui.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

object ValidEmail {
    fun isEmailValid(email:String): Boolean{
        return TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}