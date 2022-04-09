package com.hellbrandsdigital.vacationchecklist

import android.widget.TextView
import androidx.test.core.app.ActivityScenario.launch
import com.hellbrandsdigital.vacationchecklist.databinding.ActivityMainBinding
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
    fun `onCreate set ViewBindings correctly`() {
        val textToShow = "Hello World"
        launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity {
                val bindingField = MainActivity::class.java.getDeclaredField("binding")
                bindingField.isAccessible = true
                val binding = bindingField.get(it) as ActivityMainBinding
                assertEquals(binding.HelloWorldText.text, textToShow)
            }
        }
    }
}