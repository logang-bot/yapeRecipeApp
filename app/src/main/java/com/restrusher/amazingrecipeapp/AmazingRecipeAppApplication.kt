package com.restrusher.amazingrecipeapp

import android.app.Application
import com.restrusher.amazingrecipeapp.data.AppContainer
import com.restrusher.amazingrecipeapp.data.DefaultAppContainer

class AmazingRecipeAppApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}