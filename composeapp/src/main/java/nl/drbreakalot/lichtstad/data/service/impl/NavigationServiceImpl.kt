package nl.drbreakalot.lichtstad.data.service.impl

import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import nl.drbreakalot.lichtstad.data.service.NavigationData
import nl.drbreakalot.lichtstad.data.service.NavigationService
import nl.drbreakalot.lichtstad.ui.main.NAVIGATION_ITEMS

class NavigationServiceImpl : NavigationService {
    private val mutableNavigation = MutableStateFlow(NavigationData(NAVIGATION_ITEMS[0].route))
    override val navigation: Flow<NavigationData> = mutableNavigation

    override suspend fun navigate(route: String, navOptions: NavOptions?) {
        mutableNavigation.emit(NavigationData(route, navOptions))
    }
}