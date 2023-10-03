package com.gokulraj.neocare.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gokulraj.neocare.R
import com.gokulraj.neocare.adapters.AboutUsAdapter
import com.gokulraj.neocare.database.DeveloperEntity

class AboutUsActivity:AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var aboutUsAdapter: AboutUsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus)

        recyclerView = findViewById(R.id.rvAbout)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadDevelopers()
        // Sample data for demonstration

    }

    private fun loadDevelopers() {
        val developerList = ArrayList<DeveloperEntity>()
        developerList.add(DeveloperEntity("Gokulraj Venugopal", "301202722", "Health Informatics Technology",R.drawable.gokul_image))
        developerList.add(DeveloperEntity("Vinny Mariam Vinu", "301234317", "Health Informatics Technology",R.drawable.developer_image))
        developerList.add(DeveloperEntity("Husna Fatima", "300993436", "Health Informatics Technology",R.drawable.developer_image))
        developerList.add(DeveloperEntity("Lea Marie Magbalot", "301145757", "Health Informatics Technology",R.drawable.developer_image))
        developerList.add(DeveloperEntity("Zhiyang Song", "301167073", "Health Informatics Technology",R.drawable.developer_image))


        aboutUsAdapter = AboutUsAdapter(developerList)
        recyclerView.adapter = aboutUsAdapter


    }


}