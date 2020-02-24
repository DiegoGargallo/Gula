package es.diegogargallotarin.gula.ui.main

import es.diegogargallotarin.gula.model.entity.Dish
import es.diegogargallotarin.gula.model.repository.DishesRepository
import es.diegogargallotarin.gula.ui.common.Scope
import kotlinx.coroutines.launch


class MainPresenter(private val dishesRepository: DishesRepository) : Scope by Scope.Impl() {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun updateData(movies: List<Dish>)
        fun navigateTo(dish: Dish)
    }

    private var view: View? = null

    fun onCreate(view: View) {
        initScope()
        this.view = view

        launch {
            view.showProgress()
            view.updateData(dishesRepository.getDishes())
            view.hideProgress()
        }
    }

    fun onMovieClicked(dish: Dish) {
        view?.navigateTo(dish)
    }

    fun onDestroy() {
        this.view = null
        destroyScope()
    }
}