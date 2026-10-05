package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Welcome user
        val userEmail = intent.getStringExtra("USER_EMAIL")

        if (!userEmail.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "Welcome, $userEmail!",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Save tenant
        binding.saveButton.setOnClickListener {

            if (binding.tenantNameEditText.text.toString().isEmpty()) {
                binding.tenantNameEditText.error = "Tenant name is required"
                return@setOnClickListener
            }

            val name = binding.tenantNameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val rent = binding.rentEditText.text.toString()

            val tenant = Tenant(name, phone, rent)

            binding.tenant = tenant

            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
        }

        // Open website
        binding.websiteButton.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.strathmore.edu/")
            )

            startActivity(intent)
        }

        // Call tenant
        binding.callTenantButton.setOnClickListener {

            val phoneNumber =
                binding.phoneEditText.text.toString().trim()

            if (phoneNumber.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter the tenant phone number",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:$phoneNumber")
                )

                startActivity(intent)
            }
        }

        // Share tenant
        binding.shareTenantButton.setOnClickListener {

            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            val shareText = """
                Tenant Name: $name
                Phone Number: $phone
                Rent Paid: $rent
            """.trimIndent()

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, shareText)

            startActivity(
                Intent.createChooser(intent, "Share Tenant")
            )
        }
    }
}