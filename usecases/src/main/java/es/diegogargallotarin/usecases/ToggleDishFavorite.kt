package es.diegogargallotarin.usecases

import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.domain.Dish

class ToggleDishFavorite(private val gulaRepository: GulaRepository) {
    suspend fun invoke(dish: Dish): Dish = with(dish) {
        copy(favorite = !favorite).also { gulaRepository.update(it) }
    }
}