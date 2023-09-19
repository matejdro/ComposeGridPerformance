package net.composegridperformance.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GenericPagingSource<Item : Any>(
    val pageSize: Int,
    val fetcher: suspend (Int) -> List<Item>,
) : PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            withContext(Dispatchers.IO) {
                val nextPage = params.key ?: 0
                val pageResult = fetcher(nextPage)
                LoadResult.Page(
                    data = pageResult,
                    prevKey = null,
                    nextKey = if (pageResult.size < params.loadSize) null else nextPage + 1
                )
            }

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}
