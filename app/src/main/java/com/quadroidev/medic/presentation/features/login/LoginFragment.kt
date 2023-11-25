package com.quadroidev.medic.presentation.features.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.quadroidev.medic.core.components.base.BaseFragment
import com.quadroidev.medic.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate, LoginViewModel::class
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            viewModel.createUser(binding.tvUserName.text.toString())
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
        }
        observeLoginEvents()
    }

    private fun observeLoginEvents() {
        launchWhen({
            viewModel.homeEventChanel.collect { events ->
                when (events) {
                    is LoginViewModel.LoginEvents.UserLoggedInSuccessfully -> {
                        Snackbar.make(requireView(), events.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }, Lifecycle.State.CREATED)
    }
}