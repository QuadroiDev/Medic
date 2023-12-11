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
    private val adapter: HabitAdapter by lazy { HabitAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addReminder.setOnClickListener {
            viewModel.createEvent()
            observeEvents()
        }

        setAdapter()
        observeEvents()
    }

    private fun setAdapter() {
        binding.reminderRecyclerview.adapter = adapter
        binding.reminderRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeEvents() {
        viewModel.getAllHabits()
        launchWhen({
            viewModel.eventChannel.collect {events ->
                when(events) {
                    is MainViewModel.AddHabitEvents.AddHabitSuccessfully -> {
                        findNavController().navigate(R.id.action_mainFragment_to_habitFragment)
                    }
                    is MainViewModel.AddHabitEvents.FetchAllHabitsEvent -> {
                        viewModel.habits.collect {habits ->
                            adapter.submitList(habits)
                        }
                    }
                }

            }
        }, Lifecycle.State.CREATED)
    }
}