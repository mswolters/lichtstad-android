package nl.drbreakalot.lichtstad.ui.program

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import nl.drbreakalot.lichtstad.data.model.Program
import nl.drbreakalot.lichtstad.data.service.ProgramService

class ProgramViewModel(private val service: ProgramService) : ViewModel() {

    val days = service.days
    fun programs(day: LocalDate): Flow<List<Program>> {
        return service.programs(day)
    }

}