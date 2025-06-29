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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(
    navigateToB:(Person) -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Screen A")})
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .background(
                    color = colorResource(R.color.purple_200)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var name by remember {
                mutableStateOf("")
            }

            var age by remember {
                mutableStateOf("")
            }

            var id by remember {
                mutableStateOf("")
            }

            TextField(value = name, onValueChange = {
                name = it
            })

            Spacer(modifier = Modifier.height(10.dp))

            TextField(value = id, onValueChange = {id = it})

            Spacer(modifier = Modifier.height(10.dp))

            TextField(value = age, onValueChange = {age = it})

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navigateToB(
                        Person(
                            name = name,
                            id = id.toInt(),
                            age = age.toInt()
                        )
                    )
                }
            ) {
                Text("Go to B")
            }
        }
    }

}