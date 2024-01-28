package nl.drbreakalot.lichtstad.data.service.impl

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.drbreakalot.lichtstad.data.model.Result
import nl.drbreakalot.lichtstad.data.service.ResultService
import nl.drbreakalot.lichtstad.data.service.flow

class ResultServiceImpl(private val database: FirebaseDatabase) : ResultService {
    override val years: Flow<List<Int>>
        get() = flowOf(listOf(2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023))

    override fun results(year: Int): Flow<List<Result>> {
        val reference = database.getReference("result")
            .child("$year")

        return flow(reference) { snapshot -> snapshot.children.map { it.getValue<Result>()!!.apply { key = it.key!! } } }
    }
}