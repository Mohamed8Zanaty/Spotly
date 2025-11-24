package com.creator.spotly.auth.repository

interface AuthRepository {
    suspend fun signUp(name: String, email: String, password: String): Result<String>
    suspend fun login(email: String, password: String): Result<String>
    fun logOut()
    fun currentUserId(): String?
    suspend fun addFavoritePlace(uid: String, placeId: String): Result<Unit>
    suspend fun removeFavoritePlace(uid: String, placeId: String): Result<Unit>

}