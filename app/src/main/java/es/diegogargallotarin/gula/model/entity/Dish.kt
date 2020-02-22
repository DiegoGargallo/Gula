package es.diegogargallotarin.gula.model.entity

import com.google.common.base.Ascii.toLowerCase
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Dish(var name: String? = null,
              var description: String? = null,
              var photos: List<String> = emptyList()) {

    val id: String
        get() {
            return name?.let { s ->
                s.split(' ').joinToString("_") {
                    toLowerCase(it)
                }
            } ?: ""
        }
}