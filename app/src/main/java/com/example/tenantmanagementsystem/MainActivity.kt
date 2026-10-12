package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastTenant: Tenant? = null

    companion object {
        private const val TAG = "TENANT_LIFECYCLE"
        private const val TENANT_NAME = "tenant_name"
        private const val TENANT_PHONE = "tenant_phone"
        private const val TENANT_RENT = "tenant_rent"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity: onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            val name = savedInstanceState.getString(TENANT_NAME)
            val phone = savedInstanceState.getString(TENANT_PHONE)
            val rent = savedInstanceState.getString(TENANT_RENT)

            if (name != null && phone != null && rent != null) {
                lastTenant = Tenant(name, phone, rent)
                binding.tenant = lastTenant
            }
        }

        val userEmail = intent.getStringExtra("USER_EMAIL")
        if (!userEmail.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "Welcome, $userEmail!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Tenant name is required"
                return@setOnClickListener
            }

            lastTenant = Tenant(name, phone, rent)
            binding.tenant = lastTenant

            binding.tenantNameEditText.text?.clear()
            binding.phoneEditText.text?.clear()
            binding.rentEditText.text?.clear()

            Toast.makeText(
                this,
                "Tenant saved successfully",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.websiteButton.setOnClickListener {
            val websiteIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.strathmore.edu/")
            )
            startActivity(websiteIntent)
        }

        binding.callTenantButton.setOnClickListener {
            val phoneNumber = binding.phoneEditText.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter the tenant phone number",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val callIntent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:$phoneNumber")
                )
                startActivity(callIntent)
            }
        }

        binding.shareTenantButton.setOnClickListener {
            val tenant = lastTenant

            if (tenant == null) {
                Toast.makeText(
                    this,
                    "Save a tenant first",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val shareText = tenant.summary()

                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareText)
                }

                startActivity(
                    Intent.createChooser(shareIntent, "Share Tenant")
                )
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "MainActivity: onSaveInstanceState")

        lastTenant?.let { tenant ->
            outState.putString(TENANT_NAME, tenant.name)
            outState.putString(TENANT_PHONE, tenant.phone)
            outState.putString(TENANT_RENT, tenant.rent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "MainActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity: onDestroy")
    }
}
