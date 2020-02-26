package es.diegogargallotarin.gula.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.diegogargallotarin.gula.model.database.Contribution
import es.diegogargallotarin.gula.model.database.Dish
import es.diegogargallotarin.gula.model.server.repository.GulaRepository
import es.diegogargallotarin.gula.ui.common.ScopedViewModel
import kotlinx.coroutines.launch


class DetailViewModel(private val dishName: String, private val gulaRepository: GulaRepository) : ScopedViewModel() {

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
            _contributions.value = gulaRepository.findContributionsByDishName(dishName)
            _dish.value = gulaRepository.findDishByName(dishName)
            updateUi()
        }
    }

    fun onFavoriteClicked() {
        launch {
            dish.value?.let {
                val updatedDish = it.copy(favorite = !it.favorite)
                _dish.value = updatedDish
                updateUi()
                gulaRepository.update(updatedDish)
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