package net.composegridperformance.feature.colorsmodules

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import net.composegridperformance.feature.colorsmodules.ColorsModule.HorizontalStaticModule
import net.composegridperformance.feature.colorsmodules.ColorsModule.VerticalPaginatedModule

@Composable
fun ColorsModulesGridScreen(viewModel: ColorsModulesGridViewModel = hiltViewModel()) {
    val modules = remember { mutableStateListOf<ColorsModuleUiItem>() }

    viewModel.getModules().forEach { module ->
        modules.add(
            when (module) {
                is HorizontalStaticModule -> ColorsModuleUiItem.HorizontalStaticModule(
                    id = module.id,
                    colors = module.colors,
                )

                is VerticalPaginatedModule -> ColorsModuleUiItem.VerticalPaginatedModule(
                    id = module.id,
                    colors = viewModel.getPaginatedModuleContent().collectAsLazyPagingItems()
                )
            }
        )
    }

    ColorsModulesGridScreen(modules)
}

@Composable
private fun ColorsModulesGridScreen(modules: SnapshotStateList<ColorsModuleUiItem>) {
    LazyVerticalGrid(
        modifier = Modifier.testTag("items_grid"),
        columns = GridCells.Fixed(3)
    ) {
        modules.forEach { module ->
            when (module) {
                is ColorsModuleUiItem.HorizontalStaticModule -> HorizontalModule(module)
                is ColorsModuleUiItem.VerticalPaginatedModule -> VerticalModule(module)
            }
        }
    }

    ReportDrawnWhen { modules.size > 0 }
}

private fun LazyGridScope.HorizontalModule(module: ColorsModuleUiItem.HorizontalStaticModule) {
    item(
        key = module.id + "_module",
        span = { GridItemSpan(3) },
    ) {
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(module.colors.size) { index ->
                ColorCard(module.colors[index])
            }
        }
    }
}

private fun LazyGridScope.VerticalModule(module: ColorsModuleUiItem.VerticalPaginatedModule) {
    items(
        count = module.colors.itemCount,
        key = { index -> module.colors[index]!! },
    ) { index ->
        module.colors[index]?.let { color ->
            ColorCard(color)
        }
    }
}

@Composable
private fun ColorCard(color: Int) = Box(
    modifier = Modifier
        .background(Color(color))
        .testTag("grid_item")
        .size(width = 200.dp, height = 300.dp),
)