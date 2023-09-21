package net.composegridperformance.feature.colorsmodules

import androidx.compose.runtime.Stable
import androidx.paging.compose.LazyPagingItems

@Stable
sealed class ColorsModuleUiItem {
    @Stable
    data class HorizontalStaticModule(
        val id: String,
        val colors: List<Int>
    ) : ColorsModuleUiItem()

    @Stable
    data class VerticalPaginatedModule(
        val id: String,
        val colors: LazyPagingItems<Int>
    ) : ColorsModuleUiItem()
}