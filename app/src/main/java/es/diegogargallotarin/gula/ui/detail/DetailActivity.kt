package es.diegogargallotarin.gula.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.databinding.ActivityDetailBinding
import es.diegogargallotarin.gula.model.entity.Dish
import es.diegogargallotarin.gula.model.server.repository.GulaRepository
import es.diegogargallotarin.gula.ui.common.app
import es.diegogargallotarin.gula.ui.common.getViewModel
import es.diegogargallotarin.gula.ui.common.loadUrl
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val DISH = "DetailActivity:dish"
    }

    private lateinit var viewModel: DetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel { DetailViewModel(intent.getStringExtra(DISH), GulaRepository(app)) }


        val binding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }
}