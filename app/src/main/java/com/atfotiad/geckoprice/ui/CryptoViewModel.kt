package com.atfotiad.geckoprice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.atfotiad.geckoprice.data.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(repository: CryptoRepository) : ViewModel() {

    val coins = repository.getCryptoList().cachedIn(viewModelScope)

}