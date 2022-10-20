package com.hellbrandsdigital.android_template.ui.notifications

import android.os.Bundle
import android.text.InputType
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.hellbrandsdigital.android_template.HIDDEN_VALUE_KEY
import com.hellbrandsdigital.android_template.SettingsManager
import com.hellbrandsdigital.androidtemplate.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_preferences, rootKey)

        val hiddenValuePreference: EditTextPreference? = findPreference(HIDDEN_VALUE_KEY)

        hiddenValuePreference?.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }
    }

    override fun onResume() {
        super.onResume()
        checkVisibility()
    }

    private fun checkVisibility() {
        val hiddenValuePreference: EditTextPreference? = findPreference(HIDDEN_VALUE_KEY)
        val hiddenValue = SettingsManager.getFlagInt(HIDDEN_VALUE_KEY)

        if (hiddenValue != null) {
            hiddenValuePreference?.isVisible = hiddenValue >= 5
        }
    }
}
