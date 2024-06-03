package com.jorgesanaguaray.consumeapijetpackcomposetutorial.app.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import coil.compose.rememberImagePainter
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VideoItem

@Composable
fun PlayVideoCard(VideoPlayer: @Composable (videoState: MutableState<VideoItem?>) -> Unit, videoStateIn: MutableState<VideoItem?>) {

    Column (
    ){
        VideoPlayer(videoStateIn)
        val avatar = rememberImagePainter(data = videoStateIn.value!!.owner.avartaURL)


        Text(text = videoStateIn.value!!.title, fontSize = 20.sp, fontWeight = FontWeight.W700)

        Box(
            modifier = Modifier.background(Color.White)
                    .padding(5.dp)

        ) {
            Row {
                Image(
                    painter = avatar,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                )
                Box(modifier = Modifier.width(5.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = videoStateIn.value!!.owner.name)
            }
        }
        Text(
            modifier = Modifier.align(Alignment.End),
            text = "View: "+videoStateIn.value!!.numView.toString(),fontSize = 17.sp
        )

    }
}


