package nl.drbreakalot.lichtstad.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Video(
    val id: String,
    val title: String,
    val date: Long,
    val thumbnails: Map<String, String>
) : Parcelable {

    // No-arg constructor is required for automatic firebase mapping
    private constructor() : this("", "", 0, emptyMap())

    val dateAsDate: Date
        get() = Date(date)
}

val DemoVideo = Video(
    id = "asdfas",
    title = "Compilatie woensdagavond 30 augustus t/m zaterdag 2 september",
    date = 1693803300000L,
    thumbnails = mapOf(
        "high" to "https://i.ytimg.com/vi/AOpGIWQPXwI/hqdefault.jpg",
        "medium" to "https://i.ytimg.com/vi/AOpGIWQPXwI/mqdefault.jpg",
        "default" to "https://i.ytimg.com/vi/AOpGIWQPXwI/default.jpg"
    )
)
