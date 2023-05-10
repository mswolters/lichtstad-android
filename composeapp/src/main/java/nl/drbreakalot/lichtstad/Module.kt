package nl.drbreakalot.lichtstad

import nl.drbreakalot.lichtstad.data.service.ProgramService
import nl.drbreakalot.lichtstad.data.service.impl.ProgramServiceImpl
import nl.drbreakalot.lichtstad.ui.main.NavigationViewModel
import nl.drbreakalot.lichtstad.ui.program.ProgramViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::ProgramServiceImpl) { bind<ProgramService>() }
    viewModelOf(::ProgramViewModel)
    viewModelOf(::NavigationViewModel)
}