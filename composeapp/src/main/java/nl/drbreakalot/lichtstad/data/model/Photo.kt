package nl.drbreakalot.lichtstad.data.model

import com.google.firebase.database.PropertyName

data class Photo(
    var key: String,
    val order: Int,
    @get:PropertyName("image")
    @field:PropertyName("image")
    val imageUrl: String,
    @get:PropertyName("thumbnail")
    @field:PropertyName("thumbnail")
    val thumbnailUrl: String
) {
    // no-arg constructor is required for automated Firebase mapping
    constructor() : this("", 0, "", "")
}
