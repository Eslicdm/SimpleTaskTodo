package com.eslirodrigues.simpletasktodo.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.textViewLogIn.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding.buttonLogin.setOnClickListener {
            when {
                TextUtils.isEmpty(
                    binding.textInputEditTextEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please Enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(
                    binding.textInputEditTextPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please Enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String =
                        binding.textInputEditTextEmail.text.toString().trim { it <= ' ' }
                    val password: String =
                        binding.textInputEditTextPassword.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
//                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                Toast.makeText(
                                    requireContext(),
                                    "You were registered successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                findNavController().navigate(R.id.action_signUpFragment_to_listFragment)

                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}