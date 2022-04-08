package com.hellbrandsdigital.vacationchecklist

import android.widget.TextView
import androidx.test.core.app.ActivityScenario.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


/**
 * 08.04.22
 *
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30])
class MainActivityTest {

    @Test
    fun `Test onCreate`() {
        val textToShow = "Hello World"

        launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity {
                val shownText = it.findViewById<TextView>(R.id.HelloWorldText).text
               assertEquals(textToShow, shownText)
           }
        }
    }
}