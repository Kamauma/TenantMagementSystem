package com.example.tenantmanagementsystem.ui.tenants

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController



import com.example.tenantmanagementsystem.databinding.FragmentAddTenantBinding
import androidx.fragment.app.activityViewModels
import com.example.tenantmanagementsystem.ui.viewmodel.TenantViewModel
class AddTenantFragment : Fragment() {

    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TenantViewModel by activityViewModels()
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
            val tenant = viewModel.addTenant(
                binding.tenantNameEditText.text.toString(),
                binding.phoneEditText.text.toString(),
                binding.rentEditText.text.toString()
            )

            if (tenant == null) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val action = AddTenantFragmentDirections
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