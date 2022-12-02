package com.akash.heroescv3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.navArgs
import coil.load
import com.akash.heroescv3.MainActivity
import com.akash.heroescv3.databinding.FragmentDetailBinding
import com.akash.heroescv3.db.FavouriteHeroDatabase
import com.akash.heroescv3.model.FavouriteHeroModelItem
import com.akash.heroescv3.model.HeroModelItem
import com.akash.heroescv3.model.Images
import com.akash.heroescv3.repository.HeroRepository
import com.akash.heroescv3.viewModel.HeroViewModel
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var currentHero: HeroModelItem

    private lateinit var heroViewModel: HeroViewModel

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentHero = args.heroModel!!

        //initialize viewModel
        heroViewModel = ViewModelProvider(requireActivity()).get(HeroViewModel::class.java)

        setUpDetailScreen()

        binding.fabAddFavorite.setOnClickListener {
            addFabHero(view)
        }

//        val config = PRDownloaderConfig.newBuilder()
//            .setReadTimeout(30000)
//            .setConnectTimeout(30000)
//            .build()
//        PRDownloader.initialize(applicationContext, config)
    }

    private fun setUpDetailScreen() {
        binding.ivImage.load(currentHero.images.lg)
    }

    private fun addFabHero(view: View) {
        val fabHero = FavouriteHeroModelItem(
            0, currentHero.name, currentHero.slug,currentHero.images
        )
        heroViewModel.addFabHero(fabHero)
        Snackbar.make(view,"Hero saved Successfully", Snackbar.LENGTH_LONG).show()

    }
}