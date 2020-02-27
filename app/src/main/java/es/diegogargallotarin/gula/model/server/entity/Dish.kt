package es.diegogargallotarin.gula.model.server.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Dish(var name: String = "",
           var description: String? = null,
           var contributions: List<Contribution> = emptyList()) : Parcelable