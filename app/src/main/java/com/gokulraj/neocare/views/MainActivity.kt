package com.gokulraj.neocare.views

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AlertDialog
import com.gokulraj.neocare.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*binding.startButton.setOnClickListener {
            startActivity(Intent(this, HomePageActivity::class.java))
        }

         */

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomePageActivity::class.java))
        }, 5000)


    }



}