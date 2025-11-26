package com.example.ngajiq.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import androidx.lifecycle.MutableLiveData

class AuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val userLiveData: MutableLiveData<FirebaseUser?> = MutableLiveData()
    private val loggedOutLiveData: MutableLiveData<Boolean> = MutableLiveData()

    // Get Current User on start
    init {
        if (firebaseAuth.currentUser != null) {
            userLiveData.postValue(firebaseAuth.currentUser)
        }
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser?> = userLiveData
    fun getLoggedOutLiveData(): MutableLiveData<Boolean> = loggedOutLiveData

    fun login(email: String, pass: String) {
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    // Handle failure (you might want another LiveData for errors)
                    userLiveData.postValue(null)
                }
            }
    }

    fun register(email: String, pass: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    userLiveData.postValue(null)
                }
            }
    }

    fun signOut() {
        firebaseAuth.signOut()
        loggedOutLiveData.postValue(true)
    }
}