package nl.drbreakalot.lichtstad.data.service.impl

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.drbreakalot.lichtstad.data.model.Album
import nl.drbreakalot.lichtstad.data.model.Photo
import nl.drbreakalot.lichtstad.data.service.PhotoService
import nl.drbreakalot.lichtstad.data.service.flow

class PhotoServiceImpl(private val database: FirebaseDatabase) : PhotoService {
    override val years: Flow<List<String>>
        get() = flowOf(listOf("2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023"))

    override fun albums(year: String): Flow<List<Album>> {
        val reference = database.getReference("album")
            .child(year)

        return flow(reference) { snapshot ->
            snapshot.children.map {
                it.getValue<Album>()!!.apply {
                    this.year = year
                    key = it.key!!
                }
            }
        }
    }

    override fun photos(year: String, albumKey: String): Flow<List<Photo>> {
        val query = database.getReference("album_content")
            .child(year)
            .child(albumKey)
            .orderByChild("order")

        return flow(query) { snapshot ->
            snapshot.children.map {
                it.getValue<Photo>()!!.apply { key = it.key!! }
            }
        }
    }
}