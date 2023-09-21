package net.composegridperformance.feature.colorsmodules

import androidx.compose.runtime.Stable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Random
import javax.inject.Inject
import javax.inject.Singleton

@Stable
sealed class ColorsModule {
    @Stable
    data class HorizontalStaticModule(
        val id: String,
        val colors: List<Int>
    ) : ColorsModule()

    @Stable
    data class VerticalPaginatedModule(
        val id: String
    ) : ColorsModule()
}

@Singleton
class ColorsModulesGridRepository @Inject constructor() {

    private val random = Random()

    private val verticalModuleData = getRandomColorsList(1000)

    val modules = listOf(
        ColorsModule.HorizontalStaticModule(
            id = "1",
            colors = getRandomColorsList(20),
        ),
        ColorsModule.HorizontalStaticModule(
            id = "2",
            colors = getRandomColorsList(20),
        ),
        ColorsModule.VerticalPaginatedModule(
            id = "3",
        ),
    )

    suspend fun getPaginatedColors(
        pageIndex: Int,
        pageSize: Int,
    ): List<Int> = withContext(Dispatchers.IO) {
        verticalModuleData.subList(
            fromIndex = pageIndex * pageSize,
            toIndex = (pageIndex + 1) * pageSize,
        )
    }

    private fun getRandomColorsList(size: Int) =
        IntArray(size) { random.nextInt() or 0xff_00_00_00.toInt() }.toList()
}