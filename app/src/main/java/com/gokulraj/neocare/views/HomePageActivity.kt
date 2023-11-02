package com.gokulraj.neocare.views

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityHomepageBinding
import kotlin.system.exitProcess

class HomePageActivity:AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* binding.requestButton.setOnClickListener {
            // Handle the emergency services request here
            // You can start an emergency service activity, make a call, or perform any other action as needed.
            // For this example, we'll open the phone dialer to call an emergency number (e.g., 911).
            val phoneNumber = "tel:911"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
            startActivity(intent)
        }
        */

        
        /*binding.registrationLink.setOnClickListener {
            startActivity(Intent(this,RegistrationActivity::class.java))
        }
            */



        binding.teamMembersLink.setOnClickListener {
            startActivity(Intent(this, TeamMembersActivity::class.java))
        }

        binding.aboutUsLink.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }
        binding.firstAidLink.setOnClickListener {
            startActivity(Intent(this, FirstAidView::class.java))
        }

        binding.updateProfileLink.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }

    }

    /*
    private fun startVoiceCommand() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        try {
            startActivityForResult(intent, VOICE_COMMAND_REQUEST_CODE)
        } catch (e: ActivityNotFoundException) {
            // Handle the case where voice recognition is not supported on the device
            Toast.makeText(this, "Voice recognition not supported on this device", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle the result of the voice command
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VOICE_COMMAND_REQUEST_CODE && resultCode == RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val command = result?.get(0) // Get the recognized voice command

            // Check the recognized voice command and perform the action
            if (command.equals("call ambulance", ignoreCase = true)) {
                // You can initiate a call to ambulance services here
                val phoneNumber = "tel:911"
                val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
                startActivity(callIntent)
            } else {
                Toast.makeText(this, "Voice command not recognized or supported.", Toast.LENGTH_SHORT).show()
            }
        }
    }

     */
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                // If the user clicks "Yes", exit the app
                finishAffinity()
                exitProcess(0)
            }
            .setNegativeButton("No", null)
            .show()

    }
}