package com.gokulraj.neocare.database

enum class RequestStatus {
    PENDING, // Initial state, waiting for response
    IN_PROGRESS, // EMT has accepted and is attending to the request
    NEEDS_HIGHER_ATTENTION, // Marked for transfer to a higher authority or Other EMT
    COMPLETED, // Emergency handled and completed
    CANCELLED // Request cancelled or invalidated
}

data class EmergencyRequest(
    val requestId: String,
    val patientName: String,
    val location: String,
    val emergencyDetails: String,
    var status: RequestStatus = RequestStatus.PENDING // Default status
)
