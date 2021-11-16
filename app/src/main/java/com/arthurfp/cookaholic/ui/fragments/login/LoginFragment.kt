package com.arthurfp.cookaholic.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arthurfp.cookaholic.R
import com.arthurfp.cookaholic.databinding.FragmentLoginBinding
import com.arthurfp.cookaholic.ui.MainActivity
import com.arthurfp.cookaholic.util.MyTextListeners
import com.arthurfp.cookaholic.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        val userListener = MyTextListeners(binding.textInputUser, getString(R.string.errorLoginUser))
        val passwordListener = MyTextListeners(binding.textInputPassword, getString(R.string.errorLoginPass))

        binding.editTextUser.addTextChangedListener(userListener.MyTextWatcher())
        binding.editTextUser.onFocusChangeListener = userListener.MyOnFocusChangeListener()
        binding.editTextPassword.addTextChangedListener(passwordListener.MyTextWatcher())
        binding.editTextPassword.onFocusChangeListener = passwordListener.MyOnFocusChangeListener()

        binding.btnLogin.setOnClickListener {
            binding.loginErrorMessage.visibility = View.GONE
            login()
        }

        return binding.root
    }

    private fun login() {
        val strUser = binding.editTextUser.text.toString().trim()
        val strPass = binding.editTextPassword.text.toString()

        if (strUser.isEmpty() || strPass.isEmpty()) {
            if (strUser.isEmpty()) {
                binding.textInputUser.error = getString(R.string.errorLoginUser)
            }

            if (strPass.isEmpty()) {
                binding.textInputPassword.error = getString(R.string.errorLoginPass)
            }

        } else {
            loginViewModel.getLoginDetails(strUser).observe(viewLifecycleOwner, { response ->
                if (response != null) {
                    if (response.login.equals(strUser) && response.pass.equals(strPass)) {
                        activity?.let {
                            val intent = Intent(it, MainActivity::class.java)
                            it.startActivity(intent)
                        }
                    } else {
                        binding.loginErrorMessage.visibility = View.VISIBLE
                    }
                } else {
                    binding.loginErrorMessage.visibility = View.VISIBLE
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}