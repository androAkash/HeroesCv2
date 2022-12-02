package com.akash.heroescv3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.akash.heroescv3.R
import com.akash.heroescv3.adapter.HeroAdapter
import com.akash.heroescv3.databinding.FragmentMainBinding
import com.akash.heroescv3.viewModel.HeroViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel : HeroViewModel by viewModels()
    private lateinit var heroAdapter: HeroAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)

        setUpRv()

        return binding.root
    }

    private fun setUpRv() {
        heroAdapter = HeroAdapter()

        binding.recyclerView.apply {
            adapter = heroAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.responseHero.observe(viewLifecycleOwner) { listOfHero ->
            heroAdapter.heroCharacter = listOfHero
        }
    }
}