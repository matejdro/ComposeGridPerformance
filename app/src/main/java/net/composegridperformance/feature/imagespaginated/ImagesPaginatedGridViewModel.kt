package net.composegridperformance.feature.imagespaginated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import net.composegridperformance.feature.imagespaginated.data.ImagesPaginatedRepository
import net.composegridperformance.pagination.GenericPagingSource
import javax.inject.Inject

@HiltViewModel
class ImagesPaginatedGridViewModel @Inject constructor(
    private val repository: ImagesPaginatedRepository,
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 50
    }

    val data = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        GenericPagingSource(
            pageSize = PAGE_SIZE,
        ) { pageIndex ->
            repository.getImagesPage(
                pageIndex = pageIndex,
                pageSize = PAGE_SIZE,
            )
        }
    }.flow.cachedIn(viewModelScope)
}