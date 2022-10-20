package com.hellbrandsdigital.android_template

import androidx.test.core.app.ActivityScenario.launch
import com.hellbrandsdigital.androidtemplate.databinding.ActivityMainBinding
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30])
class MainActivityTest {

    @Test
    fun `onCreate set ViewBindings correctly`() {
        launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity {
                val bindingField = MainActivity::class.java.getDeclaredField("binding")
                bindingField.isAccessible = true
                val binding = bindingField.get(it) as ActivityMainBinding
                assert(binding.navView.isShown)
            }
        }
    }
}
