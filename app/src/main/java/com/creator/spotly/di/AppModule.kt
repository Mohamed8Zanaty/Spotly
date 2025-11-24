package com.creator.spotly.di

import com.creator.spotly.api.geoapify.util.ApiKeyInterceptor
import com.creator.spotly.api.geoapify.service.GeoapifyService
import com.creator.spotly.auth.repository.AuthRepositoryImpl
import com.creator.spotly.auth.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val GEO_BASE_URL = "https://api.geoapify.com/"

    // Geoapify API
    @Provides
    @Singleton
    fun provideApiKey(): String = "f63f812072a74589a7e8b052a2952f51"

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(apiKey: String): ApiKeyInterceptor = ApiKeyInterceptor(apiKey)

    @Provides
    @Singleton
    @Named("geo")
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(logging)
            .callTimeout(2, TimeUnit.SECONDS)
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideGeoapifyService(@Named("geo") ok: OkHttpClient, moshi: Moshi): GeoapifyService {
        val retrofit = Retrofit.Builder()
            .baseUrl(GEO_BASE_URL)
            .client(ok)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(GeoapifyService::class.java)
    }

    // Firebase
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(auth, firestore)

}
