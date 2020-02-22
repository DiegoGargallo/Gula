package es.diegogargallotarin.gula.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.model.entity.Dish
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    companion object {
        const val DISH = "DetailActivity:dish"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        with(intent.getParcelableExtra<Dish>(DISH)) {
            movieDetailToolbar.title = title
            movieDetailImage.loadUrl(photos[0])

            movieDetailSummary.text = description
        }
    }
}