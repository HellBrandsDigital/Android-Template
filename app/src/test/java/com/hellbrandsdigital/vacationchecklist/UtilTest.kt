package com.hellbrandsdigital.vacationchecklist

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * 10.04.22
 *
 */
internal class UtilTest {

    private val util = Util()

    @Test
    fun `multiply should return 8F`() {
        assertEquals(util.multiply(2F,4F), 8F)
    }

    @Test
    fun `multiply should return 0F`() {
        assertEquals(util.multiply(2F,0F), 0F)
    }
}