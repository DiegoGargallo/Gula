package es.diegogargallotarin.gula.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.databinding.ActivityMainBinding
import es.diegogargallotarin.gula.model.server.repository.GulaRepository
import es.diegogargallotarin.gula.ui.common.EventObserver
import es.diegogargallotarin.gula.ui.common.app
import es.diegogargallotarin.gula.ui.common.getViewModel
import es.diegogargallotarin.gula.ui.common.startActivity
import es.diegogargallotarin.gula.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: DishesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel { MainViewModel(GulaRepository(app)) }

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        adapter = DishesAdapter(viewModel::onDishClicked)
        binding.recycler.adapter = adapter

        viewModel.navigateToDish.observe(this, EventObserver { name ->
            startActivity<DetailActivity> {
                putExtra(DetailActivity.DISH, name)
            }
        })
    }
}
