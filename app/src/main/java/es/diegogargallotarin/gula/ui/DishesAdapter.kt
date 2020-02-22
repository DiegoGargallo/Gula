package es.diegogargallotarin.gula.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.model.entity.Dish
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.view_dish.view.*

class DishesAdapter(private val listener: (Dish) -> Unit) :
        RecyclerView.Adapter<DishesAdapter.ViewHolder>() {

        var dishes: List<Dish> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].name == new[newItemPosition].name

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_dish, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishes[position]
        holder.bind(dish)
        holder.itemView.setOnClickListener { listener(dish) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(dish: Dish) {
            itemView.dishTitle.text = dish.name
            itemView.dishImage.loadUrl(dish.photos[0])
        }
    }
}