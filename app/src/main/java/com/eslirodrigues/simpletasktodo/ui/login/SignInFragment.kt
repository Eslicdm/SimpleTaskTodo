package com.eslirodrigues.simpletasktodo.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        openForgotPassword()
        closeActivityOnBackPressed()
        openSignUp()
        logIn()

        return binding.root
    }

    private fun closeActivityOnBackPressed() {
        binding.root.isFocusableInTouchMode = true
        binding.root.requestFocus()
        binding.root.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                activity?.finish()
                true
            } else {
                false
            }
        }
        binding.root.isFocusableInTouchMode = false
    }

    private fun openSignUp() {
        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun openForgotPassword() {
        binding.textViewForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
        }
    }

    private fun baseLogin() {
        when {
            TextUtils.isEmpty(
                binding.textInputEditTextEmail.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    requireContext(),
                    "Please Enter Email",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TextUtils.isEmpty(
                binding.textInputEditTextPassword.text.toString()
                    .trim { it <= ' ' }) -> {
                Toast.makeText(
                    requireContext(),
                    "Please Enter Password",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                val email: String =
                    binding.textInputEditTextEmail.text.toString().trim { it <= ' ' }
                val password: String =
                    binding.textInputEditTextPassword.text.toString().trim { it <= ' ' }

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
//                                val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                requireContext(),
                                "You were logged in successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            findNavController().navigate(R.id.action_signInFragment_to_listFragment)

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

    private fun logIn() {
        binding.textInputEditTextPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                baseLogin()
            }
            return@setOnEditorActionListener true
        }
        binding.buttonLogin.setOnClickListener {
            baseLogin()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}