package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.GetVideosUseCase
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VideoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RunVideoViewModel @Inject constructor(private val getVideosUseCase: GetVideosUseCase) : ViewModel(){

    private val _videos = MutableStateFlow(emptyList<VideoItem>());
    val videos: StateFlow<List<VideoItem>> get() = _videos;

    init {
        println("ViewModel Initialized")
        getVideos()
    }

    private fun getVideos() {
        viewModelScope.launch {
            try {
                val videos = getVideosUseCase()
                _videos.value = videos
            } catch (_: Exception) {
                // Handle error
            }
        }
    }

}