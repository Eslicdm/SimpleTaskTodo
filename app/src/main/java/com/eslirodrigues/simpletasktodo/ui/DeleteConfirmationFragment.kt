package com.eslirodrigues.simpletasktodo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.databinding.FragmentDeleteConfirmationDialogBinding
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel

class DeleteConfirmationFragment : DialogFragment() {

    private var _binding: FragmentDeleteConfirmationDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteConfirmationDialogBinding.inflate(inflater, container, false)

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)



        binding.buttonConfirmDelete.setOnClickListener {
            todoViewModel.deleteAllTodos()
            findNavController().navigate(R.id.action_deleteConfirmationFragment_to_listFragment)
        }

        binding.buttonCancelDelete.setOnClickListener {
            findNavController().navigate(R.id.action_deleteConfirmationFragment_to_listFragment)
        }

        binding.checkboxAsk.setOnCheckedChangeListener { _, isChecked ->
            todoViewModel.saveCheckbox(isChecked)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}