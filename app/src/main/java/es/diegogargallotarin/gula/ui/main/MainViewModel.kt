package es.diegogargallotarin.gula.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.diegogargallotarin.gula.model.entity.Dish
import es.diegogargallotarin.gula.model.repository.DishesRepository
import es.diegogargallotarin.gula.ui.common.Scope
import kotlinx.coroutines.launch

class MainViewModel(private val dishesRepository: DishesRepository) : ViewModel(),
    Scope by Scope.Impl() {

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
            _model.value = UiModel.Content(dishesRepository.getDishes())
        }
    }

    fun onMovieClicked(dish: Dish) {
        _model.value = UiModel.Navigation(dish)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}