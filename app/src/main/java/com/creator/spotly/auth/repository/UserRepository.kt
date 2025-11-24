package com.creator.spotly.auth.repository

import com.creator.spotly.data.dto.UserEditProfileData
import com.creator.spotly.data.dto.UserHomeData
import com.creator.spotly.data.dto.UserProfileData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun currentUserHomeDataFlow(): Flow<UserHomeData?>
    fun currentUserProfileFlow(): Flow<UserProfileData?>
    suspend fun updateUserProfile(uid: String, profile: UserEditProfileData)
    fun getCurrentUid(): String?
}