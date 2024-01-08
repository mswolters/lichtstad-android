package nl.drbreakalot.lichtstad.ui.program

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import nl.drbreakalot.lichtstad.data.model.Program
import nl.drbreakalot.lichtstad.data.service.ProgramService

class ProgramViewModel(private val service: ProgramService) : ViewModel() {

    val days = service.days
    fun programs(day: LocalDate): Flow<List<Program>> {
        return service.programs(day).map { list -> list.sortedBy { it.timeAsDate } }
    }

    fun defaultPage(days: List<LocalDate>): Int =
        days.mapIndexed { index, localDate -> index to localDate }
            .filter { (_, date) ->
                Clock.System.now()
                    .toLocalDateTime(TimeZone.currentSystemDefault()).date == date
            }.map { (index, _) -> index }
            .firstOrNull() ?: 0


}