package es.diegogargallotarin.gula.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.model.entity.Dish
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = DishesAdapter {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.DISH, it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        recycler.adapter = adapter

        db.collection("dishes")
            .get()
            .addOnSuccessListener { result ->
                val dishes = mutableListOf<Dish>()
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    val dish = document.toObject(Dish::class.java)
                    dishes.add(dish)
                }
                adapter.dishes = dishes
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }
}
