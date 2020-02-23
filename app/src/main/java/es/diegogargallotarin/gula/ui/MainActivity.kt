package es.diegogargallotarin.gula.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.model.repository.DishesRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val adapter = DishesAdapter {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.DISH, it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = DishesRepository()

        recycler.adapter = adapter

        GlobalScope.launch(Dispatchers.Main){
            adapter.dishes = repository.getDishes()
        }
    }
}
