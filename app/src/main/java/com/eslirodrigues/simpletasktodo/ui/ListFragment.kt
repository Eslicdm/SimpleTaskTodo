package com.eslirodrigues.simpletasktodo.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.adapter.TodoAdapter
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.databinding.FragmentListBinding
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val todoViewModel: TodoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        initRecyclerView()
        insertTodo()
        deleteTodos()

        return binding.root
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerViewList.apply {
                val todoAdapter = TodoAdapter(true, todoViewModel)
                adapter = todoAdapter
                layoutManager = LinearLayoutManager(context)
                todoViewModel.readAllData.observe(viewLifecycleOwner) { todo ->
                    todoAdapter.submitList(todo)
                }
            }
        }
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

                Toast.makeText(requireContext(), R.string.added, Toast.LENGTH_SHORT).show()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun deleteTodos() {
        todoViewModel.readCheckboxDataStore.observe(viewLifecycleOwner) { isChecked ->
            if (isChecked) {
                todoViewModel.deleteAllTodos()
            } else {
                binding.buttonDeleteList.setOnClickListener {
                    findNavController().navigate(R.id.action_listFragment_to_deleteConfirmationFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}