package com.gokulraj.neocare.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gokulraj.neocare.adapters.PatientAdapter
import com.gokulraj.neocare.database.Patient
import com.gokulraj.neocare.databinding.ActivityViewPatientsBinding

class ViewPatientsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPatientsBinding
    private lateinit var patientAdapter: PatientAdapter
    private var patientsList = mutableListOf<Patient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPatientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadPatients()
    }

    private fun setupRecyclerView() {
        patientAdapter = PatientAdapter(patientsList) { patient ->
            // Navigate to patient detail view
            val intent = Intent(this, PatientDetailActivity::class.java)
            intent.putExtra("PATIENT_ID", patient.id)
            startActivity(intent)
        }

        binding.rvPatients.apply {
            layoutManager = LinearLayoutManager(this@ViewPatientsActivity)
            adapter = patientAdapter
        }
    }

    private fun loadPatients() {
        // Fetch patients from a database or backend service
        patientsList.add(Patient("PT101", "John Doe", "Stable", "123 Main St"))
        patientsList.add(Patient("PT102", "Jane Smith", "Chronic", "456 Oak St"))
        patientAdapter.notifyDataSetChanged()
    }
}
