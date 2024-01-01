package nl.drbreakalot.lichtstad.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalTime
import nl.drbreakalot.lichtstad.R
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

// Look at this again when kotlin's localdatetime supports formatting.
// For now, delegate to java
@Composable
fun LocalTime.formatHoursMinutes(): String {
    val format = stringResource(R.string.format_time)
    val formatter = remember(format) { DateTimeFormatter.ofPattern(format) }
    return formatter.format(this.toJavaLocalTime())
}
@Composable
fun Date.formatHoursMinutes(): String {
    val format = stringResource(R.string.format_time)
    val formatter = remember(format) { DateTimeFormatter.ofPattern(format) }
    return formatter.format(this.toInstant().atZone(ZoneId.systemDefault()))
}

@Composable
fun LocalDate.formatTabDate(): String {
    val format = stringResource(R.string.format_tab_date)
    val locale = stringResource(R.string.format_locale)
    val formatter = remember(format) { DateTimeFormatter.ofPattern(format, Locale.forLanguageTag(locale)) }
    return formatter.format(this.toJavaLocalDate())
}