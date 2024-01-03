package nl.drbreakalot.lichtstad.data.service

import kotlinx.coroutines.flow.Flow
import nl.drbreakalot.lichtstad.data.model.Video

interface VideoService {

    val years : Flow<List<Int>>

    fun videos(year: Int): Flow<List<Video>>

}