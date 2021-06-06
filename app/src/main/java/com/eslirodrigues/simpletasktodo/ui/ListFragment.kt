package com.eslirodrigues.simpletasktodo.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.adapter.TodoAdapter
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.databinding.FragmentListBinding
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel
import com.google.android.material.snackbar.Snackbar


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoAdapter = TodoAdapter(true, todoViewModel)

        binding.recyclerViewList.adapter = todoAdapter
        binding.recyclerViewList.layoutManager = LinearLayoutManager(context)

        todoViewModel.readAllData.observe(viewLifecycleOwner) { todo ->
            todoAdapter.setData(todo)
        }

        insertTodo()
        deleteTodos()

        return binding.root
    }

    private fun insertTodo() {
        binding.editTextTaskList.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputText = binding.editTextTaskList.text.toString()
                if (inputText.isNotEmpty()) {
                    val todo = Todo(todo = inputText)
                    todoViewModel.addTodo(todo)
                    binding.editTextTaskList.text.clear()
                }

                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun deleteTodos() {
        binding.buttonDeleteList.setOnClickListener {
            val snackBar = Snackbar.make(it, R.string.todo_deleted, Snackbar.LENGTH_LONG)
            snackBar.setAction(R.string.undo) {

            }
            snackBar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    if (event == DISMISS_EVENT_TIMEOUT) {
                        todoViewModel.deleteAllTodos()
                    }
                }
            })
            snackBar.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}