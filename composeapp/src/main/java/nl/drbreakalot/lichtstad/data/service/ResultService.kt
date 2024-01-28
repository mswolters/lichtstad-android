package nl.drbreakalot.lichtstad.data.service

import kotlinx.coroutines.flow.Flow
import nl.drbreakalot.lichtstad.data.model.Result

interface ResultService {

    val years : Flow<List<Int>>

    fun results(year: Int): Flow<List<Result>>

}