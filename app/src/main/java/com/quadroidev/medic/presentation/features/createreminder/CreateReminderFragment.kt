package com.quadroidev.medic.presentation.features.createreminder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.quadroidev.medic.R
import com.quadroidev.medic.core.components.base.BaseFragment
import com.quadroidev.medic.databinding.FragmentCreateReminderBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class CreateReminderFragment : BaseFragment<FragmentCreateReminderBinding, CreateReminderViewModel>(
    FragmentCreateReminderBinding::inflate, CreateReminderViewModel::class
), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private val categoryList = listOf("آمپول", "کپسول", "قطره", "قرص", "اسپری")
    private val currentDate = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
    private val timeFormatter = SimpleDateFormat("HH:mm a", Locale.US)
    private val startTime = currentDate.timeInMillis
    private var count = 0
    private var categoryName = ""
    private var drugName = ""
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
                    binding.categoryImageView.setImageResource(R.drawable.syringe)
                    categoryImage = R.drawable.syringe
                }

                "کپسول" -> {
                    binding.categoryImageView.setImageResource(R.drawable.capsule)
                    categoryImage = R.drawable.capsule
                }

                "قطره" -> {
                    binding.categoryImageView.setImageResource(R.drawable.drop)
                    categoryImage = R.drawable.drop
                }

                "قرص" -> {
                    binding.categoryImageView.setImageResource(R.drawable.pills)
                    categoryImage = R.drawable.pills
                }

                "اسپری" -> {
                    binding.categoryImageView.setImageResource(R.drawable.spray)
                    categoryImage = R.drawable.spray
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
            drugName = binding.drugNameEditText.text.toString()
            count = binding.countEditText.text.toString().toInt()
            viewModel.submitButtonClicked(
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
        Log.e("Calendar", "$year, $month, $dayOfMonth")
        currentDate.set(year, month, dayOfMonth)
        displayFormattedDate(currentDate.timeInMillis)
    }

    private fun displayFormattedDate(timeStamp: Long) {
        binding.dateTextView.text = dateFormatter.format(timeStamp)
        Log.i("Formatting", timeStamp.toString())
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
        Log.i("Formatting", timeStamp.toString())
    }

    private fun submitReminder() {
        launchWhen({
            viewModel.eventChannel.collect {event ->
                when(event) {
                    is CreateReminderViewModel.SubmitEvents.SubmitSuccessfully -> {
                        findNavController().navigate(R.id.action_createReminderFragment_to_mainFragment)
                    }
                }
            }
        }, Lifecycle.State.CREATED)
    }
}