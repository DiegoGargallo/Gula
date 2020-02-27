package es.diegogargallotarin.domain

data class Contribution(
    val id: Int,
    var dishId: String,
    var photo: String,
    var user: String,
    var restaurant: String
)