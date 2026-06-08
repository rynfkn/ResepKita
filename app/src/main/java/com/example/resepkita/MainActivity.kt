package com.example.resepkita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.resepkita.ui.RecipeViewModel
import com.example.resepkita.ui.navigation.NavGraph
import com.example.resepkita.ui.theme.ResepKitaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: RecipeViewModel = viewModel()

            ResepKitaTheme(darkTheme = viewModel.isDarkTheme) {
                NavGraph(viewModel = viewModel)
            }
        }
    }
}
