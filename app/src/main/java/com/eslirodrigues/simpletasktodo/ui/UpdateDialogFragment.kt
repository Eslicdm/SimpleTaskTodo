package com.eslirodrigues.simpletasktodo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.databinding.FragmentUpdateDialogBinding
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateDialogFragment : DialogFragment() {

    private var _binding: FragmentUpdateDialogBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateDialogFragmentArgs by navArgs()

    private val todoViewModel: TodoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateDialogBinding.inflate(inflater, container, false)

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
            Toast.makeText(requireContext(), R.string.updated, Toast.LENGTH_SHORT).show()
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