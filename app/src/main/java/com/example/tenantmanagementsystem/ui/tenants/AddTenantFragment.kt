package com.example.tenantmanagementsystem.ui.tenants

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.example.tenantmanagementsystem.data.model.Tenant
import com.example.tenantmanagementsystem.data.repository.TenantRepository
import com.example.tenantmanagementsystem.databinding.FragmentAddTenantBinding

class AddTenantFragment : Fragment() {

    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "TENANT_LIFECYCLE"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "AddTenantFragment: onCreateView")

        _binding = FragmentAddTenantBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "AddTenantFragment: onViewCreated")

        binding.saveTenantButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Tenant name is required"
                return@setOnClickListener
            }

            if (phone.isEmpty()) {
                binding.phoneEditText.error = "Phone number is required"
                return@setOnClickListener
            }

            if (rent.isEmpty()) {
                binding.rentEditText.error = "Rent is required"
                return@setOnClickListener
            }

            val tenant = Tenant(name, phone, rent)

            TenantRepository.addTenant(tenant)

            Toast.makeText(
                requireContext(),
                "Tenant saved successfully",
                Toast.LENGTH_SHORT
            ).show()

            val action =
                AddTenantFragmentDirections
                    .actionAddTenantFragmentToViewTenantsFragment(tenant)

            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "AddTenantFragment: onDestroyView")
        _binding = null
    }
}