package com.example.finalocanto.data

import com.example.finalocanto.domain.Target
import com.example.finalocanto.ui.target.TargetMVVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class TargetRepo: TargetMVVM.Model{

    var collection: CollectionReference? = null

    override fun saveTargetDone(target: String, years: String) {
        if (collection == null){
            val id = FirebaseAuth.getInstance().uid
            collection = FirebaseFirestore.getInstance().collection("User/$id/Targets")
        }
        collection!!.document(System.currentTimeMillis().toString()).set(Target(target, years))
    }
}