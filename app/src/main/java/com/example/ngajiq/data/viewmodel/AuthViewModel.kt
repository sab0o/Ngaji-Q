package com.example.ngajiq.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val userLiveData = MutableLiveData<FirebaseUser?>()
    val toastMessage = MutableLiveData<String?>() // For errors/success messages

    fun login(email: String, pass: String) {
        if(email.isBlank() || pass.isBlank()) {
            toastMessage.value = "Please fill in all fields"
            return
        }
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userLiveData.postValue(auth.currentUser)
                } else {
                    toastMessage.postValue("Login Failed: ${task.exception?.message}")
                }
            }
    }

    fun register(email: String, pass: String) {
        if(email.isBlank() || pass.isBlank()) {
            toastMessage.value = "Please fill in all fields"
            return
        }
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userLiveData.postValue(auth.currentUser)
                    toastMessage.postValue("Registration Success!")
                } else {
                    toastMessage.postValue("Register Failed: ${task.exception?.message}")
                }
            }
    }

    // Reset toast message after showing it
    fun clearToast() {
        toastMessage.value = null
    }
}