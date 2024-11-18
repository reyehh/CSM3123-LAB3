package com.example.sharedpreferencesdemo

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var greetingTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        greetingTextView = findViewById(R.id.tv_greeting)
        nameEditText = findViewById(R.id.et_name)
        saveButton = findViewById(R.id.btn_save)
        loadButton = findViewById(R.id.btn_load)

        // Set up button listeners
        saveButton.setOnClickListener {
            saveName()
        }

        loadButton.setOnClickListener {
            loadName()
        }

        // Optional edge-to-edge display setup (if needed)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun saveName() {
        // Get the SharedPreferences object
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

        // Open the editor to write to SharedPreferences
        val editor = sharedPreferences.edit()

        // Get the name from EditText and save it with a key
        val name = nameEditText.text.toString()
        editor.putString("userName", name)

        // Apply changes to save the data
        editor.apply()

        // Show a confirmation message
        greetingTextView.text = "Name saved!"
    }

    private fun loadName() {
        // Get the SharedPreferences object
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

        // Retrieve the saved name using a key
        val savedName = sharedPreferences.getString("userName", "No name saved")

        // Display the saved name in the TextView
        greetingTextView.text = "Welcome, $savedName!"
    }
}
