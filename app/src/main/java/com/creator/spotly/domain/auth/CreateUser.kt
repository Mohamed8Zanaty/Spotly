package com.creator.spotly.domain.auth

import com.creator.spotly.domain.model.Place
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun createUser(
    name: String,
    email: String,
    password: String,
    favoritePlaces: List<Place> = emptyList(),
    callback: (Boolean, String?) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    auth.createUserWithEmailAndPassword(email.trim(), password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val firebaseUser = auth.currentUser
                val userId = firebaseUser?.uid
                if (userId == null) {
                    callback(false, "User ID is null")
                    return@addOnCompleteListener
                }

                val userDoc = hashMapOf<String, Any?>(
                    "uid" to userId,
                    "name" to name,
                    "email" to email,
                    "favoritePlaces" to favoritePlaces
                )

                firestore.collection("users")
                    .document(userId)
                    .set(userDoc)
                    .addOnSuccessListener {
                        callback(true, null)
                    }
                    .addOnFailureListener { e ->
                        callback(false, e.message ?: "Failed to save user data")
                    }
            }
            else callback(false, task.exception?.message ?: "Authentication failed")
        }
}