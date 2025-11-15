package com.creator.spotly.data.repository

import com.creator.spotly.data.dto.UserEditProfileData
import com.creator.spotly.data.dto.UserHomeData
import com.creator.spotly.data.dto.UserProfileData
import com.creator.spotly.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {

    private val usersCollection = firestore.collection("users")

    override fun currentUserHomeDataFlow(): Flow<UserHomeData?> = callbackFlow {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            trySendBlocking(null)
            close()
            return@callbackFlow
        }

        val docRef = usersCollection.document(uid)
        val listener = docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySendBlocking(null)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                val name = snapshot.getString("name")
                val dto = UserHomeData(uid = uid, name = name, avatar = null)
                trySendBlocking(dto)
            } else {
                trySendBlocking(null)
            }
        }

        awaitClose { listener.remove() }
    }

    override fun currentUserProfileFlow(): Flow<UserProfileData?> = callbackFlow {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            trySendBlocking(null)
            close()
            return@callbackFlow
        }

        val docRef = usersCollection.document(uid)
        val listener = docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySendBlocking(null)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val name = snapshot.getString("name")
                val email = snapshot.getString("email")
                val rewardPoints = snapshot.getLong("rewardPoints")?.toInt() ?: 0
                val travelTrips = snapshot.getLong("travelTrips")?.toInt() ?: 0
                val bucketListItems = snapshot.getLong("bucketListItems")?.toInt() ?: 0

                val dto = UserProfileData(
                    uid = uid,
                    name = name,
                    email = email,
                    avatar = null,
                    rewardPoints = rewardPoints,
                    travelTrips = travelTrips,
                    bucketListItems = bucketListItems
                )
                trySendBlocking(dto)
            } else {
                trySendBlocking(null)
            }
        }

        awaitClose { listener.remove() }
    }

    override suspend fun updateUserProfile(uid: String, profile: UserEditProfileData) {
        val userUid = uid.ifEmpty { auth.currentUser?.uid }
            ?: throw Exception("User not logged in")

        usersCollection.document(userUid)
            .update("name", profile.name)
            .await()
    }


    override fun getCurrentUid(): String? = auth.currentUser?.uid
}
