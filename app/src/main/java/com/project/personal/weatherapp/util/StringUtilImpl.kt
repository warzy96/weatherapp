package com.project.personal.weatherapp.util

import android.content.Context
import javax.inject.Inject

class StringUtilImpl
@Inject
constructor(val context: Context) : StringUtil {

    override fun getStringResource(stringId: Int): String {
        return context.resources.getString(stringId)
    }

}