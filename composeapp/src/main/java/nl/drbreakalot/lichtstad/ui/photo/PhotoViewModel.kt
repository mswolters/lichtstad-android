package nl.drbreakalot.lichtstad.ui.photo

import nl.drbreakalot.lichtstad.data.service.PhotoService

class PhotoViewModel(val service: PhotoService) : androidx.lifecycle.ViewModel() {

    val years = service.years

    fun albums(year: Int) = service.albums(year)

}