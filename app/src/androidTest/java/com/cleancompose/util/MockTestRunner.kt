package com.cleancompose.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.cleancompose.application.CleanComposeApplication

class MockTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, CleanComposeApplication::class.java.name, context)
    }
}
