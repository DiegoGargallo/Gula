package es.diegogargallotarin.gula.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Contribution(var photo: String? = null,
                   var user: String? = null,
                   var restaurant: String? = null) : Parcelable