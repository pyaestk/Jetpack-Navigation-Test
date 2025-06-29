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
import androidx.navigation.navigation
import com.example.navigationtest.ui.theme.NavigationTestTheme

class MainActivity : ComponentActivity() {
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
                            val viewModel: SharedViewModel = it.sharedViewModel(navController = navController)
                            ScreenA(
                                navigateToB = {
                                    viewModel.person = it
                                    navController.navigate("screen_b")
                                }
                            )
                        }

                        composable(
                            route = "screen_b",
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
                            val viewModel: SharedViewModel = it.sharedViewModel(navController = navController)
                            viewModel.person?.let {
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
//                            ScreenB(
//                                navigateToC = {
//                                    navController.navigate("screen_c")
//                                },
//                                navigateBack = {
//                                    navController.popBackStack()
//                                },
//                                person = viewModel.person
//                            )

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
