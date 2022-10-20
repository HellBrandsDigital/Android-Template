package com.hellbrandsdigital.android_template.ui.dashboard

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.hellbrandsdigital.android_template.MainApplication
import com.hellbrandsdigital.android_template.model.entity.Item
import com.hellbrandsdigital.android_template.model.repository.ItemListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val itemListRepository: ItemListRepository) : ViewModel() {

    fun onCheckListClick(itemViewPair: Pair<Item, View>) {
        Toast.makeText(MainApplication.instance.applicationContext, "Clicked on Item ${itemViewPair.first.name}", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NotifyDataSetChanged") // Since all changes may happen, can't be more precise here
    fun subscribeUi(
        lifecycle: Lifecycle,
        checkListAdapter: ItemListAdapter,
        category: String = "All"
    ) {
        val itemFLow = if (category == "All") itemListFlow()
        else getItemCategoryListFlow("All", category)

        checkListAdapter.notifyDataSetChanged()
        addListItems(itemFLow, lifecycle, checkListAdapter)
    }

    private fun itemListFlow() = itemListRepository.allListItems("All").map {
        it.map { list -> list.item }.flatten()
    }

    private fun addListItems(
        flow: Flow<List<Item>>,
        lifecycle: Lifecycle,
        adapter: ItemListAdapter
    ) {
        flow.apply {
            flowWithLifecycle(lifecycle)
                .onEach {
                    adapter.submitList(it)
                }
                .launchIn(lifecycle.coroutineScope)
        }
    }

    private fun getItemCategoryListFlow(listName: String, category: String) =
        itemListRepository.allListItemsWithCategory(listName, category)
}
