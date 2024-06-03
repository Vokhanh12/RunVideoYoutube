package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model

data class VideoModel(
    val id: String,
    val screenURL: String,
    val title: String,
    val numView : Int,
    val owner : UserModel
)
