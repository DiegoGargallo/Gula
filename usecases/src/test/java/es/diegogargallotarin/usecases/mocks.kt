package es.diegogargallotarin.usecases

import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.domain.Dish


internal val mockedDish = Dish(
    "Pasta",
    "Pasta dish",
    "https://upload.wikimedia.org/wikipedia/commons/f/fc/Strozzapreti_Pasta.JPG",
    true
)

internal val mockedContribution = Contribution(
    0,
    "Pasta",
    "https://upload.wikimedia.org/wikipedia/commons/f/fc/Strozzapreti_Pasta.JPG",
    "User 1",
    "Restaurant 1"
)