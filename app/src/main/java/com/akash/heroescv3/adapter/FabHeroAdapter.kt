package com.akash.heroescv3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.akash.heroescv3.databinding.HeroLayoutBinding
import com.akash.heroescv3.fragments.DetailFragment
import com.akash.heroescv3.fragments.DetailFragmentDirections
import com.akash.heroescv3.fragments.MainFragmentDirections
import com.akash.heroescv3.fragments.SavedFragmentDirections
import com.akash.heroescv3.model.FavouriteHeroModelItem
import com.akash.heroescv3.model.HeroModelItem

class FabHeroAdapter constructor(
    private val heroItemClick: OnItemHeroClickListener
) : RecyclerView.Adapter<FabHeroAdapter.HeroViewHolder>() {

    inner class HeroViewHolder(val binding: HeroLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<FavouriteHeroModelItem>() {
        override fun areItemsTheSame(
            oldItem: FavouriteHeroModelItem,
            newItem: FavouriteHeroModelItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavouriteHeroModelItem,
            newItem: FavouriteHeroModelItem
        ): Boolean {
            return oldItem == newItem
        }
    }

     val differ = AsyncListDiffer(this, diffCallback)

    var heroCharacter: List<FavouriteHeroModelItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            HeroLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val currentHeroCharacter = heroCharacter[position]

        holder.binding.apply {
            tvTitle.text = currentHeroCharacter.name
            tvDescription.text = currentHeroCharacter.slug
            ibDelete.visibility = View.VISIBLE
            ivArticleImage.load(currentHeroCharacter.images.lg) {
                crossfade(true)
                crossfade(1000)
            }
            ibDelete.setOnClickListener {
                heroItemClick.onItemClick(currentHeroCharacter,ibDelete,position)
            }

        }
        holder.itemView.setOnClickListener { mView ->

            val Hero = HeroModelItem(
                currentHeroCharacter.id,
                currentHeroCharacter.images,
                currentHeroCharacter.name,
                currentHeroCharacter.slug
            )

            val direction = SavedFragmentDirections
                .actionSavedFragmentToDetailFragment(Hero)
            mView.findNavController().navigate(direction)


        }

    }

    override fun getItemCount() = heroCharacter.size


    interface OnItemHeroClickListener{
        fun onItemClick(
            hero : FavouriteHeroModelItem,
            view: View,
            position: Int
        )
    }
}