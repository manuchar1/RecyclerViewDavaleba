package com.example.recyclerviewdavaleba

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdavaleba.databinding.LayoutListItemBinding

class RecyclerAdapter(
    private val countries: List<Countries>,
    private var listener: OnItemModelClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == NORMAL_USER) {
            UserProfileViewHolder(
                LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            UserWithoutPhotoViewHolder(
                LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserWithoutPhotoViewHolder -> holder.bind()
            is UserProfileViewHolder -> holder.bind()

        }
    }

    override fun getItemCount() = countries.size


    inner class UserWithoutPhotoViewHolder(private val binding: LayoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {
        private lateinit var countries: Countries
        fun bind() {
            countries = this@RecyclerAdapter.countries[adapterPosition]
            binding.tvName.text = countries.name
            binding.tvContinent.text = countries.continent
            binding.imgFlag.setImageResource(countries.flag ?: R.drawable.no_photo)
        }

        init {
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
            return true

        }

    }

    inner class UserProfileViewHolder(private val binding: LayoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {
        private lateinit var countries: Countries
        fun bind() {
            countries = this@RecyclerAdapter.countries[adapterPosition]
            binding.tvName.text = countries.name
            binding.tvContinent.text = countries.continent
            binding.imgFlag.setImageResource(countries.flag ?: R.mipmap.ic_launcher)
        }

        init {
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
            return true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val model = countries[position]
        return if (model.flag == null)
            USER_WITH_NO_PHOTO
        else
            NORMAL_USER
    }

    companion object {
        const val NORMAL_USER = 1
        const val USER_WITH_NO_PHOTO = 2
    }

    interface OnItemModelClickListener {
        fun onItemClick(position: Int)
    }
}

