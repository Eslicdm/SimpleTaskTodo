package com.eslirodrigues.simpletasktodo.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.eslirodrigues.simpletasktodo.adapter.TodoAdapter
import com.eslirodrigues.simpletasktodo.databinding.FragmentListBinding
import com.eslirodrigues.simpletasktodo.model.Todo


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var edit: EditText

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
        binding.editTextTaskList.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputText = binding.editTextTaskList.text.toString()
                if (inputText.isNotEmpty()) {
                    val task = Todo(inputText)
                    todoAdapter.addTodo(task)
                    binding.editTextTaskList.text.clear()
                }

                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
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