package nl.drbreakalot.lichtstad.ui.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import nl.drbreakalot.lichtstad.data.service.MapService

class MapViewModel(application: Application, service: MapService) : AndroidViewModel(application) {

    val layers: Flow<Map<String, String>> = service.layers(2023)

}