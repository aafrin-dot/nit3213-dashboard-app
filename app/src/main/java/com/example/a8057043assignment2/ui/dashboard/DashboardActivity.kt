package com.example.a8057043assignment2.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a8057043assignment2.R
import com.example.a8057043assignment2.ui.details.DetailsActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var errorText: TextView
    private lateinit var adapter: EntityAdapter

    private val viewModel: DashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        recyclerView = findViewById(R.id.entityRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        errorText = findViewById(R.id.dashboardError)

        adapter = EntityAdapter { entity ->
            val detailsIntent = Intent(this, DetailsActivity::class.java)
            detailsIntent.putExtra("field", entity.field)
            detailsIntent.putExtra("concept", entity.concept)
            detailsIntent.putExtra("scientist", entity.scientist)
            detailsIntent.putExtra("yearProposed", entity.yearProposed)
            detailsIntent.putExtra("branch", entity.branch)
            detailsIntent.putExtra("description", entity.description)
            startActivity(detailsIntent)
        }
        recyclerView.adapter = adapter

        val keypass = intent.getStringExtra("keypass") ?: ""
        viewModel.loadDashboard(keypass)

        lifecycleScope.launch {
            viewModel.entities.collect { list ->
                adapter.setData(list)
            }
        }

        lifecycleScope.launch {
            viewModel.errorState.collect { error ->
                if (error != null) {
                    errorText.text = error
                    errorText.visibility = TextView.VISIBLE
                }
            }
        }
    }
}