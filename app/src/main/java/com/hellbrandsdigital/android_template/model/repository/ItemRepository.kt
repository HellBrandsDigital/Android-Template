package com.hellbrandsdigital.android_template.model.repository

import androidx.annotation.WorkerThread
import com.hellbrandsdigital.android_template.model.dao.ItemDao
import com.hellbrandsdigital.android_template.model.entity.Item
import javax.inject.Inject

class ItemRepository @Inject constructor(private val dao: ItemDao) {

    @WorkerThread
    suspend fun allItemsStatic() = dao.getAllStatic()

    @WorkerThread
    suspend fun findItem(name: String, category: String): Item? =
        dao.findItemByNameAndCategory(name, category)

    @WorkerThread
    suspend fun findItem(id: Long) = dao.findItemById(id)

    @WorkerThread
    suspend fun insert(item: Item) {
        if (item.id == 0L) {
            val itemFound = findItem(item.name, item.category)
            if (itemFound != null) {
                item.id = itemFound.id
            }
        }
        dao.insert(item)
    }

    @WorkerThread
    suspend fun clearTable() = dao.clearTable()

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: ItemRepository? = null

        fun getInstance(dao: ItemDao) =
            instance ?: synchronized(this) {
                instance ?: ItemRepository(dao).also { instance = it }
            }
    }
}
