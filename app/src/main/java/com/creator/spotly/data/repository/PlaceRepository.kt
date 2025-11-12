package com.creator.spotly.data.repository

import com.creator.spotly.domain.model.Place
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage

class PlaceRepository {
    private val db = Firebase.firestore
    private val placesRef = db.collection("places")
    private val storageRef = FirebaseStorage.getInstance().reference

    fun addPlace(place: Place, onComplete : (Boolean, String?) -> Unit)
        = createPlaceDocument(place, onComplete)
    private fun createPlaceDocument(place: Place, onComplete : (Boolean, String?) -> Unit) {
        val docRef = placesRef.document()
        place.id = docRef.id
        docRef.set(place)
            .addOnSuccessListener {
                onComplete(true, docRef.id)
            }
            .addOnFailureListener { e ->
                onComplete(false, e.message)
            }
    }

    fun getPlace(placeId: String, onComplete: (Place?) -> Unit) {
        placesRef.document(placeId)
            .get()
            .addOnSuccessListener { snap -> onComplete(snap.toObject(Place::class.java)) }
            .addOnFailureListener { onComplete(null) }
    }

    fun getPlaces(onComplete: (List<Place>) -> Unit) {
        placesRef.get()
            .addOnSuccessListener { q -> onComplete(q.documents.mapNotNull { it.toObject(Place::class.java) }) }
            .addOnFailureListener { onComplete(emptyList()) }

    }
    fun listenAllPlaces(onUpdate: (List<Place>) -> Unit): ListenerRegistration {
        return placesRef.addSnapshotListener { snaps, e ->
            if (e != null) {
                onUpdate(emptyList())
                return@addSnapshotListener
            }
            onUpdate(snaps?.documents?.mapNotNull { it.toObject(Place::class.java) } ?: emptyList())
        }
    }

    fun updatePlace(id: String, updates: Map<String, Any>, onComplete: (Boolean, String?) -> Unit) {
        placesRef.document(id).update(updates)
            .addOnSuccessListener { onComplete(true, null) }
            .addOnFailureListener { e -> onComplete(false, e.message) }
    }

    fun deletePlace(id: String, onComplete: (Boolean, String?) -> Unit) {
        placesRef.document(id).delete()
            .addOnSuccessListener { onComplete(true, null) }
            .addOnFailureListener { e -> onComplete(false, e.message) }
    }
}