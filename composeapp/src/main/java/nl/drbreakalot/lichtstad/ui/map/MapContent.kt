package nl.drbreakalot.lichtstad.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.data.kml.KmlLayer
import nl.drbreakalot.lichtstad.R
import nl.drbreakalot.lichtstad.res.floatResource
import org.koin.androidx.compose.navigation.koinNavViewModel
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapContent(viewModel: MapViewModel = koinNavViewModel()) {
    val mapBounds = LatLngBounds(
        LatLng(
            floatResource(R.dimen.map_min_lat).toDouble(),
            floatResource(R.dimen.map_min_lng).toDouble()
        ),
        LatLng(
            floatResource(R.dimen.map_max_lat).toDouble(),
            floatResource(R.dimen.map_max_lng).toDouble()
        )
    )

    val cameraCenter = LatLng(
        floatResource(R.dimen.map_default_center_lat).toDouble(),
        floatResource(R.dimen.map_default_center_lng).toDouble()
    )
    val cameraZoom = floatResource(R.dimen.map_default_zoom)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(cameraCenter, cameraZoom, 0.0f, 0.0f)
    }

    val layersText by viewModel.layers.collectAsStateWithLifecycle(emptyMap())
    val layers: MutableMap<String, KmlLayer> = remember { mutableMapOf() }

    val mapProperties = remember {
        MapProperties(
            latLngBoundsForCameraTarget = mapBounds,
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                viewModel.getApplication(),
                R.raw.map_style
            ),
        )
    }
    GoogleMap(cameraPositionState = cameraPositionState, properties = mapProperties) {
        MapEffect(layersText) { map ->
            layers.values.forEach { it.removeLayerFromMap() }
            layers.clear()
            layersText.forEach { (key, layerText) ->
                val layer = KmlLayer(
                    map,
                    ByteArrayInputStream(layerText.toByteArray(StandardCharsets.UTF_8)),
                    viewModel.getApplication()
                )
                layers[key] = layer
                layer.addLayerToMap()
            }
        }
    }
}