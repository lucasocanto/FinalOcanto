package com.example.finalocanto.ui.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalocanto.data.GoogleAuthRepo
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.launch

class GoogleAuthViewModel @ViewModelInject constructor(private val repo: GoogleAuthRepo)
    : ViewModel(), GoogleAuthMVVM.ViewModel {

    private var _userConnection = MutableLiveData<Boolean>().apply { value = false }
    var userConnection: LiveData<Boolean> = _userConnection

    private var _authTaskResult = MutableLiveData<Boolean>().apply { value = false }
    var authTaskResult: LiveData<Boolean> = _authTaskResult

    override fun checkUserConnection() = viewModelScope.launch {
        _userConnection.value = repo.auth.currentUser != null
    }

    override fun launchSignIn(gso: GoogleSignInOptions, signedInAccount: Task<GoogleSignInAccount>)
            = viewModelScope.launch {
        repo.googleSingIn(gso, signedInAccount)
    }

    override fun getAuthTaskResult() = viewModelScope.launch {
        _authTaskResult.value = repo.result
    }
}