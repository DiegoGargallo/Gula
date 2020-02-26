package es.diegogargallotarin.gula.model.entity

import android.os.Parcelable
import com.google.common.base.Ascii.toLowerCase
import kotlinx.android.parcel.Parcelize

@Parcelize
class Dish(var name: String = "",
           var description: String? = null,
           var contributions: List<Contribution> = emptyList()) : Parcelable