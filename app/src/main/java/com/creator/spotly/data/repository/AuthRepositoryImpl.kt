package com.creator.spotly.data.repository

import com.creator.spotly.data.dto.UserHomeData
import com.creator.spotly.domain.model.User
import com.creator.spotly.domain.repository.AuthRepository
import com.creator.spotly.ui.auth.FieldErrors
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<String> =
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: return Result.failure(Exception("No uid after sign up"))

            val userMap = hashMapOf(
                "uid" to uid,
                "name" to name,
                "email" to email.trim(),
                "created_at" to System.currentTimeMillis(),
                "favoritePlaces" to emptyList<String>(),

            )

            firestore.collection("users").document(uid).set(userMap).await()
            Result.success(uid)
        } catch (e: Exception) {
            try {
                auth.currentUser?.delete()?.await()
            } catch (_: Exception) { }
            Result.failure(e)
        }


    override suspend fun login(
        email: String,
        password: String
    ): Result<String> =
        try {
            val res = auth.signInWithEmailAndPassword(email.trim(), password).await()
            val uid = res.user?.uid ?: return Result.failure(Exception("No uid after login"))
            Result.success(uid)
        } catch (e : Exception) {
            val fieldErrors = when (e) {
                is FirebaseAuthInvalidUserException ->
                    FieldErrors(email = "No account found for this email")
                is FirebaseAuthInvalidCredentialsException ->
                    FieldErrors(password = "Incorrect password")
                else -> FieldErrors(general = e.localizedMessage ?: "Login failed")
            }
            Result.failure(AuthException(fieldErrors, e.localizedMessage, e))
        }


    override fun logOut() {
        auth.signOut()
    }
    override fun currentUserId(): String? = auth.currentUser?.uid
    override suspend fun addFavoritePlace(
        uid: String,
        placeId: String
    ): Result<Unit> =
        try {
            firestore.collection("users").document(uid)
                .update("favoritePlaces", FieldValue.arrayUnion(placeId)).await()
            Result.success(Unit)
        } catch (e : Exception) {
            Result.failure(e)
        }

    override suspend fun removeFavoritePlace(
        uid: String,
        placeId: String
    ): Result<Unit> =
        try {
            firestore.collection("users").document(uid)
                .update("favoritePlaces", FieldValue.arrayRemove(placeId)).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }


}