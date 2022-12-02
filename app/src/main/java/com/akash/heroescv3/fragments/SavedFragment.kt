package com.akash.heroescv3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.akash.heroescv3.R
import com.akash.heroescv3.adapter.FabHeroAdapter
import com.akash.heroescv3.adapter.HeroAdapter
import com.akash.heroescv3.databinding.FragmentSavedBinding
import com.akash.heroescv3.model.FavouriteHeroModelItem
import com.akash.heroescv3.viewModel.HeroViewModel
import java.nio.file.Files.delete


class SavedFragment : Fragment() , FabHeroAdapter.OnItemHeroClickListener{

    private lateinit var binding: FragmentSavedBinding
    private lateinit var heroAdapter: FabHeroAdapter
    private lateinit var heroViewModel: HeroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize viewModel
        heroViewModel = ViewModelProvider(requireActivity()).get(HeroViewModel::class.java)
        //setUpRecyclerView
        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){
        heroAdapter = FabHeroAdapter(this)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(
                object : DividerItemDecoration(
                    activity, LinearLayout.VERTICAL) {})

            adapter = heroAdapter
        }

        heroViewModel.getAllFabHeroes().observe(viewLifecycleOwner) { heroesSave ->
            heroAdapter.differ.submitList(heroesSave)
            updateUi(heroesSave)
        }
    }

    private fun updateUi(list:List<FavouriteHeroModelItem>){
        if (list.isNotEmpty()){
            binding.cardNoAvailable.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        } else{
            binding.recyclerView.visibility = View.GONE
            binding.cardNoAvailable.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(hero: FavouriteHeroModelItem, view: View, position: Int) {
        deleteHero(hero)
    }

    private fun deleteHero(hero : FavouriteHeroModelItem){
        android.app.AlertDialog.Builder(activity).apply {
            setTitle("Delete Hero")
            setMessage("Are you sure you want to permanently delete this hero?")
            setPositiveButton("DELETE") { _, _ ->
                heroViewModel.delete(hero)
                Toast.makeText(activity,"Hero deleted", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("CANCEL", null)
        }.create().show()
    }
}