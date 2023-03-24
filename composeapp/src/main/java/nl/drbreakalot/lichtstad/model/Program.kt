package nl.drbreakalot.lichtstad.model

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