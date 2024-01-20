package nl.drbreakalot.lichtstad.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.data.kml.KmlLayer
import nl.drbreakalot.lichtstad.R
import org.koin.androidx.compose.navigation.koinNavViewModel
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapContent(viewModel: MapViewModel = koinNavViewModel()) {
    val latLng = LatLng(stringResource(R.string.map_center_lat).toDouble(), stringResource(R.string.map_center_lng).toDouble())
    val zoom = stringResource(R.string.map_zoom).toFloat()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(latLng, zoom, 0.0f, 0.0f)
    }

    val route by viewModel.route.collectAsStateWithLifecycle("")
    var routeLayer: KmlLayer? = remember { null }

    GoogleMap(cameraPositionState = cameraPositionState) {
        MapEffect(route) {map ->
            routeLayer?.apply { removeLayerFromMap() }
            routeLayer = if (route.isEmpty()) null else KmlLayer(map, ByteArrayInputStream(route.toByteArray(StandardCharsets.UTF_8)), viewModel.getApplication())
            routeLayer?.apply { addLayerToMap() }
        }
    }
}