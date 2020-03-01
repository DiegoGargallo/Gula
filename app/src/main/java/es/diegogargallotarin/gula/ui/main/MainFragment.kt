package es.diegogargallotarin.gula.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.gula.R
import es.diegogargallotarin.gula.databinding.FragmentMainBinding
import es.diegogargallotarin.gula.data.database.RoomDataSource
import es.diegogargallotarin.gula.data.server.FirebaseDataSource
import es.diegogargallotarin.gula.ui.common.EventObserver
import es.diegogargallotarin.gula.ui.common.app
import es.diegogargallotarin.gula.ui.common.bindingInflate
import es.diegogargallotarin.gula.ui.common.getViewModel
import es.diegogargallotarin.usecases.GetDishes
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by lazy { getViewModel { app.component.mainViewModel } }
    private lateinit var adapter: DishesAdapter

    private lateinit var navController: NavController
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = container?.bindingInflate(R.layout.fragment_main, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        viewModel.navigateToDish.observe(viewLifecycleOwner, EventObserver { name ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(name)
            navController.navigate(action)
        })

        adapter = DishesAdapter(viewModel::onDishClicked)
        recycler.adapter = adapter

        binding?.apply {
            viewmodel = viewModel
            lifecycleOwner = this@MainFragment
        }
    }
}