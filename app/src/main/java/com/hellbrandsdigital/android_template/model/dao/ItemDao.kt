package com.hellbrandsdigital.android_template.model.dao

import androidx.room.*
import com.hellbrandsdigital.android_template.model.entity.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("select * from item")
    suspend fun getAllStatic(): List<Item>

    @Query("select * from item")
    fun getAll(): Flow<MutableList<Item>>

    @Query("select * from item where category = :category")
    fun getByCategory(category: String): Flow<MutableList<Item>>

    @Query("select * from item where name = :name and category = :category")
    suspend fun findItemByNameAndCategory(name: String, category: String): Item?

    @Query("select * from item where id = :id")
    suspend fun findItemById(id: Long): Item?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Query("delete from item")
    suspend fun clearTable()

    @Delete
    suspend fun delete(item: Item)
}
