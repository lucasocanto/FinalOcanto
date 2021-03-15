package com.example.finalocanto.ui.target

import kotlinx.coroutines.Job

interface TargetMVVM {
    interface Model{
        fun saveTargetDone(target: String, years: String)
    }
    interface View{
        fun submitTargetDone()
        fun saveTarget()
        fun updateUI()
        fun showToast(textResource: Int)
    }
    interface ViewModel{
        fun setTargetDone(target: String, years: String): Job
    }
}