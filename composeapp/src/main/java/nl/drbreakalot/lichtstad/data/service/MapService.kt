package nl.drbreakalot.lichtstad.data.service

import kotlinx.coroutines.flow.Flow

interface MapService {

    fun layers(year: Int): Flow<Map<String, String>>

}