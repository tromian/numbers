package com.tromian.test.numberfacts.presentation.main

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavGraphNavigator
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tromian.test.numberfacts.R
import com.tromian.test.numberfacts.appComponent
import com.tromian.test.numberfacts.databinding.FragmentMainScreenBinding
import com.tromian.test.numberfacts.model.Number
import com.tromian.test.numberfacts.model.NumbersRepository
import javax.inject.Inject

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var repository: NumbersRepository

    private val viewModel: MainScreenVM by viewModels {
        NumberViewModelFactory(repository)
    }

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        NumberListAdapter() { itemId ->
            openFragment(itemId)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainScreenBinding.bind(view)
        val rvList = binding.rvList
        setButtonClickListeners()
        rvList.adapter = adapter
        viewModel.history.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })
        viewModel.number.observe(viewLifecycleOwner,{
            if (it!=null) {
                bind(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setButtonClickListeners() {

        binding.btnSearch.setOnClickListener {
            val etValue = binding.editTextNumber.text.toString()
            if (etValue.isNotEmpty()){
                viewModel.searchFactAboutNumber(etValue)
            }
        }
        binding.btnRandom.setOnClickListener {
                viewModel.searchFactAboutRandomNumber()
        }
    }

    private fun bind(number: Number){
        binding.tvNumberResult.text = number.number.toString()
        binding.tvFact.text = number.text
    }

    private fun openFragment(itemId: Int) {
        val number = viewModel.history.value?.get(itemId)
        if (number != null){
            val action = MainScreenFragmentDirections.actionMainScreenFragmentToDetailsFragment(number)
            requireActivity().findNavController(R.id.nav_host_fragment).navigate(action)
        }
    }
}
