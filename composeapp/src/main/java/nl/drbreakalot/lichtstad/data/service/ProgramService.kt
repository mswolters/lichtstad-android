package nl.drbreakalot.lichtstad.data.service

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import nl.drbreakalot.lichtstad.data.model.Program

interface ProgramService {

    val days: Flow<List<LocalDate>>

    fun programs(day: LocalDate): Flow<List<Program>>

}