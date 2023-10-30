package com.gokulraj.neocare.database

data class Patient(
    val id: String? = null,
    val fullName: String? = null,
    val healthCardNumber: String? = null,
    val email: String? = null,
    val password: String? = null,
    val isTermsAccepted: Boolean? = false
)
