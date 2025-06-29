package com.example.navigationtest

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.navigationtest.ui.theme.NavigationTestTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json



class MainActivity : ComponentActivity() {

    val personNavType = NavigationUtil.parcelableNavType<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTestTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main"){

                    navigation(
                        route = "main",
                        startDestination = "screen_a"
                    ){
                        composable(
                            route = "screen_a",
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(700)
                                )
                            }
                        ){

                            ScreenA(
                                navigateToB = { person ->
                                    val encodedPerson = Uri.encode(Json.encodeToString(person))
                                    navController.navigate("screen_b/${encodedPerson}")
                                }
                            )
                        }

                        composable(
                            route = "screen_b/{person}",
                            arguments = listOf(
                                navArgument("person"){
                                    type = personNavType
                                    nullable = true
                                }
                            ),
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(700)
                                )
                            }

                        ){
                            it.arguments?.getParcelable<Person>("person")?.let { it ->
                                ScreenB(
                                    navigateToC = {
                                        navController.navigate("screen_c")
                                    },
                                    navigateBack = {
                                        navController.popBackStack()
                                    },
                                    person = it
                                )
                            }


                        }

                        composable(
                            route = "screen_c"
                        ){
                            ScreenC(
                                navigateToA = {
                                    navController.popBackStack()
                                    // navController.popBackStack() //or
                                    navController.navigate("screen_a"){
                                        popUpTo(0)
                                    }
                                },
                                navigateBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }


                }
            }
        }
    }
}
