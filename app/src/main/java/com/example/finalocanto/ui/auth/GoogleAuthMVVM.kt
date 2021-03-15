package com.example.finalocanto.ui.auth

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Job

interface GoogleAuthMVVM {
    interface Model{
        fun googleSingIn(gso: GoogleSignInOptions, signedInAccount: Task<GoogleSignInAccount>)
        fun firebaseAuth(result: String?)
    }
    interface View{
        fun checkForLoggedUser()
        fun initializeGoogleSignIn()
        fun checkAuthTaskResult()
        fun replaceFragment()
        fun showToast(textResource: Int)
    }
    interface ViewModel{
        fun checkUserConnection() : Job
        fun launchSignIn(gso: GoogleSignInOptions, signedInAccount: Task<GoogleSignInAccount>) : Job
        fun getAuthTaskResult() : Job
    }
}