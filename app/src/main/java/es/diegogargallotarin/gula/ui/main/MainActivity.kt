package es.diegogargallotarin.gula.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.model.repository.DishesRepository
import es.diegogargallotarin.gula.ui.common.getViewModel
import es.diegogargallotarin.gula.ui.common.startActivity
import es.diegogargallotarin.gula.ui.detail.DetailActivity
import es.diegogargallotarin.gula.ui.main.MainViewModel.UiModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: DishesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = getViewModel { MainViewModel(DishesRepository()) }

        adapter = DishesAdapter(viewModel::onMovieClicked)
        recycler.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: UiModel) {
        progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> adapter.dishes = model.dishes
            is UiModel.Navigation -> startActivity<DetailActivity> {
                putExtra(DetailActivity.DISH, model.dish)
            }
        }
    }
}
