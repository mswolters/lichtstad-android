package nl.drbreakalot.lichtstad.data.service

import kotlinx.coroutines.flow.Flow

interface MapService {

    fun route(year: Int): Flow<String>

}