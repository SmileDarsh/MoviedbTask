package com.max.moviedbtask

import android.app.Application
import com.max.moviedbtask.core.utils.SharedPreferencesManager.init
import com.max.moviedbtask.core.utils.SharedPreferencesManager.saveToken
import com.max.moviedbtask.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        init(applicationContext)
        startKoin {
            androidContext(this@MovieApplication)
            androidLogger()
            modules(koinModules)
        }
    }
}