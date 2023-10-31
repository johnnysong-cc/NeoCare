package com.gokulraj.neocare.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

    }

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