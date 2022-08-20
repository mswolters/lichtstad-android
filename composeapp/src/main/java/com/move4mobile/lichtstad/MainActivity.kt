@file:OptIn(ExperimentalMaterial3Api::class)

package com.move4mobile.lichtstad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.move4mobile.lichtstad.ui.i18n.Translations
import com.move4mobile.lichtstad.ui.main.MainContent
import com.move4mobile.lichtstad.ui.theme.ResultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Translations {
                ResultTheme {
                    MainContent()
                }
            }
        }
    }
}
