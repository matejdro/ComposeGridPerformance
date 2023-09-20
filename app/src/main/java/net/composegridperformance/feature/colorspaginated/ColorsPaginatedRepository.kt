package net.composegridperformance.feature.colorspaginated

import java.util.Random
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ColorsPaginatedRepository @Inject constructor() {

    private val random = Random()

    private val data = IntArray(1000) { random.nextInt() or 0xff_00_00_00.toInt() }.toList()

    fun getColorsPage(
        pageIndex: Int,
        pageSize: Int,
    ): List<Int> = data.subList(
        fromIndex = pageIndex * pageSize,
        toIndex = (pageIndex + 1) * pageSize,
    )
}