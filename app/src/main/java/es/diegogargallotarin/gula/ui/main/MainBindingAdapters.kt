package es.diegogargallotarin.gula.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import es.diegogargallotarin.gula.model.database.Dish

@BindingAdapter("items")
fun RecyclerView.setItems(dishes: List<Dish>?) {
    (adapter as? DishesAdapter)?.let {
        it.dishes = dishes ?: emptyList()
    }
}