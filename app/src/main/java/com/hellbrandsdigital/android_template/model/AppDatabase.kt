package com.hellbrandsdigital.android_template.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hellbrandsdigital.android_template.model.dao.ItemDao
import com.hellbrandsdigital.android_template.model.dao.ItemListDao
import com.hellbrandsdigital.android_template.model.entity.Item
import com.hellbrandsdigital.android_template.model.entity.ItemList

@Database(
    entities = [Item::class, ItemList::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun itemListDao(): ItemListDao
}
