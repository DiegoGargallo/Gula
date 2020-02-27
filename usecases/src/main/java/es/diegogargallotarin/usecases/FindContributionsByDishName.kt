package es.diegogargallotarin.usecases

import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.domain.Contribution

class FindContributionsByDishName(private val gulaRepository: GulaRepository) {
    suspend operator fun invoke(name: String): List<Contribution> = gulaRepository.findContributionsByDishName(name)
}