package com.ahsanzahid.doordashlite

import android.app.Application
import com.ahsanzahid.doordashlite.network.DoorDashAPIService
import com.ahsanzahid.doordashlite.network.RestaurantsRepository
import com.ahsanzahid.doordashlite.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(appModule)
        }
    }
}

val appModule = module {
    single<DoorDashAPIService> {
        Retrofit.Builder()
            .baseUrl("https://api.doordash.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DoorDashAPIService::class.java)
    }


    // single instance of HelloRepository
    single { RestaurantsRepository(get()) }

    // MyViewModel ViewModel
    viewModel { MainViewModel(get()) }
}