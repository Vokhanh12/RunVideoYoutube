package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home

import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.app.component.PlayVideoCard
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.app.component.VideoCard
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VideoItem
import kotlinx.coroutines.delay
import java.util.LinkedList
import java.util.Queue

// Use for queue video
fun _removeElement(queue: Queue<VideoItem>, element: VideoItem): Queue<VideoItem> {
    val filteredQueue = LinkedList<VideoItem>()

    // Iterate through the elements of the queue
    for (item in queue) {
        // Add all elements except the one to remove to the filtered queue
        if (item != element) {
            filteredQueue.add(item)
        }
    }

    // Return the filtered queue
    return filteredQueue
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectScreen(
    runVideoViewModel: RunVideoViewModel = hiltViewModel()
) {
    // gets video in view model
    val videos by runVideoViewModel.videos.collectAsState()

    // queue video
    var videoQueue : Queue<VideoItem> = LinkedList<VideoItem>(videos)

    // get size mobile screen
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    val videoCurrentState = remember {mutableStateOf<VideoItem?>(null)}
    val showDialog = remember { mutableStateOf(false) }
    val isVideoEndState = remember { mutableStateOf(false) }
    val isVideoOnNext = remember { mutableStateOf(false) }
    val dialogVideoItem = remember { mutableStateOf<VideoItem?>(null) }

    Scaffold(
        content = { paddingValues ->
            Column {
                // Check State of video
                if (videoCurrentState.value != null) {

                    if (showDialog.value) {
                        dialogVideoItem.value?.let { video ->
                            AlertDialogExample(
                                onDismissRequest = { showDialog.value = false },
                                onConfirmation = {

                                    val videoNext01 = videoQueue.poll()
                                    videoCurrentState.value = videoNext01

                                    showDialog.value = false
                                    isVideoEndState.value= false

                                    println("Confirmation registered for video: ${video.title}")
                                },
                                dialogTitle = "Next Video",

                                icon = Icons.Default.Info
                            )
                        }
                    }

                    PlayVideoCard(
                        VideoPlayer = {
                            YoutubeVideoPlayer(
                                videoCurrentState, isShowDiaglogStateIn = showDialog,
                            )
                        },
                        videoStateIn = videoCurrentState
                    )
                    // Check video in the end
                    if(isVideoEndState.value){
                        // show
                        showDialog.value = true
                    }
                }
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(paddingValues)
                ) {
                    for (video in videoQueue) {
                        VideoCard(
                            videoItem = video,
                            avatarUrl = video.owner.avartaURL,
                            nickNameOwner = video.owner.name,
                            paddingValues = paddingValues,
                            onVideoClicked = {
                                videoCurrentState.value = it
                                videoQueue = _removeElement(videoQueue, video)
                                dialogVideoItem.value = it
                            }
                        )
                    }
                }
            }
        }
    )
    // Show AlertDialog when showDialog is true
}

@Composable
fun YoutubeVideoPlayer(videoState: MutableState<VideoItem?>, isShowDiaglogStateIn: MutableState<Boolean> ) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp


    val webView = WebView(LocalContext.current).apply {
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        webViewClient = WebViewClient()
    }

    val htmlData = getHTMLData(videoState.value!!.id,screenHeightDp - 140)

    val webViewInterface = WebViewInterface { isVideoEnd ->
        isShowDiaglogStateIn.value = isVideoEnd
    }
    webView.addJavascriptInterface(webViewInterface, "Android")

    AndroidView(
        factory = { webView }
    ) { view ->
        view.loadDataWithBaseURL(
            "https://www.youtube.com",
            htmlData,
            "text/html",
            "UTF-8",
            null
        )
    }

}

fun getHTMLData(videoId: String, screenHeight: Int): String {
    return """
        <html>
            <body style="margin:0px;padding:0px;">
               <div id="player"></div>
                <script>
                    var player;
                    function onYouTubeIframeAPIReady() {
                        player = new YT.Player('player', {
                            height: '$screenHeight',
                            width: '100%',
                            videoId: '$videoId',
                            playerVars: {
                                'playsinline': 1
                            },
                            events: {
                                'onReady': onPlayerReady,
                                'onStateChange': onPlayerStateChange
                            }
                        });
                    }
                    function onPlayerReady(event) {
                        player.playVideo();
                    }
                 
                    // Khi video kết thúc
                    function onPlayerStateChange(event) {
                        if (event.data === YT.PlayerState.ENDED) {
                            Android.notifyVideoEnd();
                        }
                    }
                </script>
                <script src="https://www.youtube.com/iframe_api"></script>
            </body>
        </html>
    """.trimIndent()
}

class WebViewInterface(private val onVideoEnd: (Boolean) -> Unit) {
    @JavascriptInterface
    fun notifyVideoEnd() {
        onVideoEnd(true)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    icon: ImageVector,
) {
    var secondsLeft by remember { mutableStateOf(5) }

    LaunchedEffect(Unit) {
        for (i in 5 downTo 0) {
            secondsLeft = i
            delay(1000)
        }
        onDismissRequest()
    }

    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = "$dialogTitle \n \n $secondsLeft",  textAlign = TextAlign.Center)
        },
        text = {

        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Next")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}