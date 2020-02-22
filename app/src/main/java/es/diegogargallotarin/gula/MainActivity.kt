package es.diegogargallotarin.gula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import es.diegogargallotarin.gula.data.entity.Dish
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = DishesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        recycler.adapter = adapter

        db.collection("platos")
            .get()
            .addOnSuccessListener { result ->
                val dishes = mutableListOf<Dish>()
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    dishes.add(Dish(document.data["name"].toString()))
                }
                adapter.dishes = dishes
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }
}
