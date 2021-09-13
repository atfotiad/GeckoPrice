package com.atfotiad.geckoprice.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.atfotiad.geckoprice.network.GeckoApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoRepository @Inject constructor(private val api: GeckoApi) {

    fun getCryptoList() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 10,
                maxSize = 1200,
                enablePlaceholders = false,
                initialLoadSize = 3 * 10
            ),
            pagingSourceFactory = { GeckoPagingSource(api) }
        ).liveData


}