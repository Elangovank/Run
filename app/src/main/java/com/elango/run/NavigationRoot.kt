package com.elango.run

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.elango.auth.presentation.intro.IntroScreenRoot
import com.elango.auth.presentation.login.LoginScreenRoot
import com.elango.auth.presentation.register.RegisterScreenRoot
import com.elango.run.presentation.active_run.ActiveRunScreenRoot
import com.elango.run.presentation.run_overview.RunOverviewAction
import org.w3c.dom.Text


@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(navController = navController, startDestination = if (!isLoggedIn) "auth" else "run") {
        authGraph(navController)
        runGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(startDestination = "intro", route = "auth") {
        composable(route = "intro") {
            IntroScreenRoot(
                onSignIn = {
                    navController.navigate(route = "login")
                },
                onSignUp = {
                    navController.navigate(route = "signup")
                }
            )
        }

        composable(route = "signup") {
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate(route = "login") {
                        popUpTo("signup") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate(route = "intro")
                }
            )
        }

        composable(route = "login") {
            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate(route = "run") {
                        popUpTo(route = "auth") {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = {
                    navController.navigate(route = "signup") {
                        popUpTo(route = "login") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.runGraph(navController: NavController) {
    navigation(startDestination = "auth_overview", route = "run") {
        composable(route = "auth_overview") {
            RunOverviewAction(onStartClick = {
                navController.navigate("active_run")
            })
        }
        composable(route = "active_run") {
            ActiveRunScreenRoot()
        }
    }
}