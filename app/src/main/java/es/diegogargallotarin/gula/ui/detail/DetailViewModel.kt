package es.diegogargallotarin.gula.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.diegogargallotarin.gula.model.database.Contribution
import es.diegogargallotarin.gula.model.database.Dish
import es.diegogargallotarin.gula.model.toDomainDish
import es.diegogargallotarin.gula.model.toRoomContribution
import es.diegogargallotarin.gula.model.toRoomDish
import es.diegogargallotarin.gula.ui.common.ScopedViewModel
import es.diegogargallotarin.usecases.FindContributionsByDishName
import es.diegogargallotarin.usecases.FindDishByName
import es.diegogargallotarin.usecases.ToggleDishFavorite
import kotlinx.coroutines.launch


class DetailViewModel(private val dishName: String,
                      private val findDishByName: FindDishByName,
                      private val findContributionsByDishName: FindContributionsByDishName,
                      private val toggleDishFavorite: ToggleDishFavorite) : ScopedViewModel() {

    private val _dish = MutableLiveData<Dish>()
    val dish: LiveData<Dish> get() = _dish

    private val _contributions = MutableLiveData<List<Contribution>>()
    val contributions: LiveData<List<Contribution>> get() = _contributions

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _url = MutableLiveData<String>()
    val url: LiveData<String> get() = _url

    private val _overview = MutableLiveData<String>()
    val overview: LiveData<String> get() = _overview

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> get() = _favorite

    init {
        launch {
            _contributions.value = findContributionsByDishName(dishName).map { it.toRoomContribution() }
            _dish.value = findDishByName(dishName).toRoomDish()
            updateUi()
        }
    }

    fun onFavoriteClicked() {
        launch {
            dish.value?.let {
                toggleDishFavorite.invoke(it.toDomainDish())
            }
        }
    }

    private fun updateUi() {
        dish.value?.run {
            _title.value = name
            _overview.value = description
            _url.value = photo
            _favorite.value = favorite
        }
        contributions.value?.run {
            _contributions.value = this
        }
    }
}