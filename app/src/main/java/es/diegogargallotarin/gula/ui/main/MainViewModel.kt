package es.diegogargallotarin.gula.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.diegogargallotarin.gula.model.database.Dish
import es.diegogargallotarin.gula.model.server.repository.GulaRepository
import es.diegogargallotarin.gula.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val gulaRepository: GulaRepository) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val dishes: List<Dish>) : UiModel()
        class Navigation(val dish: Dish) : UiModel()
    }

    init {
        initScope()
    }

    private fun refresh() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(gulaRepository.getAllDishes())
        }
    }

    fun onDishClicked(dish: Dish) {
        _model.value = UiModel.Navigation(dish)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}