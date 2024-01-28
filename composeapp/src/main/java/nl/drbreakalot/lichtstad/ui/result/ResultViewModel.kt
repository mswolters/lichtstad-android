package nl.drbreakalot.lichtstad.ui.result

import androidx.lifecycle.ViewModel
import nl.drbreakalot.lichtstad.data.service.ResultService

class ResultViewModel(private val service: ResultService) : ViewModel() {

    val years = service.years
    fun results(year: Int) = service.results(year)

}