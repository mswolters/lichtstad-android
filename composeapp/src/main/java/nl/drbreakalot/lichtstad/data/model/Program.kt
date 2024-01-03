package nl.drbreakalot.lichtstad.data.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Program(
    val key: String,
    val title: String,
    val time: Long,
    val description: String?,
    val location: Location?,
    @get:PropertyName("image_url")
    @field:PropertyName("image_url")
    val imageUrl: String?
) : Parcelable {

    val timeAsDate: Date
        get() = Date(time)

    // No-arg constructor is required for automatic firebase mapping
    private constructor() : this("",
        "",
        0,
        null,
        null,
        null)
}

val DemoProgram = Program(
    title = "Officiële opening",
    key = "abcdlfa",
    description = "Officiële opening van Gramsbergen Lichtstad 2022",
    location = Location("Vijver"),
    time = 1661666400000L,
    imageUrl = "https://lichtstad-prd.s3.eu-west-1.amazonaws.com/program/2022/6481c559/1662976806126.jpg",
)