package nl.drbreakalot.lichtstad.ui

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalTime

@Composable
fun formatHoursMinutes(time: LocalTime): String {
    return "%02d:%02d".format(time.hour, time.minute)
}