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

        // Find the TextView by its ID
        val aboutUsLink = binding.aboutUsLink

        // Create a SpannableString with underlined text
        val spannableString = SpannableString("About Us")

        // Apply UnderlineSpan to the text
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Apply other styles if needed, e.g., changing text color and style
        spannableString.setSpan(ForegroundColorSpan(Color.CYAN), 0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), 0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set the modified SpannableString as the text for the TextView
        aboutUsLink.text = spannableString

        // Set an onClick listener for the TextView
        aboutUsLink.setOnClickListener {
            // Handle the click event (e.g., navigate to the "About Us" screen)
            openAboutUsActivity()
        }

        binding.startButton.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))


        }

    }
    private fun openAboutUsActivity() {
        // Implement your logic to open the "About Us" screen here
        startActivity(Intent(this, AboutUsActivity::class.java))
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