package com.akash.heroescv3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.akash.heroescv3.databinding.HeroLayoutBinding
import com.akash.heroescv3.fragments.MainFragmentDirections
import com.akash.heroescv3.model.HeroModelItem

class HeroAdapter : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    inner class HeroViewHolder(val binding: HeroLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object :DiffUtil.ItemCallback<HeroModelItem>(){
        override fun areItemsTheSame(oldItem: HeroModelItem, newItem: HeroModelItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: HeroModelItem, newItem: HeroModelItem): Boolean {
            return oldItem==newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var heroCharacter: List<HeroModelItem>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(HeroLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val currentHeroCharacter = heroCharacter[position]

        holder.binding.apply {
            tvTitle.text = currentHeroCharacter.name
            tvDescription.text = currentHeroCharacter.slug
            ivArticleImage.load(currentHeroCharacter.images.lg){
                crossfade(true)
                crossfade(1000)
            }
        }
        //Main -> Detail
        holder.itemView.setOnClickListener { mView->
            val direction = MainFragmentDirections
                .actionMainFragmentToDetailFragment(currentHeroCharacter)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount()= heroCharacter.size
}