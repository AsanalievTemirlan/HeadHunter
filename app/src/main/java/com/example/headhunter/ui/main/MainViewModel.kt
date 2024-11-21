package com.example.headhunter.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.either.Either
import com.example.domain.usecase.GetDataRoomUseCase
import com.example.domain.usecase.GetDataUseCase
import com.example.domain.usecase.InsertDataUseCase
import com.example.domain.usecase.UpdateDataUseCase
import com.example.headhunter.state.UiState
import com.example.headhunter.uiModel.BaseResponseUi
import com.example.headhunter.uiModel.VacancyUi
import com.example.headhunter.uiModel.toDomain
import com.example.headhunter.uiModel.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val getDataRoomUseCase: GetDataRoomUseCase,
    private val updateDataUseCase: UpdateDataUseCase,
    private val insertDataUseCase: InsertDataUseCase
) : ViewModel() {

    private val _job = MutableStateFlow<UiState<BaseResponseUi>>(UiState.Loading())
    val job = _job.asStateFlow()



    init {
        getData()
        getDataRoom()
    }

    private fun getData() {
        viewModelScope.launch {
            getDataUseCase().collect { it ->
                when (it) {
                    is Either.Left -> it.message?.let {
                        _job.value = UiState.Error(it)
                    }
                    is Either.Right -> it.data?.let {
                         insertDataUseCase(it)
                    }
                }
            }
        }
    }

    private fun getDataRoom(){
        viewModelScope.launch {
            getDataRoomUseCase().collect{
                when(it){
                    is Either.Left -> it.message?.let {
                        _job.value = UiState.Error(it)
                    }
                    is Either.Right -> it.data?.let {
                        Log.e("ololo", "getDataRoom: $it", )
                        _job.value = UiState.Success(it.toUi())
                    }
                }
            }
        }
    }

    fun update(id: String, isFavorite: Boolean){
        viewModelScope.launch {
            Log.e("ololo", "update: $isFavorite ", )
            updateDataUseCase(id, isFavorite)
        }
    }


}