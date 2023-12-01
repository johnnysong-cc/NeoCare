package com.gokulraj.neocare.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityPatientDetailBinding

class PatientDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val patientId = intent.getStringExtra("PATIENT_ID")
        loadPatientDetails(patientId)
    }

    private fun loadPatientDetails(patientId: String?) {
        // Fetch patient details from a database or backend service using patientId
        // Placeholder for data fetching logic
        // Example data for UI demonstration
        binding.tvPatientNameDetail.text = "John Doe"
        binding.tvPatientConditionDetail.text = "Chronic"
        binding.tvPatientLocationDetail.text = "123 Main St"
    }
}
