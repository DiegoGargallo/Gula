package es.diegogargallotarin.gula.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.databinding.FragmentDetailBinding
import es.diegogargallotarin.gula.model.database.RoomDataSource
import es.diegogargallotarin.gula.model.server.FirebaseDataSource
import es.diegogargallotarin.gula.ui.common.app
import es.diegogargallotarin.gula.ui.common.bindingInflate
import es.diegogargallotarin.gula.ui.common.getViewModel
import es.diegogargallotarin.usecases.FindContributionsByDishName
import es.diegogargallotarin.usecases.FindDishByName
import es.diegogargallotarin.usecases.GetDishes
import es.diegogargallotarin.usecases.ToggleDishFavorite


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()

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
            val gulaRepository = GulaRepository(
                RoomDataSource(app.db),
                FirebaseDataSource())

            DetailViewModel(
                args.name,
                FindDishByName(gulaRepository),
                FindContributionsByDishName(gulaRepository),
                ToggleDishFavorite(gulaRepository)
            )
        }

        binding?.apply {
            viewmodel = viewModel
            lifecycleOwner = this@DetailFragment
        }
    }
}