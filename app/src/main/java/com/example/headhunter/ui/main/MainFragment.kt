package com.example.headhunter.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.headhunter.R
import com.example.headhunter.databinding.FragmentMainBinding
import com.example.headhunter.state.UiState
import com.example.headhunter.uiModel.VacancyUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val binding: FragmentMainBinding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    private val adapterVacancies by lazy { VacanciesAdapter() }
    private val adapterRecommendation by lazy { RecommendationAdapter() }
    private var isExpanded = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    private fun vacanciesCount(count: Int): String {
        return when {
            count % 100 in 11..19 -> "$count вакансий"
            count % 10 == 1 -> "$count вакансия"
            count % 10 in 2..4 -> "$count вакансии"
            else -> "$count вакансий"
        }
    }

    private fun vacanciesState(state: Boolean) = with(binding) {
        if (state) {
            rvRec.visibility = View.GONE
            tvVacancies.visibility = View.GONE
            tvCount.visibility = View.VISIBLE
            tvCorrespondence.visibility = View.VISIBLE
            btnVacancies.visibility = View.GONE
            etSearch.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_arrow, 0, 0, 0
            )
        } else {
            btnVacancies.visibility = View.VISIBLE
            rvRec.visibility = View.VISIBLE
            tvVacancies.visibility = View.VISIBLE
            tvCount.visibility = View.GONE
            tvCorrespondence.visibility = View.GONE
            etSearch.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_search, 0, 0, 0
            )
        }
    }

    private fun stateUpdate(){
        val state = viewModel.job.value
        if (state is UiState.Success) {
            updateVacanciesList(state.data.vacancies)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun clickListener() = with(binding) {
        btnVacancies.setOnClickListener {
            isExpanded =!isExpanded
            vacanciesState(true)
            stateUpdate()
            scroll.smoothScrollTo(0, 0)
        }
        etSearch.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableStart = etSearch.compoundDrawables[0]
                if (drawableStart != null) {
                    val drawableWidth = drawableStart.bounds.width()
                    if (event.rawX <= (etSearch.left + drawableWidth + etSearch.paddingStart)) {
                        vacanciesState(false)
                        isExpanded = !isExpanded
                        stateUpdate()
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }
    }

    private fun updateVacanciesList(vacancies: List<VacancyUi>) {
        val displayedVacancies = if (isExpanded) vacancies else vacancies.take(3)
        adapterVacancies.submitList(displayedVacancies)
    }

    private fun init() {
        binding.rvVacancies.apply {
            adapter = adapterVacancies
        }
        binding.rvRec.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterRecommendation
        }
        viewModel.viewModelScope.launch {
            viewModel.job.collect {
                when (it) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        updateVacanciesList(it.data.vacancies)
                        adapterRecommendation.submitList(it.data.offerUis)
                        binding.btnVacancies.text = "Ещё " + vacanciesCount(it.data.vacancies.size)
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
}