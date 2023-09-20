package net.composegridperformance.feature.imagesglide

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagesGlideGridViewModel @Inject constructor(): ViewModel() {

    val data = ImageData(IntArray(1000) { it }.toList())
}