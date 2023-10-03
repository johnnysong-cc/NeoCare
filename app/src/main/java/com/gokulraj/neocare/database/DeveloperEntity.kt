package com.gokulraj.neocare.database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class DeveloperEntity(
    val dev_name: String,
    val dev_id: String,
    val dev_unit: String,
    val dev_image: Int // Resource ID of the developer's image
)