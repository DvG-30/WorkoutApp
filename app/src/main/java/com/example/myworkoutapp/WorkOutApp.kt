package com.example.myworkoutapp

import android.app.Application

class WorkOutApp: Application() {
    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}