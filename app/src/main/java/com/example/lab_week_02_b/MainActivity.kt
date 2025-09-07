package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
    }

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        val error = data?.getBooleanExtra(ERROR_KEY, false)
        if (error == true) {
            Toast.makeText(this, "Color code is invalid!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colorInput = findViewById<TextInputEditText>(R.id.color_code_input_field)
        val submitBtn = findViewById<Button>(R.id.submit_button)

        submitBtn.setOnClickListener {
            val colorCode = colorInput.text.toString()

            if (colorCode.isEmpty()) {
                Toast.makeText(this, "Color code field is empty!", Toast.LENGTH_LONG).show()
            } else if (colorCode.length < 6) {
                Toast.makeText(this, "Color code must be 6 digits!", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(COLOR_KEY, colorCode)
                startActivity(intent)
            }
        }
    }
}
