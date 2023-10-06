package com.gokulraj.neocare.views

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AlertDialog
import com.gokulraj.neocare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.startButton.setOnClickListener {
            startActivity(Intent(this, TeamMembersActivity::class.java))
        }
        binding.teamMembersLink.setOnClickListener {
            startActivity(Intent(this, TeamMembersActivity::class.java))
        }



    }


    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                // If the user clicks "Yes", exit the app
                finishAffinity()
            }
            .setNegativeButton("No", null)
            .show()
    }

}