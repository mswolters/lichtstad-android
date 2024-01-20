package nl.drbreakalot.lichtstad.ui.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import nl.drbreakalot.lichtstad.data.service.MapService

class MapViewModel(application: Application, private val service: MapService) : AndroidViewModel(application) {

    val route: Flow<String> = service.route(2023)

}