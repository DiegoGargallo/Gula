package es.diegogargallotarin.gula.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import es.diegogargallotarin.gula.model.database.Contribution
import es.diegogargallotarin.gula.model.entity.Dish

class DishDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    fun setDish(dish: Dish) = with(dish) {
        text = buildSpannedString {

            bold { appendln("Contributions") }
            for (contribution in contributions){
                bold { appendln("Contribution: ") }
                appendln(contribution.photo)
                appendln(contribution.restaurant)
                appendln(contribution.user)
            }
        }
    }

    fun setContributions(contributions: List<Contribution>) = with(contributions) {
        text = buildSpannedString {

            bold { appendln("Contributions") }
            for (contribution in contributions){
                bold { appendln("Contribution: ") }
                appendln(contribution.photo)
                appendln(contribution.restaurant)
                appendln(contribution.user)
            }
        }
    }
}