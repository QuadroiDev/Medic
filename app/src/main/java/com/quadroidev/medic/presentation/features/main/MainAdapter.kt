package com.quadroidev.medic.presentation.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quadroidev.medic.core.model.Habit
import com.quadroidev.medic.databinding.ReminderLayoutBinding

class MainAdapter : ListAdapter<Habit, MainAdapter.MyViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Habit>() {
            override fun areItemsTheSame(
                oldItem: Habit,
                newItem: Habit
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: Habit,
                newItem: Habit
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MyViewHolder(private val binding: ReminderLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: Habit) {
            with(binding) {
                reminderCategoryImageView.setImageResource(habit.category.image)
                reminderCategoryTextView.text = habit.category.categoryName
                reminderDrugNameTextView.text = habit.name
                drugCountTextView.text = "${habit.count} pill(s) left"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ReminderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val habitItem = getItem(position)
        holder.bind(habitItem)
    }
}