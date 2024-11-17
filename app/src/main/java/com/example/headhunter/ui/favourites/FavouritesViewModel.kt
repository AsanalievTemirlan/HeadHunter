package com.example.headhunter.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.either.Either
import com.example.domain.usecase.GetDataUseCase
import com.example.headhunter.state.UiState
import com.example.headhunter.uiModel.BaseResponseUi
import com.example.headhunter.uiModel.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val useCase: GetDataUseCase): ViewModel() {

    private val _job = MutableStateFlow<UiState<BaseResponseUi>>(UiState.Loading())
    val job = _job.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            useCase().collect { it ->
                when (it) {
                    is Either.Left -> it.message?.let {
                        _job.value = UiState.Error(it)
                    }

                    is Either.Right -> it.data?.let { _job.value = UiState.Success(it.toUi()) }
                }
            }
        }
    }
}