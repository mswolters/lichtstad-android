package nl.drbreakalot.lichtstad.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(val name: String): Parcelable {
    // No-arg constructor is required for automatic firebase mapping
    private constructor() : this("")
}
