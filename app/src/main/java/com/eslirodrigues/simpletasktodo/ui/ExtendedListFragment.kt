package com.eslirodrigues.simpletasktodo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.eslirodrigues.simpletasktodo.adapter.TodoAdapter
import com.eslirodrigues.simpletasktodo.databinding.FragmentExtendedListBinding
import com.eslirodrigues.simpletasktodo.model.Todo

class ExtendedListFragment : Fragment(){

    private var _binding: FragmentExtendedListBinding? = null
    private val binding get() = _binding!!

    private val args: ExtendedListFragmentArgs by navArgs()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExtendedListBinding.inflate(inflater, container, false)

        val title = args.title

        binding.textViewItemTitle.text = title

        todoAdapter = TodoAdapter(mutableListOf())

        binding.recyclerViewExtendedList.adapter = todoAdapter
        binding.recyclerViewExtendedList.layoutManager = LinearLayoutManager(context)

        addItemClickListener()
        deleteItemClickListener()

        return binding.root
    }

    private fun addItemClickListener() {
        binding.buttonAddExtended.setOnClickListener {
            val inputText = binding.editTextTaskExtended.text.toString()
            if (inputText.isNotEmpty()) {
                val task = Todo(inputText)
                todoAdapter.addTodo(task)
                binding.editTextTaskExtended.text.clear()
            }
        }
    }

    private fun deleteItemClickListener() {
        binding.buttonDeleteExtended.setOnClickListener {
            todoAdapter.deleteDoneTodo()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}