package net.composegridperformance.feature.imagespaginated

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import net.composegridperformance.feature.imagespaginated.data.ImageItem

@Composable
fun ImagesPaginatedGridScreen(viewModel: ImagesPaginatedGridViewModel = hiltViewModel()) {
    val data = viewModel.data.collectAsLazyPagingItems()

    LazyVerticalGrid(
        modifier = Modifier.testTag("items_grid"),
        columns = GridCells.Fixed(3)
    ) {
        items(
            count = data.itemCount,
            key = { index -> data[index]!!.id },
        ) { index ->
            data[index]?.let { item ->
                ImageCard(item)
            }
        }
    }

    ReportDrawnWhen { data.itemCount > 0 }
}

@Composable
private fun ImageCard(image: ImageItem) = AsyncImage(
    modifier = Modifier
        .testTag("grid_item")
        .size(width = 200.dp, height = 300.dp),
    model = image.url,
    contentDescription = null,
)