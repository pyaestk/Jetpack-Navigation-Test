package com.example.navigationtest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(
    person: Person?,
    navigateToC: () -> Unit,
    navigateBack: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Screen B")})
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .background(
                    color = colorResource(R.color.teal_200)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(text = person.toString())

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = navigateToC
            ) {
                Text("Go to C")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = navigateBack
            ) {
                Text("Go to A")
            }
        }
    }

}