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
import nl.drbreakalot.lichtstad.data.service.MapService

class MapServiceImpl(private val database: FirebaseDatabase) : MapService {
    override fun route(year: Int): Flow<String> {
        val reference = database.getReference("map")
            .child(year.toString())
            .child("route")
        return callbackFlow {
            trySend("")
            val listener = reference.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    trySend(snapshot.getValue<String>().orEmpty())
                }

                override fun onCancelled(error: DatabaseError) {
                    cancel()
                }

            })
            awaitClose { reference.removeEventListener(listener) }
        }
    }
}