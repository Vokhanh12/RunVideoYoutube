package com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.VideoService
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VideoItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.toVideoItem
import javax.inject.Inject

class VideoRepository @Inject constructor(private val service: VideoService) {
    suspend fun getVideos() : List<VideoItem>{
        return service.getAllVideo().map {
            it.toVideoItem()
        }
    }
}