package nl.drbreakalot.lichtstad.data.service

import kotlinx.coroutines.flow.Flow
import nl.drbreakalot.lichtstad.data.model.Album

interface PhotoService {

    val years: Flow<List<Int>>

    fun albums(year: Int): Flow<List<Album>>

}