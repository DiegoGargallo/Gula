package es.diegogargallotarin.gula.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.model.entity.Dish
import es.diegogargallotarin.gula.ui.common.loadUrl
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailPresenter.View {
    companion object {
        const val DISH = "DetailActivity:dish"
    }

    private val presenter = DetailPresenter()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter.onCreate(this, intent.getParcelableExtra(DISH))
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun updateUI(dish: Dish) = with(dish) {
        with(intent.getParcelableExtra<Dish>(DISH)) {
            dishDetailToolbar.title = title
            contributions[0].photo?.let { dishDetailImage.loadUrl(it) }
            dishDetailSummary.text = description
            dishDetailInfo.setDish(this)
        }
    }
}