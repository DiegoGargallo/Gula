package es.diegogargallotarin.gula.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.model.entity.Dish
import es.diegogargallotarin.gula.model.repository.DishesRepository
import es.diegogargallotarin.gula.ui.common.startActivity
import es.diegogargallotarin.gula.ui.detail.DetailActivity
import es.diegogargallotarin.gula.ui.main.DishesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val presenter by lazy { MainPresenter(DishesRepository()) }
    private val adapter = DishesAdapter(presenter::onMovieClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onCreate(this)
        recycler.adapter = adapter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun updateData(dishes: List<Dish>) {
        adapter.dishes = dishes
    }

    override fun navigateTo(dish: Dish) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.DISH, dish)
        }
    }
}
