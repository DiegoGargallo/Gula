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

    class UiModel(val dish: Dish, val contributions: List<Contribution>)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findDish()
            return _model
        }

    private fun findDish() {
        launch {
            _model.value = UiModel(gulaRepository.findDishByName(dishName), gulaRepository.findContributionsByDishName(dishName))
        }
    }
}