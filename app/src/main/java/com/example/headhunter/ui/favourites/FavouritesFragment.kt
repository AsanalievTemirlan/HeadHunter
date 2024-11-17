package com.example.headhunter.ui.favourites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.headhunter.R
import com.example.headhunter.databinding.FragmentFavouritesBinding
import com.example.headhunter.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private val viewModel: FavouritesViewModel by viewModels()
    private val binding by lazy { FragmentFavouritesBinding.inflate(layoutInflater) }
    private val favoritesAdapter by lazy { FavoriteAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        launchObserver()
    }

    private fun init() {
        binding.rvVacancies.adapter = favoritesAdapter
    }

    private fun launchObserver() {
        lifecycleScope.launch {
            viewModel.job.collect {
                when (it) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        if (it.data.vacancies.any { vacancy -> vacancy.isFavorite }) {
                            favoritesAdapter.submitList(it.data.vacancies)
                        }
                        binding.tvCount.text = vacanciesCount(it.data.vacancies.size)
                    }

                    is UiState.Error -> {
                        Log.e("ololo", "init: ${it.error}")
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun vacanciesCount(count: Int): String {
        return when {
            count % 100 in 11..19 -> "$count вакансий"
            count % 10 == 1 -> "$count вакансия"
            count % 10 in 2..4 -> "$count вакансии"
            else -> "$count вакансий"
        }
    }
}