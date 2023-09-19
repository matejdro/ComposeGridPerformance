package net.composegridperformance.feature.colorspaginated

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun ColorsPaginatedGridScreen(viewModel: ColorsPaginatedGridViewModel = hiltViewModel()) {
    val data = viewModel.data.collectAsLazyPagingItems()

    LazyVerticalGrid(
        modifier = Modifier.testTag("items_grid"),
        columns = GridCells.Fixed(3)
    ) {
        items(
            count = data.itemCount,
            key = { index -> data[index]!! },
        ) { index ->
            data[index]?.let { color ->
                ColorCard(color)
            }
        }
    }

    ReportDrawnWhen { data.itemCount > 0 }
}

@Composable
private fun ColorCard(color: Int) = Box(
    modifier = Modifier
        .background(Color(color))
        .testTag("grid_item")
        .size(width = 200.dp, height = 300.dp),
)