package nl.drbreakalot.lichtstad.data.service

import kotlinx.coroutines.flow.Flow
import nl.drbreakalot.lichtstad.data.model.Album
import nl.drbreakalot.lichtstad.data.model.Photo

interface PhotoService {

    val years: Flow<List<String>>

    fun albums(year: String): Flow<List<Album>>
    fun photos(year: String, albumKey: String): Flow<List<Photo>>

}