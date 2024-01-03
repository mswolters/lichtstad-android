package nl.drbreakalot.lichtstad.ui.video

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.drbreakalot.lichtstad.data.model.Video
import nl.drbreakalot.lichtstad.data.service.VideoService

class VideoViewModel(private val service: VideoService) : ViewModel() {

    val years = service.years
    fun videos(year: Int): Flow<List<Video>> {
        return service.videos(year).map { list -> list.sortedBy { it.dateAsDate } }
    }

    fun onClicked(video: Video) {
        //TODO
    }

}