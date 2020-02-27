package es.diegogargallotarin.usecases

import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.domain.Dish

class FindDishByName(private val gulaRepository: GulaRepository) {
    suspend operator fun invoke(name: String): Dish = gulaRepository.findByName(name)
}