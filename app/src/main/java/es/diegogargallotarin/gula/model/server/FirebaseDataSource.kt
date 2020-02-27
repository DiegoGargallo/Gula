package es.diegogargallotarin.gula.model.server

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import es.diegogargallotarin.data.source.RemoteDataSource
import es.diegogargallotarin.domain.Dish
import es.diegogargallotarin.gula.model.server.entity.Dish as ServerDish
import es.diegogargallotarin.gula.model.toDomainDish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseDataSource : RemoteDataSource {

    private var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun getAllDishes(): List<Dish> = withContext(Dispatchers.IO) {
        val task: Task<QuerySnapshot> = fireStore.collection("dishes").get()

        val dishes: MutableList<ServerDish> = mutableListOf()
        for (document in Tasks.await(task).documents) {
            dishes.add(document.toObject(ServerDish::class.java)!!)
        }
        dishes.map { it.toDomainDish() }
    }
}