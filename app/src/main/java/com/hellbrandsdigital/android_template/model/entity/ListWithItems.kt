package com.hellbrandsdigital.android_template.model.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.hellbrandsdigital.android_template.model.entity.Item
import com.hellbrandsdigital.android_template.model.entity.ItemList

data class ListAndItems(
    @Embedded val itemList: ItemList,
    @Relation(
        parentColumn = "itemId",
        entityColumn = "id"
    )
    val item: List<Item>
)
