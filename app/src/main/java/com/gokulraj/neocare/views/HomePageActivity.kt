package com.gokulraj.neocare.views

import android.content.Intent
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