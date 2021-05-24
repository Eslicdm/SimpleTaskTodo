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
import com.eslirodrigues.simpletasktodo.adapter.TodoAdapter
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.databinding.FragmentListBinding
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel


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
            todoViewModel.deleteAllTodos()
        }
    }

//    private fun deleteItemClickListener() {
//        binding.buttonDeleteList.setOnClickListener {
//            val todos = todoViewModel.readAllData
//
//            val builder = AlertDialog.Builder(requireContext())
//                .setPositiveButton("Yes") { _, _ ->
//                    todoViewModel.de
//                    Toast.makeText(requireContext(), "Successfully removed everything}", Toast.LENGTH_SHORT).show()
//                }
//                .setNegativeButton("No") { _, _ ->
//
//                }
//                .setTitle("Delete everything?")
//                .setMessage("Are you sure you want to delete everything")
//                .create()
//                .show()
//
//        }
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}