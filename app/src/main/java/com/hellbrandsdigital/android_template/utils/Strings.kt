package com.hellbrandsdigital.android_template.utils

import androidx.annotation.StringRes
import com.hellbrandsdigital.android_template.MainApplication

/**
28.09.22
This class provides a utility function to receive a string resource from all places in the app
 */
object Strings {

    /**
     * @param stringRes: String ID to receive
     * @return String from ID
     */
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return MainApplication.instance.getString(stringRes, *formatArgs)
    }
}
