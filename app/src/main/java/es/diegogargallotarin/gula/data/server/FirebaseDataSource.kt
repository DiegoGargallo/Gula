package es.diegogargallotarin.gula.data.server

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import es.diegogargallotarin.data.source.RemoteDataSource
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.gula.data.server.entity.Contribution as ServerContribution
import es.diegogargallotarin.domain.Dish
import es.diegogargallotarin.gula.data.toDomainContribution
import es.diegogargallotarin.gula.data.server.entity.Dish as ServerDish
import es.diegogargallotarin.gula.data.toDomainDish
import es.diegogargallotarin.gula.data.toRoomContribution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseDataSource : RemoteDataSource {

    private var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun getAllDishes(): List<Dish> = withContext(Dispatchers.IO) {
        val task: Task<QuerySnapshot> = fireStore.collection("dishes").get()

        val dishes: MutableList<ServerDish> = mutableListOf()
        for (document in Tasks.await(task).documents) {
            val dish = document.toObject(ServerDish::class.java)
            dish?.let {
                dishes.add(it)
            }

        }
        dishes.map { it.toDomainDish() }
    }

    override suspend fun getContributionsByDishName(dishName: String): List<Contribution> = withContext(Dispatchers.IO) {
        val task: Task<QuerySnapshot> = fireStore.collection("dishes").whereEqualTo("name",dishName).get()

        val contributions: MutableList<ServerContribution> = mutableListOf()
        for (document in Tasks.await(task).documents) {
            val dish = document.toObject(ServerDish::class.java)
            dish?.let {
                for(contribution in it.contributions){
                    contributions.add(contribution)
                }
            }

        }
        contributions.map { it.toDomainContribution(dishName) }
    }
}