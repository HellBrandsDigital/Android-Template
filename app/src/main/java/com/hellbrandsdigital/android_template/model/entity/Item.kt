package com.hellbrandsdigital.android_template.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "category") var category: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
