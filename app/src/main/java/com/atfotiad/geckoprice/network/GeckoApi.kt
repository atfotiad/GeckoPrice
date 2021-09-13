package com.atfotiad.geckoprice.network

import com.atfotiad.geckoprice.model.GeckoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeckoApi {

    @GET("coins/markets")
    suspend fun getAllCrypto(
        @Query("vs_currency") currency: String = "eur",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false,
    ): GeckoResponse
}