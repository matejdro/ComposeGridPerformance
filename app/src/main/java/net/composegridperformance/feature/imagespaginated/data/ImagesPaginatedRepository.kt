package net.composegridperformance.feature.imagespaginated.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImagesPaginatedRepository @Inject constructor() {

    private val imagesService = Retrofit.Builder()
        .baseUrl("https://picsum.photos/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(PicsumImagesService::class.java)

    suspend fun getImagesPage(
        pageIndex: Int,
        pageSize: Int,
    ): List<ImageItem> = imagesService.getImagesPage(
        pageIndex = pageIndex,
        pageSize = pageSize,
    )
}