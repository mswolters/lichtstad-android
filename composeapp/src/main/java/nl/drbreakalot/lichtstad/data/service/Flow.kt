package nl.drbreakalot.lichtstad.data.service

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


fun <T> flow(
    reference: DatabaseReference,
    mapFunction: (DataSnapshot) -> T
): Flow<T> {
    return callbackFlow {
        val listener = reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = mapFunction(snapshot)
                trySend(value)
            }

            override fun onCancelled(error: DatabaseError) {
                cancel()
            }

        })
        awaitClose { reference.removeEventListener(listener) }
    }
}