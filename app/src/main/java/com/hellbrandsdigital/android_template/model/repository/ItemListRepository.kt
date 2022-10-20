package com.hellbrandsdigital.android_template.model.repository

import androidx.annotation.WorkerThread
import com.hellbrandsdigital.android_template.model.dao.ItemDao
import com.hellbrandsdigital.android_template.model.dao.ItemListDao
import com.hellbrandsdigital.android_template.model.entity.Item
import com.hellbrandsdigital.android_template.model.entity.ItemList
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemListRepository @Inject constructor(
    private val dao: ItemListDao,
    private val itemDAO: ItemDao
) {
    fun allListItems(listName: String) = dao.getItemsFromList(listName)

    fun allListItemsWithCategory(listName: String, category: String) =
        dao.getItemsFromList(listName).map { mutableList ->
            mutableList
                .map { list -> list.item }
                .flatten()
                .filter { it.category == category }
        }

    fun allUncheckedListItems(listName: String) = dao.getUncheckedItems(listName)

    @WorkerThread
    suspend fun insertItems(listName: String, items: List<Item>) =
        items.forEach {
            insertItem(listName, it)
        }

    @WorkerThread
    suspend fun insertItem(listName: String, item: Item) {
        val itemIns: Item = getItem(item)
        val new = ItemList(listName, itemIns.id, false)
        dao.insert(new)
    }

    private suspend fun getItem(item: Item): Item {
        if (itemDAO.findItemByNameAndCategory(item.name, item.category) == null) {
            itemDAO.insert(Item(item.name, item.category))
        }
        return itemDAO.findItemByNameAndCategory(item.name, item.category)!!
    }

    @WorkerThread
    suspend fun updateList(listName: String, newListName: String) {
        dao.getItemsFromList(listName).collect { list ->
            list.forEach {
                val x = it.itemList
                x.listName = newListName
                dao.updateItem(x)
            }
        }
    }

    @WorkerThread
    suspend fun deleteItem(listName: String, itemId: Long) =
        dao.deleteItemFromList(listName, itemId)

    @WorkerThread
    suspend fun deleteList(listName: String) = dao.deleteList(listName)

    @WorkerThread
    suspend fun findItemInList(listName: String, itemId: Long) =
        dao.findItemInList(listName, itemId)

    @WorkerThread
    suspend fun checkItem(listName: String, itemId: Long) {
        val item = dao.findItemInList(listName, itemId)
        if (item != null) {
            item.checked = !item.checked
            dao.updateItem(item)
        }
    }

    @WorkerThread
    suspend fun clearTable() = dao.deleteTable()

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: ItemListRepository? = null

        fun getInstance(dao: ItemListDao, itemDAO: ItemDao) =
            instance ?: synchronized(this) {
                instance ?: ItemListRepository(dao, itemDAO).also { instance = it }
            }
    }
}
