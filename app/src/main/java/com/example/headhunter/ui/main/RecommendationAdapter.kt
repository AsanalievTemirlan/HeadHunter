package com.example.headhunter.ui.main

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.headhunter.R
import com.example.headhunter.databinding.ItemRecomendationBinding
import com.example.headhunter.uiModel.OfferUi

class RecommendationAdapter :
    ListAdapter<OfferUi, RecommendationAdapter.RecommendationViewHolder>(DiffUtilCallback()) {
    class DiffUtilCallback : DiffUtil.ItemCallback<OfferUi>() {

        override fun areItemsTheSame(oldItem: OfferUi, newItem: OfferUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OfferUi, newItem: OfferUi): Boolean {
            return oldItem == newItem
        }
    }

    inner class RecommendationViewHolder(private val binding: ItemRecomendationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(offerUi: OfferUi) = with(binding) {
            tvTitle.maxLines = if (offerUi.button?.text != null) 2 else 3
            tvTitle.text = offerUi.title
            offerUi.button?.text?.let {
                tvButton.text = it
                tvButton.visibility = View.VISIBLE
            } ?: run {
                tvButton.visibility = View.GONE
            }
            if (offerUi.id != null) {
                icon(offerUi.id)
            } else {
                imgRec.visibility = View.GONE
            }

            offerUi.link?.let { link ->
                itemView.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    itemView.context.startActivity(intent)
                }
            } ?: run {
                itemView.setOnClickListener(null)
            }
        }


        private fun icon(id: String) = with(binding) {
            when (id) {
                "near_vacancies" -> {
                    imgRec.setBackgroundResource(R.drawable.bg_rec_item_blue)
                    imgRec.setImageResource(R.drawable.ic_locate)
                    imgRec.visibility = View.VISIBLE
                }

                "level_up_resume" -> {
                    imgRec.setBackgroundResource(R.drawable.bg_rec_item_green)
                    imgRec.setImageResource(R.drawable.ic_star)
                    imgRec.visibility = View.VISIBLE
                }

                "temporary_job" -> {
                    imgRec.setBackgroundResource(R.drawable.bg_rec_item_green)
                    imgRec.setImageResource(R.drawable.ic_rec1)
                    imgRec.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        return RecommendationViewHolder(
            ItemRecomendationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}