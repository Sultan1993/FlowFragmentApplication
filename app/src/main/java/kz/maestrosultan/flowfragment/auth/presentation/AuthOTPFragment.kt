package kz.maestrosultan.flowfragment.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kz.maestrosultan.flowfragment.R
import kz.maestrosultan.flowfragment.core.extensions.finishFlow
import kz.maestrosultan.flowfragment.core.extensions.popBackStack
import kz.maestrosultan.flowfragment.databinding.FragmentAuthOtpBinding

class AuthOTPFragment : Fragment() {

    private lateinit var binding: FragmentAuthOtpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth_otp, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { popBackStack() }
        binding.flowButton.setOnClickListener { finishFlow() }
    }
}