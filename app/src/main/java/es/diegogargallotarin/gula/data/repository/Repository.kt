package es.diegogargallotarin.gula.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import es.diegogargallotarin.gula.data.entity.Dish
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


class Repository {

    private var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun getDishes(): MutableList<Dish> = suspendCancellableCoroutine { continuation ->
        val dishes = mutableListOf<Dish>()
        fireStore.collection("users").get().addOnCompleteListener {
            if (it.isSuccessful && it.result != null) {
                for (document in it.result!!.documents) {
                    dishes.add(document.toObject(Dish::class.java)!!)
                }
                continuation.resume(dishes)
            }
        }
    }

}
