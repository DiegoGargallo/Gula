package es.diegogargallotarin.gula.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.diegogargallotarin.gula.model.entity.Dish


class DetailViewModel(private val dish: Dish) : ViewModel() {

    class UiModel(val dish: Dish)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) _model.value = UiModel(dish)
            return _model
        }
}