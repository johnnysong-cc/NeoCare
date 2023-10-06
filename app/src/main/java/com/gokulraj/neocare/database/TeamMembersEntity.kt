package com.gokulraj.neocare.database

data class TeamMembersEntity(
    val dev_name: String,
    val dev_id: String,
    val dev_unit: String,
    val dev_image: Int // Resource ID of the developer's image
)