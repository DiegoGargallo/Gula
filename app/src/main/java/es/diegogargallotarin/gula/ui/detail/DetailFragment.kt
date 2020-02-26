package es.diegogargallotarin.gula.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.databinding.FragmentDetailBinding
import es.diegogargallotarin.gula.model.server.repository.GulaRepository
import es.diegogargallotarin.gula.ui.common.app
import es.diegogargallotarin.gula.ui.common.bindingInflate
import es.diegogargallotarin.gula.ui.common.getViewModel


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var binding: FragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = container?.bindingInflate(R.layout.fragment_detail, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = getViewModel {
            DetailViewModel(arguments?.getString("name") ?: "", GulaRepository(app))
        }

        binding?.apply {
            viewmodel = viewModel
            lifecycleOwner = this@DetailFragment
        }
    }
}