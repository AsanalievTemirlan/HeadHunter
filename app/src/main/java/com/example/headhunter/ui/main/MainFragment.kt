package com.example.headhunter.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.headhunter.databinding.FragmentMainBinding
import com.example.headhunter.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val binding: FragmentMainBinding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    private val adapterVacancies by lazy { VacanciesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun init() {
        binding.rvVacancies.adapter = adapterVacancies
        viewModel.viewModelScope.launch {
            viewModel.job.collect {
                when (it) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        adapterVacancies.submitList(it.data.vacancies)
                    }

                    is UiState.Error -> {
                        Log.e("ololo", "init: ${it.error}")
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}





