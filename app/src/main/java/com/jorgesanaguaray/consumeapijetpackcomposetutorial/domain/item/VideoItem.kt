package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.UserModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VideoModel


data class VideoItem(
    val id: String,
    val screenURL: String,
    val title: String,
    val numView : Int,
    val owner : UserModel
)

fun VideoModel.toVideoItem() = VideoItem(id, screenURL, title, numView, owner)