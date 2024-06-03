package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VideoModel
import javax.inject.Inject

class VideoService @Inject constructor(private val videoApi: VideoApi) {
    suspend fun getAllVideo(): List<VideoModel> {
        return DataVideo.videos
    }
}