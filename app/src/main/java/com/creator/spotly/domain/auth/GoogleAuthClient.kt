package com.creator.spotly.domain.auth

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthClient(
    private val context: Context,
) {

  private val tag = "GoogleSingInClient:"
  private val credentialManager = CredentialManager.create(context)
  private val firebaseAuth = FirebaseAuth.getInstance()

  fun isSignedIn(): Boolean {
    if (firebaseAuth.currentUser != null) {
      println("$tag: Already signed in")
      return true
    }
    return false
  }

  suspend fun signIn(): Boolean {
    if (isSignedIn()) {
      return true
    } else {
      try {
        val response = buildCredentailRequast()

        return handleSignIn(response)
      } catch (e: Exception) {
        e.printStackTrace()
        if (e is CancellationException) throw e
        println("$tag: ${e.message}")
        return false
      }
    }
    return false
  }

  private suspend fun handleSignIn(response: GetCredentialResponse): Boolean {
    val credential = response.credential

    if (
        credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
    ) {
      try {
        val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
        println("$tag: name: ${tokenCredential.displayName}")
        println("$tag: Phone: ${tokenCredential.phoneNumber}")
        println("$tag: PhotoUrl:  ${tokenCredential.profilePictureUri}")

        val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken, null)
        val authResult = firebaseAuth.signInWithCredential(authCredential).await()
        return authResult.user != null
      } catch (e: GoogleIdTokenParsingException) {
        println("$tag GoogleIdTokenParsingException: ${e.message}")
        return false
      }
    } else {
      println("$tag: ${credential.type}")
      return false
    }
  }

  private suspend fun buildCredentailRequast(): GetCredentialResponse {
    val getCredentialRequest =
        GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(
                        "1059548853853-sg15aqlhnbp9s3dqnsmogoi5sbg1ethm.apps.googleusercontent.com"
                    )
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()
    return credentialManager.getCredential(request = getCredentialRequest, context = context)
  }

  suspend fun signOut() {
    credentialManager.clearCredentialState(
        ClearCredentialStateRequest(),
    )
    firebaseAuth.signOut()
  }

  fun getUserName(): String = firebaseAuth.currentUser?.displayName.toString()

  fun getUserEmail(): String = firebaseAuth.currentUser?.email.toString()

  fun getUserProfileUrl(): String = firebaseAuth.currentUser?.photoUrl.toString()

  fun getUserPhoneNumber(): String = firebaseAuth.currentUser?.phoneNumber.toString()
}
