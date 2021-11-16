package com.arthurfp.cookaholic.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arthurfp.cookaholic.R
import com.arthurfp.cookaholic.databinding.FragmentRegisterBinding
import com.arthurfp.cookaholic.util.MyTextListeners
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.btnSignIn.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }

//        val userListener = MyTextListeners(binding.textInputUser, getString(R.string.errorLoginUser))
//        val passwordListener = MyTextListeners(binding.textInputPassword, getString(R.string.errorLoginPass))
//
//        binding.editTextUser.addTextChangedListener(userListener.MyTextWatcher())
//        binding.editTextUser.onFocusChangeListener = userListener.MyOnFocusChangeListener()
//        binding.editTextPassword.addTextChangedListener(passwordListener.MyTextWatcher())
//        binding.editTextPassword.onFocusChangeListener = passwordListener.MyOnFocusChangeListener()
//
//        binding.btnLogin.setOnClickListener {
//            binding.loginErrorMessage.visibility = View.GONE
//            //TODO: terminar de implementar
//        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}