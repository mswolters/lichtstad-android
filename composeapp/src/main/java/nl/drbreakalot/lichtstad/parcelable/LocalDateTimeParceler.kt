package nl.drbreakalot.lichtstad.parcelable

import android.os.Parcel
import nl.drbreakalot.lichtstad.parcelable.LocalDateParceler.write
import nl.drbreakalot.lichtstad.parcelable.LocalTimeParceler.write
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.parcelize.Parceler

object LocalDateTimeParceler : Parceler<LocalDateTime> {
    override fun create(parcel: Parcel): LocalDateTime =
        LocalDateTime(LocalDateParceler.create(parcel), LocalTimeParceler.create(parcel))

    override fun LocalDateTime.write(parcel: Parcel, flags: Int) {
        date.write(parcel, flags)
        time.write(parcel, flags)
    }
}

object LocalDateParceler : Parceler<LocalDate> {
    override fun create(parcel: Parcel): LocalDate = LocalDate(
        year = parcel.readInt(),
        monthNumber = parcel.readInt(),
        dayOfMonth = parcel.readInt()
    )

    override fun LocalDate.write(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeInt(year)
            writeInt(monthNumber)
            writeInt(dayOfMonth)
        }
    }

}

object LocalTimeParceler : Parceler<LocalTime> {
    override fun create(parcel: Parcel): LocalTime = LocalTime(
        hour = parcel.readInt(),
        minute = parcel.readInt(),
        second = parcel.readInt(),
        nanosecond = parcel.readInt()
    )

    override fun LocalTime.write(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeInt(hour)
            writeInt(minute)
            writeInt(second)
            writeInt(nanosecond)
        }
    }

}