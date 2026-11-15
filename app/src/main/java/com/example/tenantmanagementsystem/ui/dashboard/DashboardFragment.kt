package com.example.tenantmanagementsystem.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.tenantmanagementsystem.databinding.FragmentDashboardBinding
import androidx.fragment.app.activityViewModels
import com.example.tenantmanagementsystem.ui.viewmodel.TenantViewModel
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: TenantViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}