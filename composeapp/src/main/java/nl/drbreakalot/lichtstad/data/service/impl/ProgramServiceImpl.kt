package nl.drbreakalot.lichtstad.data.service.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.LocalDate
import nl.drbreakalot.lichtstad.data.model.DemoProgram
import nl.drbreakalot.lichtstad.data.model.Program
import nl.drbreakalot.lichtstad.data.service.ProgramService

class ProgramServiceImpl : ProgramService {

    override val days: Flow<List<LocalDate>>
        get() = flowOf(
            listOf(
                LocalDate(2022, 8, 26),
                LocalDate(2022, 8, 27),
                LocalDate(2022, 8, 28),
                LocalDate(2022, 8, 29),
                LocalDate(2022, 8, 30),
                LocalDate(2022, 8, 31),
                LocalDate(2022, 9, 1),
                LocalDate(2022, 9, 2),
                LocalDate(2022, 9, 3),
            )
        )

    override fun programs(day: LocalDate): Flow<List<Program>> {
        return flowOf(
            buildList {
                for (x in 0..4) {
                    add(DemoProgram.copy(key = day.toString() + x))
                }
            }
        )
    }

}