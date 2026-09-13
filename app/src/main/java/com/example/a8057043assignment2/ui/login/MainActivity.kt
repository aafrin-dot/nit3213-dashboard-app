package com.example.a8057043assignment2.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.a8057043assignment2.R
import com.example.a8057043assignment2.ui.dashboard.DashboardActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var usernameField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button
    private lateinit var errorText: TextView

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameField = findViewById(R.id.usernameField)
        passwordField = findViewById(R.id.passwordField)
        loginButton = findViewById(R.id.loginButton)
        errorText = findViewById(R.id.errorText)

        loginButton.setOnClickListener {
            errorText.visibility = TextView.GONE
            viewModel.login(
                usernameField.text.toString().trim(),
                passwordField.text.toString().trim()
            )
        }

        lifecycleScope.launch {
            viewModel.keypass.collect { keypass ->
                if (keypass != null) {
                    val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                    intent.putExtra("keypass", keypass)
                    startActivity(intent)
                }
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