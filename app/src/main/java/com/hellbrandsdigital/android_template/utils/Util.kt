package com.hellbrandsdigital.android_template.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/**
10.04.22
This class provides some utility functions, that are globally useful
 */
object Util {

    /**
     * Provides a Coroutine scope with the IO-Dispatcher
     */
    val ioScope = CoroutineScope(Dispatchers.IO)
}
