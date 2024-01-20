package nl.drbreakalot.lichtstad

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import nl.drbreakalot.lichtstad.data.service.MapService
import nl.drbreakalot.lichtstad.data.service.ProgramService
import nl.drbreakalot.lichtstad.data.service.VideoService
import nl.drbreakalot.lichtstad.data.service.impl.MapServiceImpl
import nl.drbreakalot.lichtstad.data.service.impl.ProgramServiceImpl
import nl.drbreakalot.lichtstad.data.service.impl.VideoServiceImpl
import nl.drbreakalot.lichtstad.ui.main.NavigationViewModel
import nl.drbreakalot.lichtstad.ui.map.MapViewModel
import nl.drbreakalot.lichtstad.ui.program.ProgramViewModel
import nl.drbreakalot.lichtstad.ui.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single { Firebase.database }
    singleOf(::ProgramServiceImpl) { bind<ProgramService>() }
    singleOf(::VideoServiceImpl) { bind<VideoService>() }
    singleOf(::MapServiceImpl) { bind<MapService>() }
    viewModelOf(::ProgramViewModel)
    viewModelOf(::VideoViewModel)
    viewModelOf(::MapViewModel)
    viewModelOf(::NavigationViewModel)
}