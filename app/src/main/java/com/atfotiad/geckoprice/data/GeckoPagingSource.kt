package com.atfotiad.geckoprice.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.atfotiad.geckoprice.model.Coin
import com.atfotiad.geckoprice.network.GeckoApi
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGING_INDEX = 1
class GeckoPagingSource (private val api: GeckoApi): PagingSource<Int,Coin>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        val position = params.key ?: STARTING_PAGING_INDEX

       return try {
            val response = api.getAllCrypto(page = position,perPage = params.loadSize)


           LoadResult.Page(
               data = response,
               prevKey = if (position == STARTING_PAGING_INDEX) null else position - 1,
               nextKey = if (response.isEmpty()) null else position + 1
           )

       }catch (exception: IOException){
           LoadResult.Error(exception)
       }catch (exception:HttpException){
           LoadResult.Error(exception)
       }

    }

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}
