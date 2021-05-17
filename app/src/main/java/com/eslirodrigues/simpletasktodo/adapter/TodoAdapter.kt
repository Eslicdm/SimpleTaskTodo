package com.eslirodrigues.simpletasktodo.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.databinding.TodoListBinding
import com.eslirodrigues.simpletasktodo.ui.ListFragmentDirections

class TodoAdapter(private val newListVisibility: Boolean) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todos = emptyList<Todo>()

    class TodoViewHolder(val binding: TodoListBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTask = todos[position]
        holder.binding.apply {
            if (newListVisibility) {
                imageViewExtendedItem.setOnClickListener {
                    val title = curTask.todo
                    val action =
                        ListFragmentDirections.actionListFragmentToExtendedListFragment(title)
                    Navigation.findNavController(it).navigate(action)
                }
            } else {
                imageViewExtendedItem.visibility = View.INVISIBLE
            }

            textViewItem.text = curTask.todo

            checkboxItem.isChecked = curTask.isChecked
            todoStrikeThrough(textViewItem, curTask.isChecked)
            checkboxItem.setOnCheckedChangeListener { _, isChecked ->
                todoStrikeThrough(textViewItem, isChecked)
                curTask.isChecked = !curTask.isChecked

            }

            textViewItem.setOnClickListener {
                Navigation.findNavController(it).navigate(ListFragmentDirections.actionListFragmentToUpdateDialogFragment(curTask))
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    private fun todoStrikeThrough(taskText: TextView, isChecked: Boolean) {
        if (isChecked) {
            taskText.paintFlags = taskText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            taskText.paintFlags = taskText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun setData(todo: List<Todo>) {
        this.todos = todo
        notifyDataSetChanged()
    }

}