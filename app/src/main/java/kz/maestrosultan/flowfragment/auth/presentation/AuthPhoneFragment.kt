package kz.maestrosultan.flowfragment.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.maestrosultan.flowfragment.R
import kz.maestrosultan.flowfragment.databinding.FragmentAuthPhoneBinding

class AuthPhoneFragment : Fragment() {

    private lateinit var binding: FragmentAuthPhoneBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth_phone, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.flowButton.setOnClickListener {
            findNavController().navigate(AuthPhoneFragmentDirections
                .actionAuthPhoneFragmentToAuthOTPFragment())
        }
    }
}