package com.example.nuntium.di

import android.app.Application

class NuntiumApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDefaultContainer(this)
    }
}