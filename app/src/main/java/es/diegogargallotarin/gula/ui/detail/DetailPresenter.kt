package es.diegogargallotarin.gula.ui.detail

import es.diegogargallotarin.gula.model.entity.Dish

class DetailPresenter {

    private var view: View? = null

    interface View {
        fun updateUI(dish: Dish)
    }

    fun onCreate(view: View, dish: Dish) {
        this.view = view
        view.updateUI(dish)
    }

    fun onDestroy() {
        view = null
    }
}