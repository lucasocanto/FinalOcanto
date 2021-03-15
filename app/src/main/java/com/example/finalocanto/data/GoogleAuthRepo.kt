package com.example.finalocanto.data

import com.example.finalocanto.ui.auth.GoogleAuthMVVM
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class GoogleAuthRepo : GoogleAuthMVVM.Model{

    val auth = FirebaseAuth.getInstance()
    var result = false

    override fun googleSingIn(gso: GoogleSignInOptions, signedInAccount: Task<GoogleSignInAccount>) {
        try { firebaseAuth(signedInAccount.result?.idToken) }
        catch (e: ApiException){ result = false}
    }

    override fun firebaseAuth(result: String?) {
        val credential = GoogleAuthProvider.getCredential(result, null)
        auth.signInWithCredential(credential).addOnCompleteListener { this.result = it.isSuccessful }
    }
}
