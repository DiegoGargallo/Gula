package es.diegogargallotarin.gula.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.diegogargallotarin.gula.model.database.Dish
import es.diegogargallotarin.gula.model.server.repository.GulaRepository
import es.diegogargallotarin.gula.ui.common.Event
import es.diegogargallotarin.gula.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val gulaRepository: GulaRepository) : ScopedViewModel() {

    private val _dishes = MutableLiveData<List<Dish>>()
    val dishes: LiveData<List<Dish>> get() = _dishes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _navigateToDish = MutableLiveData<Event<String>>()
    val navigateToDish: LiveData<Event<String>> get() = _navigateToDish

    init {
        initScope()
        refresh()
    }

    fun onDishClicked(dish: Dish) {
        _navigateToDish.value = Event(dish.name)
    }

    private fun refresh() {
        launch {
            _loading.value = true
            _dishes.value = gulaRepository.getAllDishes()
            _loading.value = false
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}