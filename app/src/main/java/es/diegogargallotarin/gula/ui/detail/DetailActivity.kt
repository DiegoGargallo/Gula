package es.diegogargallotarin.gula.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import es.diegogargallotarin.gula.R
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
        setContentView(R.layout.activity_detail)

        viewModel = getViewModel { DetailViewModel(intent.getStringExtra(DISH), GulaRepository(app)) }

        viewModel.model.observe(this, Observer(::updateUi))

        movieDetailFavorite.setOnClickListener { viewModel.onFavoriteClicked() }
    }

    private fun updateUi(model: DetailViewModel.UiModel) = with(model) {
        dishDetailToolbar.title = title
        dish.photo?.let { dishDetailImage.loadUrl(it) }
        dishDetailSummary.text = dish.description
        dishDetailInfo.setContributions(contributions)

        val icon = if (dish.favorite) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
        movieDetailFavorite.setImageDrawable(getDrawable(icon))
    }
}