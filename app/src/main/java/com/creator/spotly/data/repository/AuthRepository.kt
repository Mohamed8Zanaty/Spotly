package com.creator.spotly.data.repository

import com.creator.spotly.domain.model.User

interface AuthRepository {
    suspend fun signUp(name: String, email: String, password: String): Result<String>
    suspend fun login(email: String, password: String): Result<String>
    fun logOut()
    fun currentUserId(): String?

    suspend fun getUser(uid: String): Result<User>
    suspend fun addFavoritePlace(uid: String, placeId: String): Result<Unit>
    suspend fun removeFavoritePlace(uid: String, placeId: String): Result<Unit>
}