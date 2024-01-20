package nl.drbreakalot.lichtstad.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import nl.drbreakalot.lichtstad.R

@Composable
fun MapContent(modifier: Modifier = Modifier,) {
    val latLng = LatLng(stringResource(R.string.map_center_lat).toDouble(), stringResource(R.string.map_center_lng).toDouble())
    val zoom = stringResource(R.string.map_zoom).toFloat()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(latLng, zoom, 0.0f, 0.0f)
    }
    GoogleMap(modifier = modifier, cameraPositionState = cameraPositionState) {

    }
}