package nl.drbreakalot.lichtstad.data.service

import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.Flow

interface NavigationService {

    val navigation: Flow<NavigationData>

    fun navigate(route: String, navOptions: NavOptions? = null, isAutomaticAction: Boolean = false)

}

data class NavigationData(val route: String, val navOptions: NavOptions? = null, val isAutomaticAction: Boolean = false)