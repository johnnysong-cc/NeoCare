package com.gokulraj.neocare.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.database.RequestStatus
import com.gokulraj.neocare.databinding.ActivityEmtEmergencyrequestDetailsBinding

/**
 * To handle viewing, updating, and transferring an emergency request.
 * */
class EmtEmergencyRequestDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmtEmergencyrequestDetailsBinding
    private var requestId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmtEmergencyrequestDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestId = intent.getStringExtra("REQUEST_ID")
        loadRequestDetails(requestId)

        binding.btnUpdateRequest.setOnClickListener {
            updateRequestStatus()
        }

        binding.btnTransferRequest.setOnClickListener {
            transferRequest()
        }
    }

    private fun loadRequestDetails(requestId: String?) {
        // Fetch request details from Firebase using requestId
        // Placeholder for Firebase logic
        // Example data for UI demonstration
        binding.tvPatientName.text = "John Doe"
        binding.tvLocation.text = "123 Main St"
        binding.tvDetails.text = "Severe allergic reaction"
    }

    private fun updateRequestStatus() {
        val updatedStatus = RequestStatus.IN_PROGRESS // Example status update
        // Update request status in Firebase
        // Placeholder for Firebase logic
        Toast.makeText(this, "Request status updated", Toast.LENGTH_SHORT).show()
    }

    private fun transferRequest() {
        val intent = Intent(this, EmtEmergencyRequestTransferActivity::class.java).apply {
            putExtra("REQUEST_ID", requestId)
        }
        startActivity(intent)
    }
}
