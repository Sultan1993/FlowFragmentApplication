package kz.maestrosultan.flowfragment.dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.maestrosultan.flowfragment.R
import kz.maestrosultan.flowfragment.databinding.FragmentDashboardBinding
import kz.maestrosultan.flowfragment.profile.ProfileFlowArgs

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.flowButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections
                .actionDashboardFragmentToProfileFlowFragment(ProfileFlowArgs(false)))
        }

        binding.flowInnerButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections
                .actionDashboardFragmentToProfileFlowFragment(ProfileFlowArgs(true)))
        }
    }
}