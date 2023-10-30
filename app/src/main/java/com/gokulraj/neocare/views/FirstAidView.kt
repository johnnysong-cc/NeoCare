package com.gokulraj.neocare.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.R

class FirstAidView : AppCompatActivity() {
    private lateinit var emergencyDescriptionTextView: TextView
    private lateinit var firstAidInfoTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emergencyDescriptionTextView = findViewById(R.id.emergencyDescriptionTextView)
        firstAidInfoTextView = findViewById(R.id.firstAidInfoTextView)
        val emergencyRadioGroup = findViewById<RadioGroup>(R.id.emergencyRadioGroup)

        emergencyRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            val emergency = selectedRadioButton.text.toString()
            displayEmergencyDetails(emergency)
        }
    }

    private fun displayEmergencyDetails(emergency: String) {
        val description = getEmergencyDescription(emergency)
        val firstAidInfo = getFirstAidInfo(emergency)

        emergencyDescriptionTextView.text = "Description: $description"
        firstAidInfoTextView.text = "First Aid Info: $firstAidInfo"
    }

    private fun getEmergencyDescription(emergency: String): String {
        // Replace with actual descriptions for each emergency
        return when (emergency) {
            "Fire" -> "A fire emergency involves the presence of an uncontrolled fire. Stay low and evacuate the building."
            "Earthquake" -> "An earthquake is the shaking of the surface of the Earth. Take cover under sturdy furniture."
            // Add descriptions for other emergencies
            else -> "No description available"
        }
    }

    private fun getFirstAidInfo(emergency: String): String {
        // Replace with actual first aid information for each emergency
        return when (emergency) {
            "Fire" -> "For fire-related injuries, use a fire blanket or extinguisher if safe to do so and seek medical help."
            "Earthquake" -> "For earthquake-related injuries, provide first aid as needed and call for medical assistance."
            // Add first aid information for other emergencies
            else -> "No first aid information available"
        }
    }
}
