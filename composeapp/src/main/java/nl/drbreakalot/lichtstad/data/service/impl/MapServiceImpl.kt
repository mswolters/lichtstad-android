package nl.drbreakalot.lichtstad.data.service.impl

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.Flow
import nl.drbreakalot.lichtstad.data.service.MapService
import nl.drbreakalot.lichtstad.data.service.flow

class MapServiceImpl(private val database: FirebaseDatabase) : MapService {
    override fun layers(year: Int): Flow<Map<String, String>> {
        val reference = database.getReference("map")
            .child(year.toString())

        return flow(reference) { snapshot -> snapshot.getValue<Map<String, String>>().orEmpty() }
    }
}