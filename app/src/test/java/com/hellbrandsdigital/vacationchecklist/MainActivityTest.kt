package com.hellbrandsdigital.vacationchecklist

import android.view.View
import androidx.test.core.app.ActivityScenario.launch
import com.hellbrandsdigital.vacationchecklist.databinding.ActivityMainBinding
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
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
        val textToShow = "10.0"
        launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity {
                val bindingField = MainActivity::class.java.getDeclaredField("binding")
                bindingField.isAccessible = true
                val binding = bindingField.get(it) as ActivityMainBinding
                assertEquals(binding.HelloWorldText.text, textToShow)
            }
        }
    }

    @Test
    fun `onCreate setContentView is correct`() {
        launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity {
                assertEquals(R.id.ActivityMainLayout,
                    it.findViewById<View?>(android.R.id.content)
                        .findViewById<View?>(R.id.ActivityMainLayout).id
                )
            }
        }
    }
}