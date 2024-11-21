package com.example.headhunter.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.local.entity.BaseResponseEntity
import com.example.headhunter.R
import com.example.headhunter.databinding.ItemVacanciesBinding
import com.example.headhunter.uiModel.BaseResponseUi
import com.example.headhunter.uiModel.VacancyUi
import com.example.headhunter.uiModel.toDomain
import java.text.SimpleDateFormat
import java.util.Locale

class VacanciesAdapter(
    private val click: OnItemActionListener
) :
    ListAdapter<VacancyUi, VacanciesAdapter.VacanciesViewHolder>(DiffUtilCallback()) {
    inner class VacanciesViewHolder(private val binding: ItemVacanciesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: VacancyUi) = with(binding) {
            imgHeart.setImageResource(if (model.isFavorite) R.drawable.ic_heart_blue else R.drawable.ic_heart)
            tvSalary.text =
                if (model.salaryUi.full == "Уровень дохода не указан") model.salaryUi.full else model.salaryUi.short
            tvTitle.text = model.title
            tvLookingNumber.text = lookingNumber(model.lookingNumber)
            tvLocation.text = model.addressUi.town
            tvCompany.text = model.company
            tvExp.text = model.experienceUi.previewText
            tvPublicDate.text = publicDate(model.publishedDate)

            imgHeart.setOnClickListener {
                click.updateClicked(model.id, !model.isFavorite)
                model.isFavorite = !model.isFavorite
                imgHeart.setImageResource(if (model.isFavorite) R.drawable.ic_heart_blue else R.drawable.ic_heart)
            }
        }

        private fun publicDate(dateString: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))
            val date = inputFormat.parse(dateString)
            return "Опубликовано ${date?.let { outputFormat.format(it) }}"
        }

        private fun lookingNumber(number: Int): String {
            val lastDigit = number % 10
            val lastTwoDigits = number % 100

            return when {
                lastTwoDigits in 11..14 -> "Сейчас просматривает $number человек"
                lastDigit == 1 -> "Сейчас просматривает $number человек"
                lastDigit in 2..4 -> "Сейчас просматривает $number человека"
                else -> "Сейчас просматривает $number человек"
            }
        }

    }

    class DiffUtilCallback : DiffUtil.ItemCallback<VacancyUi>() {

        override fun areItemsTheSame(oldItem: VacancyUi, newItem: VacancyUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VacancyUi, newItem: VacancyUi): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        return VacanciesViewHolder(
            ItemVacanciesBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnItemActionListener {
        fun updateClicked(id: String, isFavorite: Boolean)
    }
}
