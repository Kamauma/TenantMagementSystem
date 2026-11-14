package com.example.tenantmanagementsystem.ui.tenants

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tenantmanagementsystem.R
import com.example.tenantmanagementsystem.data.repository.TenantRepository
import com.example.tenantmanagementsystem.databinding.FragmentViewTenantsBinding

class ViewTenantsFragment : Fragment() {

    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!

    private val args: ViewTenantsFragmentArgs by navArgs()

    companion object {
        private const val TAG = "TENANT_LIFECYCLE"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "ViewTenantsFragment: onCreateView")

        _binding = FragmentViewTenantsBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "ViewTenantsFragment: onViewCreated")

        val newTenant = args.newTenant
        val tenants = TenantRepository.tenants

        val tenantList = if (tenants.isEmpty()) {
            "No tenants yet. Add one from the Dashboard."
        } else {
            tenants.joinToString("\n\n") { it.summary() }
        }

        binding.tenantListTextView.text = if (newTenant != null) {
            "Newly added tenant:\n${newTenant.summary()}\n\n" +
                    "All tenants:\n$tenantList"
        } else {
            tenantList
        }

        // Call the newly added tenant when one was passed
        if (newTenant != null) {
            val callButton = binding.root.findViewById<
                    Button
                    >(R.id.callButton)

            callButton?.setOnClickListener {
                if (newTenant.phone.isBlank()) {
                    Toast.makeText(
                        requireContext(),
                        "Tenant phone number is missing",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val dialIntent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:${newTenant.phone}")
                    )
                    startActivity(dialIntent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "ViewTenantsFragment: onDestroyView")
        _binding = null
    }
}