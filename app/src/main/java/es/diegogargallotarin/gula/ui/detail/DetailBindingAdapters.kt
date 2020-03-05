package es.diegogargallotarin.gula.ui.detail

import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.gula.R

@BindingAdapter("contributions")
fun TextView.updateDishDetails(contributionsList: List<Contribution>?) = contributionsList?.run {
    text = buildSpannedString {

        bold { appendln("Contributions") }
        for (contribution in contributionsList){
            bold { appendln("Contribution: ") }
            appendln(contribution.photo)
            appendln(contribution.restaurant)
            appendln(contribution.user)
        }
    }
}

@BindingAdapter("favorite")
fun FloatingActionButton.setFavorite(favorite: Boolean?) {
    val icon = if (favorite == true) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
    setImageDrawable(context.getDrawable(icon))
}