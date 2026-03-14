package com.recolor.plugin.com

import android.util.Log

class Recolor {

    fun echo(value: String?): String? {
        Log.i("Echo", value ?: "null")

        return value
    }
}
