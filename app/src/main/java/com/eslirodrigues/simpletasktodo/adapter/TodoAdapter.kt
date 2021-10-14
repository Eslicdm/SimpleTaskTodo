package com.eslirodrigues.simpletasktodo.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eslirodrigues.simpletasktodo.adapter.TodoAdapter.*
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.databinding.TodoListBinding
import com.eslirodrigues.simpletasktodo.ui.ListFragmentDirections
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel

class TodoAdapter(
    private val viewModel: TodoViewModel
) : ListAdapter<Todo, TodoViewHolder>(DIFF_CALLBACK) {

    class TodoViewHolder(val binding: TodoListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTask = getItem(position)
        holder.binding.apply {
            textViewItem.text = curTask.todo

            checkboxItem.isChecked = curTask.isChecked
            todoStrikeThrough(textViewItem, curTask.isChecked)
            checkboxItem.setOnCheckedChangeListener { _, isChecked ->
                todoStrikeThrough(textViewItem, isChecked)
                curTask.isChecked = !curTask.isChecked
                viewModel.updateTodo(curTask)
            }

            textViewItem.setOnClickListener {
                Navigation.findNavController(it).navigate(ListFragmentDirections.actionListFragmentToUpdateDialogFragment(curTask))
            }
        }
    }

    private fun todoStrikeThrough(taskText: TextView, isChecked: Boolean) {
        if (isChecked) {
            taskText.paintFlags = taskText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            taskText.paintFlags = taskText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(
                oldItem: Todo,
                newItem: Todo
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Todo,
                newItem: Todo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}