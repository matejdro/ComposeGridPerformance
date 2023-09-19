package net.composegridperformance.feature.images

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagesGridViewModel @Inject constructor(): ViewModel() {

    val data = ImageData(IntArray(1000) { it }.toList())
}