package com.eslirodrigues.simpletasktodo.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.databinding.TodoListBinding
import com.eslirodrigues.simpletasktodo.model.Todo

class TodoAdapter(private val todos: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(val binding: TodoListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemChanged(todos.size - 1)
    }

    fun deleteDoneTodo() {
        todos.removeAll {
            it.isChecked
        }
        notifyDataSetChanged()
    }

    private fun todoStrikeThrough(taskText: TextView, isChecked: Boolean) {
        if (isChecked) {
            taskText.paintFlags = taskText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            taskText.paintFlags = taskText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTask = todos[position]
        holder.binding.apply {
            imageViewNewListItem.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_listFragment_to_extendedListFragment)
            }
            textViewItem.text = curTask.todo
            checkboxItem.isChecked = curTask.isChecked
            todoStrikeThrough(textViewItem, curTask.isChecked)
            checkboxItem.setOnCheckedChangeListener { _, isChecked ->
                todoStrikeThrough(textViewItem, isChecked)
                curTask.isChecked = !curTask.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

}