package com.quadroidev.medic.presentation.features.habit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.quadroidev.medic.R
import com.quadroidev.medic.core.components.base.BaseFragment
import com.quadroidev.medic.databinding.FragmentHabitBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class HabitFragment : BaseFragment<FragmentHabitBinding, HabitViewModel>(
    FragmentHabitBinding::inflate, HabitViewModel::class
), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private val categoryList = listOf("آمپول", "کپسول", "قطره", "قرص", "اسپری")
    private val currentDate = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
    private val timeFormatter = SimpleDateFormat("HH:mm a", Locale.US)
    private val startTime = currentDate.timeInMillis
    private var count = 0
    private var categoryName = ""
    private var categoryImage = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dropDownMenu = binding.dropDownMenu
        val adapter = ArrayAdapter(requireContext(), R.layout.category_view, categoryList)
        dropDownMenu.setAdapter(adapter)
        dropDownMenu.setOnItemClickListener { adapterView, _, position, _ ->
            categoryName = adapterView.getItemAtPosition(position).toString()
            when (categoryName) {
                "آمپول" -> {
                    binding.categoryImageView.setImageResource(R.drawable.ic_syringe)
                    categoryImage = R.drawable.ic_syringe
                }

                "کپسول" -> {
                    binding.categoryImageView.setImageResource(R.drawable.ic_capsule)
                    categoryImage = R.drawable.ic_capsule
                }

                "قطره" -> {
                    binding.categoryImageView.setImageResource(R.drawable.ic_dropper)
                    categoryImage = R.drawable.ic_dropper
                }

                "قرص" -> {
                    binding.categoryImageView.setImageResource(R.drawable.ic_pills)
                    categoryImage = R.drawable.ic_pills
                }

                "اسپری" -> {
                    binding.categoryImageView.setImageResource(R.drawable.ic_spray)
                    categoryImage = R.drawable.ic_spray
                }

            }
        }

        binding.timeCardView.setOnClickListener {
            TimePickerDialog(
                requireContext(),
                this,
                currentDate.get(Calendar.HOUR_OF_DAY),
                currentDate.get(Calendar.MINUTE),
                false
            ).show()
        }

        binding.dateCardView.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                this,
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.submitBtn.setOnClickListener {
            val drugName = binding.drugNameEditText.text.toString()
            count = binding.countEditText.text.toString().toInt()
            viewModel.addHabit(
                name = drugName,
                startTime = startTime,
                count = count,
                categoryName = categoryName,
                categoryImage = categoryImage
            )
        }

        submitReminder()

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        currentDate.set(year, month, dayOfMonth)
        displayFormattedDate(currentDate.timeInMillis)
    }

    private fun displayFormattedDate(timeStamp: Long) {
        binding.dateTextView.text = dateFormatter.format(timeStamp)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        currentDate.apply {
            set(Calendar.HOUR, hourOfDay)
            set(Calendar.MINUTE, minute)
        }
        displayFormattedTime(currentDate.timeInMillis)
    }

    private fun displayFormattedTime(timeStamp: Long) {
        binding.timeTextView.text = timeFormatter.format(timeStamp)
    }

    private fun submitReminder() {
        launchWhen({
            viewModel.eventChannel.collect {event ->
                when(event) {
                    is HabitViewModel.HabitEvents.HabitCreatedSuccessfully -> {
                        findNavController().navigate(R.id.action_habitFragment_to_mainFragment)
                    }
                }
            }
        }, Lifecycle.State.CREATED)
    }
}