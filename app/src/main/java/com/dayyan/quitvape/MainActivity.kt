package com.dayyan.quitvape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dayyan.quitvape.navigation.AppNavigation
import com.dayyan.quitvape.ui.theme.QuitVapeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuitVapeTheme {
                AppNavigation()
            }
        }
    }
}