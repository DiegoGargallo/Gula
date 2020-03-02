package es.diegogargallotarin.gula.data

import es.diegogargallotarin.domain.Dish
import es.diegogargallotarin.gula.data.database.Dish as DatabaseDish
import es.diegogargallotarin.gula.data.server.entity.Dish as ServerDish
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.gula.data.database.Contribution as DatabaseContribution
import es.diegogargallotarin.gula.data.server.entity.Contribution as ServerContribution


fun Dish.toRoomDish(): DatabaseDish = DatabaseDish(
    name,
    description,
    photo,
    favorite)

fun DatabaseDish.toDomainDish(): Dish = Dish(
    name,
    description,
    photo,
    favorite)

fun ServerDish.toDomainDish(): Dish = Dish(
    name,
    description.toString(),
    contributions[0].photo.toString(),
    false)

fun Contribution.toRoomContribution(): DatabaseContribution = DatabaseContribution(
    id,
    dishId,
    photo,
    user,
    restaurant)

fun DatabaseContribution.toDomainContribution(): Contribution = Contribution(
    0,
    dishId,
    photo,
    user,
    restaurant)

fun ServerContribution.toDomainContribution(dishName: String): Contribution = Contribution(
    0,
    dishName,
    photo.toString(),
    user.toString(),
    restaurant.toString())
