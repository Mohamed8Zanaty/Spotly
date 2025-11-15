package com.creator.spotly.domain.repository

import com.creator.spotly.data.dto.UserHomeData
import com.creator.spotly.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(name: String, email: String, password: String): Result<String>
    suspend fun login(email: String, password: String): Result<String>
    fun logOut()
    fun currentUserId(): String?
    suspend fun addFavoritePlace(uid: String, placeId: String): Result<Unit>
    suspend fun removeFavoritePlace(uid: String, placeId: String): Result<Unit>


}