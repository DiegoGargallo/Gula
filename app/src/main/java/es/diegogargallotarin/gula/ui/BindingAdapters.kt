package es.diegogargallotarin.gula.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import es.diegogargallotarin.gula.ui.common.loadUrl

@BindingAdapter("url")
fun ImageView.bindUrl(url: String?) {
    if (url != null) loadUrl(url)
}