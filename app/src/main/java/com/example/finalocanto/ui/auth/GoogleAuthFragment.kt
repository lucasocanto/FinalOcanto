package com.example.finalocanto.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.finalocanto.R
import com.example.finalocanto.databinding.FragmentGoogleAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoogleAuthFragment : Fragment(),
    GoogleAuthMVVM.View {

    companion object{ private const val RC_SIGN_IN = 9001 }

    private val viewModel: GoogleAuthViewModel by viewModels()

    private lateinit var gso: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient

    private var _binding: FragmentGoogleAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGoogleAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkForLoggedUser()
        initializeGoogleSignIn()

        binding.signInButton.setOnClickListener {
            startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
            binding.replaceFragmentButton.setOnClickListener { checkAuthTaskResult() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun checkForLoggedUser() {
        viewModel.checkUserConnection()
        viewModel.userConnection.observe(requireActivity(), Observer { replaceFragment(it) })
    }

    override fun initializeGoogleSignIn() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK)
            viewModel.launchSignIn(gso, GoogleSignIn.getSignedInAccountFromIntent(data))
    }

    override fun checkAuthTaskResult() {
        viewModel.getAuthTaskResult()
        viewModel.authTaskResult.observe(requireActivity(), Observer { replaceFragment(it)})
    }

    override fun replaceFragment(result: Boolean) {
        if (result) findNavController().navigate(R.id.action_googleAuthFragment_to_targetFragment)
    }

    override fun showToast(textResource: Int) {
        Toast.makeText(requireContext(), getString(textResource), Toast.LENGTH_SHORT).show()
    }
}

//holis