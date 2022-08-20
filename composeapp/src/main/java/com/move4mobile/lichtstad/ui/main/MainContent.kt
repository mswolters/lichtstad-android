package com.move4mobile.lichtstad.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.move4mobile.lichtstad.ui.theme.LichtstadTheme
import com.move4mobile.lichtstad.ui.theme.ProgramTheme

@ExperimentalMaterial3Api
@Composable
fun MainContent() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
    ) { paddingValues ->
        Surface(Modifier.padding(paddingValues)) {

        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun TopBar() {
    SmallTopAppBar(
        title = { Text("Hai") },
        navigationIcon = {
            Icon(
                painter = LichtstadTheme.iconSet.program(),
                contentDescription = null
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
    )
}

@Composable
private fun BottomBar() {
    NavigationBar {
        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                painter = LichtstadTheme.iconSet.program(),
                contentDescription = null
            )
        })
        NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
            Icon(
                painter = LichtstadTheme.iconSet.result(),
                contentDescription = null
            )
        }, label = { Text("active") })
        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                painter = LichtstadTheme.iconSet.photo(),
                contentDescription = null
            )
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProgramTheme {
        MainContent()
    }
}