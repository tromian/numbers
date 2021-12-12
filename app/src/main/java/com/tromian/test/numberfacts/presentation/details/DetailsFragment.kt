package com.tromian.test.numberfacts.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tromian.test.numberfacts.R
import com.tromian.test.numberfacts.databinding.FragmentMainScreenBinding
import com.tromian.test.numberfacts.databinding.FragmentNumberDetailsBinding
import com.tromian.test.numberfacts.model.Number

class DetailsFragment : Fragment(R.layout.fragment_number_details) {


    private var _binding: FragmentNumberDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var number : Number

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNumberDetailsBinding.bind(view)
        val safeArgs : DetailsFragmentArgs by navArgs()
        number = safeArgs.number
        bind(number)
    }

    private fun bind(number: Number){

        binding.tvNumberDetails.text = number.number.toString()
        binding.tvFactDetails.text = number.text

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}