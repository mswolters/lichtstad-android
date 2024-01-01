package nl.drbreakalot.lichtstad

import android.app.Application
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LichtstadApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        startKoin {
            androidLogger()
            androidContext(this@LichtstadApplication)
            modules(appModule)
        }
    }
}
