package net.composegridperformance.feature.colors

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Stable
data class ColorData(val colors: List<Int>)

@Composable
fun ColorsGridScreen(viewModel: ColorsGridViewModel = hiltViewModel()) {
    val data by remember { mutableStateOf(viewModel.data) }

    LazyVerticalGrid(
        modifier = Modifier.testTag("items_grid"),
        columns = GridCells.Fixed(3)
    ) {
        items(
            count = data.colors.size,
            key = { index -> data.colors[index] },
        ) { index ->
            ColorCard(data.colors[index])
        }
    }

    ReportDrawnWhen { data.colors.isNotEmpty() }
}

@Composable
private fun ColorCard(color: Int) = Box(
    modifier = Modifier
        .background(Color(color))
        .testTag("grid_item")
        .size(width = 200.dp, height = 300.dp),
)