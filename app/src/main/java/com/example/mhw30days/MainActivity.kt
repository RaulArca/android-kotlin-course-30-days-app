package com.example.mhw30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mhw30days.model.Monster
import com.example.mhw30days.model.MonsterRepository.monsterList
import com.example.mhw30days.ui.theme.MHW30DaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val monster = Monster(
            descriptionRes = R.string.rathalos_desc,
            nameRes = R.string.rathalos_name,
            imageres = R.drawable.rathalos
        )

        setContent {
            MHW30DaysTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MonsterListView(contentPaddingValues = innerPadding)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MHW30DaysTheme {
        Greeting("Android")
    }
}