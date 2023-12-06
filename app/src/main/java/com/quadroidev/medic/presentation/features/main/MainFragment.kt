package com.quadroidev.medic.presentation.features.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.quadroidev.medic.R
import com.quadroidev.medic.core.components.base.BaseFragment
import com.quadroidev.medic.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(
    FragmentMainBinding::inflate,
    MainViewModel::class
) {

    private val adapter: MainAdapter by lazy { MainAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addReminder.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_createReminderFragment)
        }
        setAdapter()

        observeHabits()
    }

    private fun setAdapter() {
        binding.reminderRecyclerview.adapter = adapter
        binding.reminderRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(emptyList()) // TODO: set data
    }

    private fun observeHabits() {
        viewModel.getAllHabits()
        launchWhen({
            viewModel.habits.collect {habits ->
                adapter.submitList(habits)
            }
        }, Lifecycle.State.CREATED)
    }
}