package nl.drbreakalot.lichtstad.data.model

import com.google.firebase.database.PropertyName

data class Album(
    var key: String,
    val title: String,
    @get:PropertyName("photo_count")
    @field:PropertyName("photo_count")
    val photoCount: Int,
    @get:PropertyName("thumbnail")
    @field:PropertyName("thumbnail")
    val thumbnailUrl: String,
    @get:PropertyName("thumbnail_size")
    @field:PropertyName("thumbnail_size")
    val thumbnailSize: Size,
    @get:PropertyName("upload_time")
    @field:PropertyName("upload_time")
    val uploadTime: Long
) {
    // no-arg constructor is required for automated Firebase mapping
    constructor() : this("", "", 0, "", Size(), 0)
}

data class Size(val width: Int, val height: Int) {

    // no-arg constructor is required for automated Firebase mapping
    constructor() : this(0, 0)

}

val DemoAlbum = Album(
    key = "asdfasdf",
    title = "Demo album",
    photoCount = 10,
    thumbnailUrl = "https://picsum.photos/200/300",
    thumbnailSize = Size(200, 300),
    uploadTime = 1693803300000L
)
