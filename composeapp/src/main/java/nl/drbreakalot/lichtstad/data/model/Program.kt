package nl.drbreakalot.lichtstad.data.model

import android.os.Parcelable
import nl.drbreakalot.lichtstad.parcelable.LocalDateTimeParceler
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler

@Parcelize
@TypeParceler<LocalDateTime, LocalDateTimeParceler>
data class Program(
    val key: String,
    val title: String,
    val time: LocalDateTime,
    val description: String?,
    val location: Location?,
    val imageUrl: String?
): Parcelable

val DemoProgram = Program(
    title = "Officiële opening",
    key = "abcdlfa",
    description = "Officiële opening van Gramsbergen Lichtstad 2022",
    location = Location("Vijver"),
    time = LocalDateTime(2022, 8, 26, 20, 0),
    imageUrl = "https://lichtstad-prd.s3.eu-west-1.amazonaws.com/program/2022/6481c559/1662976806126.jpg",
)