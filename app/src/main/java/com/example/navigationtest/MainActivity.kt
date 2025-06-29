package com.example.navigationtest

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
import com.example.navigationtest.ui.theme.NavigationTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTestTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "screen_a"){
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
                            navigateToB = {
//                                navController.navigate("screen_b/$it")
                                navController.navigate("screen_b?text=$it")
                            }
                        )
                    }

                    composable(
//                        route = "screen_b/{text}",
                        route = "screen_b?text={text}",
                        arguments = listOf(navArgument("text"){
                            type = NavType.StringType
                            nullable = true
                            defaultValue = "Unknown"
                        }),
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
                        //val text = it.arguments?.getString("text")
                        ScreenB(
                            navigateToC = {
                                navController.navigate("screen_c")
                            },
                            navigateBack = {
                                navController.popBackStack()
                            },
                            text = it.arguments?.getString("text")?: "Unknown"
                        )
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
