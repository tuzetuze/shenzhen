package com.shenzhen.housing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.shenzhen.housing.ui.navigation.HousingNavigation
import com.shenzhen.housing.ui.theme.ShenzhenHousingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShenzhenHousingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HousingNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
