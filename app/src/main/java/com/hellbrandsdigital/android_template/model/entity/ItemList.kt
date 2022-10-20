package com.hellbrandsdigital.android_template.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.hellbrandsdigital.android_template.model.entity.Item

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Item::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class ItemList(
    @ColumnInfo(name = "listName") var listName: String,
    @ColumnInfo(name = "itemId", index = true) var itemId: Long,
    @ColumnInfo(name = "checked") var checked: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
