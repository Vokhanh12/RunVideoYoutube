package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.RunVideoModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VideoModel


object  DataRunVideo{

    val videos_01: List<VideoModel> = listOf(
       DataVideo.video01
    )
    val videos_02: List<VideoModel> = listOf(
        DataVideo.video03,
        DataVideo.video04,
        DataVideo.video05
    )

    val runVideoModel01 = RunVideoModel(DataUser.user01, videos_01)
    val runVideoModel02 = RunVideoModel(DataUser.user02, videos_02)
    var runVideos: List<RunVideoModel> = listOf(DataRunVideo.runVideoModel01, DataRunVideo.runVideoModel02)

}