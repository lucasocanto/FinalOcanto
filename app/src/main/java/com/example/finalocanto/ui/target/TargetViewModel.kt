package com.example.finalocanto.ui.target

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalocanto.data.TargetRepo
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class TargetViewModel @ViewModelInject constructor(private val repo: TargetRepo)
    : ViewModel(), TargetMVVM.ViewModel {

    override fun setTargetDone(target: String, years: String) = viewModelScope.launch {
        repo.saveTargetDone(target, years)
    }
}