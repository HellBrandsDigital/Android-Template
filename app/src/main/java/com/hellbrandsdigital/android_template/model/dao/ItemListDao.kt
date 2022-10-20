package com.hellbrandsdigital.android_template.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hellbrandsdigital.android_template.model.entity.ItemList
import com.hellbrandsdigital.android_template.model.entity.ListAndItems
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemListDao {

    @Transaction
    @Query("select * from itemList where listName = :listName")
    fun getItemsFromList(listName: String): Flow<MutableList<ListAndItems>>

    @Transaction
    @Query("select count(id) from itemList where listName = :listName and checked = 0")
    fun getUncheckedItems(listName: String): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemList: ItemList)

    @Query("delete from itemList where listName = :listName and itemId = :itemId")
    suspend fun deleteItemFromList(listName: String, itemId: Long)

    @Delete
    suspend fun deleteItemList(itemList: ItemList)

    @Query("delete from itemList where listName = :listName")
    suspend fun deleteList(listName: String)

    @Update
    suspend fun updateItem(itemList: ItemList)

    @Query("select * from itemList where listName = :listName and itemId = :itemId")
    suspend fun findItemInList(listName: String, itemId: Long): ItemList?

    @Query("delete from itemList")
    suspend fun deleteTable()
}
