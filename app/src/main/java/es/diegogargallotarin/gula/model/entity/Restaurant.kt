package es.diegogargallotarin.gula.model.entity

import com.google.firebase.firestore.GeoPoint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class Restaurant(var name: String? = null,
                 var location: @RawValue GeoPoint = GeoPoint(0.0,0.0) ) : Parcelable