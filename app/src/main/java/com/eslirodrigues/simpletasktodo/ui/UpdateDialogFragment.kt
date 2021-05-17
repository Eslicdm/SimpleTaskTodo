package com.eslirodrigues.simpletasktodo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.databinding.FragmentUpdateDialogBinding
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel
import kotlinx.android.synthetic.*

class UpdateDialogFragment : DialogFragment() {

    private var _binding: FragmentUpdateDialogBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateDialogFragmentArgs by navArgs()

    private lateinit var todoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateDialogBinding.inflate(inflater, container, false)

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        val todo = args.curTodo

        binding.editTextUpdate.setText(todo.todo)

        binding.buttonSaveUpdate.setOnClickListener {
            todoViewModel.updateTodo(
                Todo(
                    id = args.curTodo.id,
                    todo = binding.editTextUpdate.text.toString(),
                    isChecked = args.curTodo.isChecked
                )
            )
            findNavController().navigate(R.id.action_updateDialogFragment_to_listFragment)
        }

        binding.buttonCancelUpdate.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}