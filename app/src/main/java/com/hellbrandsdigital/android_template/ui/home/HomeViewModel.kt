package com.hellbrandsdigital.android_template.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hellbrandsdigital.android_template.HIDDEN_VALUE_KEY
import com.hellbrandsdigital.android_template.MAIN_PART_TEXT_KEY
import com.hellbrandsdigital.android_template.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(): ViewModel() {

    var hiddenValue: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        value = SettingsManager.getFlagInt(HIDDEN_VALUE_KEY)
    }
    var mainText: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = SettingsManager.getFlagString(MAIN_PART_TEXT_KEY)
    }

    fun incrementHiddenValue() {
        hiddenValue.value = hiddenValue.value?.inc()
        hiddenValue.value?.let {
            SettingsManager.setFlagInt(HIDDEN_VALUE_KEY, it)
        }
    }
}
