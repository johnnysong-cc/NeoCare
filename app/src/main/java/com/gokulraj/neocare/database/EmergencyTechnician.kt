package com.gokulraj.neocare.database

data class EmergencyTechnician(
    val emtid: String? = null,
    val fullName: String? = null,
    val contactNumber: String? = null,
    val email: String? = null,
    val password: String? = null,
    val specialization: String? = null,
    val userType: String? = "EMT"
)
