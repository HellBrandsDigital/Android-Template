package com.hellbrandsdigital.android_template

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hellbrandsdigital.android_template.model.entity.Item
import com.hellbrandsdigital.android_template.model.repository.ItemListRepository
import com.hellbrandsdigital.android_template.model.repository.ItemRepository
import com.hellbrandsdigital.android_template.utils.Util
import com.hellbrandsdigital.androidtemplate.R
import com.hellbrandsdigital.androidtemplate.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var itemRepo: ItemRepository
    @Inject lateinit var itemListRepo: ItemListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnItemReselectedListener { item ->
            // Pop everything up to the reselected item
            val reselectedDestinationId = item.itemId
            navController.popBackStack(reselectedDestinationId, inclusive = false)
        }

        initializeSettings()
        initializeDB()
    }

    private fun initializeSettings() {
        val mainText = SettingsManager.getFlagString(MAIN_PART_TEXT_KEY)
        if (mainText.isNullOrBlank()) {
            SettingsManager.setFlagString(MAIN_PART_TEXT_KEY, "Hello World")
        }

        val hiddenValue = SettingsManager.getFlagInt(HIDDEN_VALUE_KEY)
        if (hiddenValue == null || hiddenValue == -1) {
            SettingsManager.setFlagInt(HIDDEN_VALUE_KEY, 0)
        }
    }

    private fun initializeDB() {
        Util.ioScope.launch {
            if (itemRepo.allItemsStatic().isEmpty()) {
                populateDB(itemListRepo)
            }
        }
    }

    private fun populateDB(itemListRepo: ItemListRepository) {
        val items = listOf(
            Item("Item1", "All"),
            Item("Item2", "All"),
            Item("Item3", "Other")
        )

        Util.ioScope.launch {
            items.forEach {
                // This method will insert those items in the Item-DB as well
                itemListRepo.insertItem("All", it)
            }
        }
    }
}
