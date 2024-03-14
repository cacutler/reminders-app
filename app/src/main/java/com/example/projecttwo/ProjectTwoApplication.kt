package com.example.projecttwo
import android.app.Application
class ProjectTwoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ReminderRepository.initialize(this)
    }
}