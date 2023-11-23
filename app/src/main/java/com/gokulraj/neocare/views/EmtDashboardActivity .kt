package com.gokulraj.neocare.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.gokulraj.neocare.R
import com.google.firebase.auth.FirebaseAuth

class EmtDashboardActivity : AppCompatActivity() {

    private lateinit var requestButton: Button
    private lateinit var viewPatientsButton: Button
    private lateinit var updatePatientButton: Button
    private lateinit var updateProfileButton: Button
    private lateinit var communicationToolButton: Button
    private lateinit var medicalGuidelinesButton: Button
    private lateinit var medicalEducationButton: Button
    private lateinit var logoutButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emt_dashboard)

        requestButton = findViewById(R.id.requestButton)
        viewPatientsButton = findViewById(R.id.viewPatientsButton)
        updateProfileButton = findViewById(R.id.updateProfileButton)
        communicationToolButton = findViewById(R.id.communicationToolButton)
        medicalGuidelinesButton = findViewById(R.id.medicalGuidelinesButton)
        medicalEducationButton = findViewById(R.id.medicalEducationButton)
        logoutButton = findViewById(R.id.logoutButton)

        requestButton.setOnClickListener {
            // Handle EMT request action
            // To initiate an emergency request process
            // For ideal, open a new activity to request medical assistance
            startActivity(Intent(this@EmtDashboardActivity, EmtRequestActivity::class.java))
        }

        viewPatientsButton.setOnClickListener {
            // Handle EMT's ability to view patient information
            // To navigate to a screen that displays a list of patients
            // and allows EMTs to access patient data.
            startActivity(Intent(this@EmtDashboardActivity, ViewPatientsActivity::class.java))
        }

        updatePatientButton.setOnClickListener {
            // Allow EMT to update patient information
            // Navigation
            startActivity(Intent(this@EmtDashboardActivity, updatePatientActivity::class.java))
        }

        updateProfileButton.setOnClickListener {
            // Allow EMT to update their profile information
            // Navigate to the profile update screen
            startActivity(Intent(this@EmtDashboardActivity, ProfileActivity::class.java))
        }

        communicationToolButton.setOnClickListener {
            // Handle EMT's communication tool to contact the patient
            // Provide a screen with contact details of the patient and options to make calls or initiate video chats.
            startActivity(Intent(this@EmtDashboardActivity, EmtCommunicationActivity::class.java))
        }

        medicalGuidelinesButton.setOnClickListener {
            // Handle EMT's access to medical guidelines and protocols
            // Create a screen to display medical guidelines and protocols for complex or rare medical conditions.
            startActivity(Intent(this@EmtDashboardActivity, EmtMedicalGuidelinesActivity::class.java))
        }

        medicalEducationButton.setOnClickListener {
            // Handle EMT's access to continuing medical education
            // Provide links to valuable resources and courses related to emergency medical services.
            startActivity(Intent(this@EmtDashboardActivity, EmtMedicalEducationActivity::class.java))
        }


        logoutButton.setOnClickListener {
            // Handle EMT logout action
            logoutUser()
        }
    }

    private fun logoutUser() {
        // Implement the logout logic for EMTs
        // To clear the user session and return to the login screen
        // Similar to the logic in LoginActivity or HomePageActivity

        FirebaseAuth.getInstance().signOut() // Sign out the user from Firebase Authentication

        // Clear any stored user information and preferences if needed

        // Redirect to the login screen
        startActivity(Intent(this@EmtDashboardActivity, LoginActivity::class.java))
        finish() // Finish the current activity
    }
}
