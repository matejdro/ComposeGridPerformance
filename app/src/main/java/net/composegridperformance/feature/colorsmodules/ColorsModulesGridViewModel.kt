package net.composegridperformance.feature.colorsmodules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import net.composegridperformance.pagination.GenericPagingSource
import javax.inject.Inject

@HiltViewModel
class ColorsModulesGridViewModel @Inject constructor(
    private val repository: ColorsModulesGridRepository,
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 100
    }

    fun getModules() = repository.modules

    // This can be cached, but it is called once in this example and does not cause actual issue
    fun getPaginatedModuleContent() = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        GenericPagingSource(
            pageSize = PAGE_SIZE,
        ) { pageIndex ->
            repository.getPaginatedColors(
                pageIndex = pageIndex,
                pageSize = PAGE_SIZE,
            )
        }
    }.flow.cachedIn(viewModelScope)
}