package net.composegridperformance.feature.colors

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class ColorsGridViewModel @Inject constructor(): ViewModel() {

    private val random = Random()

    val data = ColorData(IntArray(1000) { random.nextInt() or 0xff_00_00_00.toInt() }.toList())
}