package nl.drbreakalot.lichtstad.ui.photo

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import kotlinx.coroutines.launch
import nl.drbreakalot.lichtstad.data.model.Album
import nl.drbreakalot.lichtstad.data.service.NavigationService
import nl.drbreakalot.lichtstad.data.service.PhotoService
import nl.drbreakalot.lichtstad.ui.main.ROUTE_PHOTOS
import nl.drbreakalot.lichtstad.ui.main.routeAlbum

class PhotoViewModel(private val service: PhotoService, private val navigationService: NavigationService) : androidx.lifecycle.ViewModel() {

    val years = service.years

    fun albums(year: String) = service.albums(year)

    fun photos(year: String, albumKey: String) = service.photos(year, albumKey)

    fun onClicked(album: Album) {
        navigationService.navigate(routeAlbum(album.year, album.key))
    }

}