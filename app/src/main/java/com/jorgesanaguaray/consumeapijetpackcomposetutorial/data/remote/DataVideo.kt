package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VideoModel


object DataVideo{
    val videoURL01 = "https://www.youtube.com/watch?v=wjJ3-SzxhCk"
    val imageURL01 = "https://www.elegantthemes.com/blog/wp-content/uploads/2020/08/hello-world.png"

    val videoURL02 = "https://storage.googleapis.com/exoplayer-test-media-1/gen-3/screens/dash-vod-single-segment/video-avc-baseline-480.mp4"
    val imageURL02 = "https://st.quantrimang.com/photos/image/2021/11/27/bo-suu-tap-hello-world-bang-603-ngon-ngu-lap-trinh.jpg"

    val video01 =  VideoModel(
        id = "Mq5PVEmkZlQ",
        screenURL = imageURL01,
        title = "Title 01",
        numView = 345,
        owner = DataUser.user01
    )

    val video03 = VideoModel(
        id = "rKB9TBlFhNA",
        screenURL = imageURL02,
        title = "Title 01",
        numView = 123,
        owner = DataUser.user02
    )

    val video04 = VideoModel(
            id = "swAVlSrKQ2I",
            screenURL = imageURL02,
            title = "Title 02",
            numView = 500,
            owner = DataUser.user02
        )

    val video05 =  VideoModel(
        id = "_CLfOGykrPk",
        screenURL = imageURL02,
        title = "Title 03",
        numView = 300,
        owner = DataUser.user02
    )

    val videos: List<VideoModel> = listOf(
        video01,
        video03,
        video04,
        video05
    )

}