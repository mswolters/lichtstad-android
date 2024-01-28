package nl.drbreakalot.lichtstad.data.service.impl

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.drbreakalot.lichtstad.data.model.Album
import nl.drbreakalot.lichtstad.data.service.PhotoService
import nl.drbreakalot.lichtstad.data.service.flow

class PhotoServiceImpl(private val database: FirebaseDatabase) : PhotoService {
    override val years: Flow<List<Int>>
        get() = flowOf(listOf(2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023))

    override fun albums(year: Int): Flow<List<Album>> {
        val reference = database.getReference("album")
            .child("$year")

        return flow(reference) { snapshot -> snapshot.children.map { it.getValue<Album>()!!.apply { key = it.key!! } } }
    }
}