package nl.drbreakalot.lichtstad.data.service.impl

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.LocalDate
import nl.drbreakalot.lichtstad.data.model.DemoProgram
import nl.drbreakalot.lichtstad.data.model.Program
import nl.drbreakalot.lichtstad.data.service.ProgramService

class ProgramServiceImpl : ProgramService {

    override val days: Flow<List<LocalDate>>
        get() = flowOf(
            listOf(
                LocalDate(2023, 8, 25),
                LocalDate(2023, 8, 26),
                LocalDate(2023, 8, 27),
                LocalDate(2023, 8, 28),
                LocalDate(2023, 8, 29),
                LocalDate(2023, 8, 30),
                LocalDate(2023, 8, 31),
                LocalDate(2023, 9, 1),
                LocalDate(2023, 9, 2),
            )
        )

    override fun programs(day: LocalDate): Flow<List<Program>> {
        val dayReference = Firebase.database
            .getReference("program_v2")
            .child("2023")
            .child("$day")
            .child("programs")

        return callbackFlow {
            val listener = dayReference.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val snapshots = snapshot.getValue<Map<String, Program>>() ?: emptyMap()
                    trySend(snapshots.values.toList().sortedBy { it.timeAsDate })
                }

                override fun onCancelled(error: DatabaseError) {
                    cancel()
                }

            })
            awaitClose { dayReference.removeEventListener(listener) }
        }
    }

}