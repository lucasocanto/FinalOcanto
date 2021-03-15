package com.example.finalocanto.ui.target

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.finalocanto.R
import com.example.finalocanto.databinding.FragmentTargetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TargetFragment : Fragment(), TargetMVVM.View {

    private val viewModel: TargetViewModel by viewModels()

    private var _binding: FragmentTargetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTargetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTargetDone.setOnClickListener { submitTargetDone() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun submitTargetDone() {
        if (binding.editTextTarget.text.isNotBlank() && binding.editTextYearsTarget.text.isNotBlank()){
            saveTarget()
            updateUI()
        }
        else showToast(R.string.text_missing)
    }

    override fun saveTarget() {
        viewModel.setTargetDone(binding.editTextTarget.text.toString(), binding.editTextYearsTarget.text.toString())
    }

    override fun updateUI() {
        binding.editTextTarget.text.clear()
        binding.editTextYearsTarget.text.clear()
        binding.editTextTarget.hint = getString(R.string.target_avchived)
        binding.editTextYearsTarget.hint = getString(R.string.extra_info)
    }

    override fun showToast(textResource: Int) {
        Toast.makeText(requireContext(), getString(textResource), Toast.LENGTH_SHORT).show()
    }
}