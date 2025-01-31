package com.gokulraj.neocare.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.R
import com.gokulraj.neocare.databinding.ActivityAboutusBinding


class AboutUsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAboutusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus)
        binding = ActivityAboutusBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvAboutDetails.text = getString(R.string.about_us)

    }
}
