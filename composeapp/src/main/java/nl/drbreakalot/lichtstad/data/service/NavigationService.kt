package nl.drbreakalot.lichtstad.data.service

import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.Flow

interface NavigationService {

    val navigation: Flow<NavigationData>

    suspend fun navigate(route: String, navOptions: NavOptions? = null)

}

data class NavigationData(val route: String, val navOptions: NavOptions? = null)