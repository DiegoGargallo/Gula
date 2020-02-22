package es.diegogargallotarin.gula.model.entity

import android.os.Parcelable
import com.google.common.base.Ascii.toLowerCase
import kotlinx.android.parcel.Parcelize

@Parcelize
class Dish(var name: String? = null,
           var description: String? = null,
           var photos: List<String> = emptyList()) : Parcelable