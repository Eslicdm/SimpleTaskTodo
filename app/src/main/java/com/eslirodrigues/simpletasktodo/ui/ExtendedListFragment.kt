package com.eslirodrigues.simpletasktodo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.eslirodrigues.simpletasktodo.databinding.FragmentExtendedListBinding

class ExtendedListFragment : Fragment(){

    private var _binding: FragmentExtendedListBinding? = null
    private val binding get() = _binding!!

    private val args: ExtendedListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExtendedListBinding.inflate(inflater, container, false)

        val title = args.title

        binding.textViewItemTitle.text = title

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}