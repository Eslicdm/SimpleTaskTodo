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
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeleteConfirmationFragment : DialogFragment() {

    private var _binding: FragmentDeleteConfirmationDialogBinding? = null
    private val binding get() = _binding!!

    private val todoViewModel: TodoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteConfirmationDialogBinding.inflate(inflater, container, false)

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