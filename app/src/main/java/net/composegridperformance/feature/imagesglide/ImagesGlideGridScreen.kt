package net.composegridperformance.feature.imagesglide

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Stable
data class ImageData(val ids: List<Int>)

@Composable
fun ImagesGlideGridScreen(viewModel: ImagesGlideGridViewModel = hiltViewModel()) {
    val data by remember { mutableStateOf(viewModel.data) }

    LazyVerticalGrid(
        modifier = Modifier.testTag("items_grid"),
        columns = GridCells.Fixed(3)
    ) {
        items(
            count = data.ids.size,
            key = { index -> data.ids[index] },
        ) { index ->
            ImageCard(data.ids[index])
        }
    }

    ReportDrawnWhen { data.ids.isNotEmpty() }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImageCard(id: Int) = GlideImage(
    modifier = Modifier
        .testTag("grid_item")
        .size(width = 200.dp, height = 300.dp),
    model = "https://picsum.photos/id/$id/200/300",
    contentDescription = ""
)