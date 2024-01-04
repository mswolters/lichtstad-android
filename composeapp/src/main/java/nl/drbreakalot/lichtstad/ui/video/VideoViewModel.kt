package nl.drbreakalot.lichtstad.ui.video

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.drbreakalot.lichtstad.data.model.Video
import nl.drbreakalot.lichtstad.data.service.VideoService

class VideoViewModel(application: Application, private val service: VideoService) :
    AndroidViewModel(application) {

    val years = service.years
    fun videos(year: Int): Flow<List<Video>> {
        return service.videos(year).map { list -> list.sortedBy { it.dateAsDate } }
    }

    fun onClicked(video: Video) {
        val uri = Uri.parse("http://www.youtube.com/watch?v=${video.id}")
        val intent = Intent(Intent.ACTION_VIEW, uri).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
        getApplication<Application>().startActivity(intent)
    }

}