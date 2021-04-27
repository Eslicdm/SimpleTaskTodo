package com.eslirodrigues.simpletasktodo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.eslirodrigues.simpletasktodo.adapter.TodoAdapter
import com.eslirodrigues.simpletasktodo.databinding.FragmentListBinding
import com.eslirodrigues.simpletasktodo.model.Todo

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        todoAdapter = TodoAdapter(mutableListOf())

        binding.recyclerViewList.adapter = todoAdapter
        binding.recyclerViewList.layoutManager = LinearLayoutManager(context)

        addItemClickListener()
        deleteItemClickListener()

        return binding.root
    }

    private fun addItemClickListener() {
        binding.buttonAddList.setOnClickListener {
            val inputText = binding.editTextTaskList.text.toString()
            if (inputText.isNotEmpty()) {
                val task = Todo(inputText)
                todoAdapter.addTodo(task)
                binding.editTextTaskList.text.clear()
            }
        }
    }

    private fun deleteItemClickListener() {
        binding.buttonDeleteList.setOnClickListener {
            todoAdapter.deleteDoneTodo()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}