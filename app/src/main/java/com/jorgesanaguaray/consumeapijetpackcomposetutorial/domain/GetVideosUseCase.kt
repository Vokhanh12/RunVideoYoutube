package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain


import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VideoItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.VideoRepository
import javax.inject.Inject


class GetVideosUseCase @Inject constructor(private val videoRepository: VideoRepository) {

    suspend operator fun invoke(): List<VideoItem> {
        return videoRepository.getVideos()
    }

}