package com.move4mobile.lichtstad.model

import android.os.Parcelable
import com.move4mobile.lichtstad.parcelable.LocalDateTimeParceler
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler

@Parcelize
@TypeParceler<LocalDateTime, LocalDateTimeParceler>
data class Program(
    val key: String,
    val description: String?,
    val location: Location?,
    val time: LocalDateTime,
    val title: String,
    val imageUrl: String?
): Parcelable
