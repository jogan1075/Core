package com.jmc.core.utilities.base.errors

import android.content.res.Resources

fun interface Translatable {
    fun getStringResource(resources: Resources) : String
}