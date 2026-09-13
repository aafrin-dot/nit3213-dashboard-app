package com.example.a8057043assignment2.ui.details

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a8057043assignment2.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val concept = intent.getStringExtra("concept") ?: ""
        val scientist = intent.getStringExtra("scientist") ?: ""
        val field = intent.getStringExtra("field") ?: ""
        val branch = intent.getStringExtra("branch") ?: ""
        val yearProposed = intent.getIntExtra("yearProposed", 0)
        val description = intent.getStringExtra("description") ?: ""

        findViewById<TextView>(R.id.detailsConcept).text = concept
        findViewById<TextView>(R.id.detailsScientist).text = scientist
        findViewById<TextView>(R.id.detailsField).text = field
        findViewById<TextView>(R.id.detailsBranch).text = branch
        findViewById<TextView>(R.id.detailsYear).text = yearProposed.toString()
        findViewById<TextView>(R.id.detailsDescription).text = description
    }
}