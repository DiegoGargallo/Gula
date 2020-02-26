package es.diegogargallotarin.gula.model.server.repository

import com.google.firebase.firestore.FirebaseFirestore
import es.diegogargallotarin.gula.GulaApp
import es.diegogargallotarin.gula.model.database.DishDao
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import es.diegogargallotarin.gula.model.database.Dish as DbDish
import es.diegogargallotarin.gula.model.entity.Dish as ServerDish
import es.diegogargallotarin.gula.model.database.Contribution as DbContribution
import es.diegogargallotarin.gula.model.entity.Contribution as ServerContribution


class GulaRepository(application: GulaApp) {

    private var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val db = application.db

    suspend fun getDishes(): MutableList<ServerDish> = suspendCancellableCoroutine { continuation ->
        val dishes = mutableListOf<ServerDish>()
        fireStore.collection("dishes").get().addOnCompleteListener {
            if (it.isSuccessful && it.result != null) {
                for (document in it.result!!.documents) {
                    dishes.add(document.toObject(ServerDish::class.java)!!)
                }
                continuation.resume(dishes)
            }
        }
    }

    suspend fun getAllDishes(): List<DbDish> = withContext(Dispatchers.IO) {
        with(db.dishDao()) {
            if (dishCount() <= 0) {
                getDataFromFirestore()
            }
            getAll()
        }
    }

    private fun DishDao.getDataFromFirestore() {
        val dishes = mutableListOf<ServerDish>()
        val contributions = mutableListOf<ServerContribution>()
        fireStore.collection("dishes").get().addOnCompleteListener {
            if (it.isSuccessful && it.result != null) {
                for (document in it.result!!.documents) {
                    var dish = document.toObject(ServerDish::class.java)!!
                    dishes.add(dish)
                    for (contribution in dish.contributions) {
                        contributions.add(contribution)
                    }
                }
            }
            GlobalScope.launch(Dispatchers.IO) {
                insertAll(dishes.map(ServerDish::convertToDbDish), getContributionList(dishes))
            }
        }
    }

    suspend fun findDishByName(name: String): DbDish = withContext(Dispatchers.IO) {
        db.dishDao().findByName(name)
    }

    suspend fun findContributionsByDishName(name: String): List<DbContribution> = withContext(Dispatchers.IO) {
        db.contributionDao().findContributionsByDishName(name)
    }
}

private fun ServerDish.convertToDbDish() = DbDish(
    this.name,
    description,
    contributions[0].photo
)

private fun getContributionList(dish: ServerDish): MutableList<DbContribution>{
    val dbContributions =  mutableListOf<DbContribution>()

    for (contribution in dish.contributions){
        dbContributions.add(DbContribution(
            0,
            dish.name,
            contribution.photo,
            contribution.user,
            contribution.restaurant
        ))
    }
    return dbContributions
}

private fun getContributionList(dishes: List<ServerDish>): MutableList<DbContribution>{
    val dbContributions =  mutableListOf<DbContribution>()

    for (dish in dishes){
        dbContributions.addAll(getContributionList(dish))
    }
    return dbContributions
}