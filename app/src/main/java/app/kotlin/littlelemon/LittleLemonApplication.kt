package app.kotlin.littlelemon

import android.app.Application
import app.kotlin.littlelemon.data.AppContainer
import app.kotlin.littlelemon.data.DefaultAppContainer

class LittleLemonApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}