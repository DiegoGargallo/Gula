package es.diegogargallotarin.usecases

import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.domain.Dish

class GetDishes(private val gulaRepository: GulaRepository) {
    suspend fun invoke(): List<Dish> = gulaRepository.getAllDishes()
}
