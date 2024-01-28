package nl.drbreakalot.lichtstad.data.service.impl

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import nl.drbreakalot.lichtstad.data.model.Result
import nl.drbreakalot.lichtstad.data.service.ResultService

class ResultServiceImpl(private val database: FirebaseDatabase) : ResultService {
    override val years: Flow<List<Int>>
        get() = flowOf(listOf(2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023))

    override fun results(year: Int): Flow<List<Result>> {
        val reference = database.getReference("result")
            .child("$year")

        return callbackFlow {
            val listener = reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val snapshots = snapshot.children.map { it.getValue<Result>()!!.apply { key = it.key!! } }
                    trySend(snapshots)
                }

                override fun onCancelled(error: DatabaseError) {
                    cancel()
                }

            })
            awaitClose { reference.removeEventListener(listener) }
        }
    }
}