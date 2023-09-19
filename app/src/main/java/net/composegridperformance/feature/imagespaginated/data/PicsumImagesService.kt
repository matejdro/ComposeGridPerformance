package net.composegridperformance.feature.imagespaginated.data

import retrofit2.http.GET
import retrofit2.http.Path

interface PicsumImagesService {

    @GET("v2/list?page={pageIndex}&limit={pageSize}")
    suspend fun getImagesPage(
        @Path("pageIndex") pageIndex: Int,
        @Path("pageSize") pageSize: Int,
    ): List<ImageItem>
}