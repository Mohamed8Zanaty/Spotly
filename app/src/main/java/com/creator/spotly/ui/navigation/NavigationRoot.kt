package com.creator.spotly.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
//import androidx.compose.runtime.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.creator.spotly.LoginScreen
import com.creator.spotly.ProfileScreen
import com.creator.spotly.auth.viewmodel.AuthViewModel
import com.creator.spotly.ui.components.BottomNavBar
import com.creator.spotly.ui.components.Tab
import com.creator.spotly.ui.navigation.utils.backButtonHandler
import com.creator.spotly.home.ui.HomeScreen
import com.creator.spotly.search.SearchScreen
import com.creator.spotly.auth.ui.signup.SignUpScreen
import com.creator.spotly.home.viewmodel.CategoriesViewModel
import com.creator.spotly.home.viewmodel.HomeViewModel
import com.creator.spotly.placeslist.PlacesListViewModel
import com.creator.spotly.profile.EditProfileScreen
import com.creator.spotly.profile.EditProfileViewModel
import com.creator.spotly.profile.ProfileViewModel
import com.creator.spotly.placeslist.PlacesScreen
import com.creator.spotly.ui.screens.startup.WelcomeScreen


@Composable
fun NavigationRoot() {
    // welcome/login/signup backstack
    val authBackstack = rememberNavBackStack(WelcomeScreen)

    val homeStack = rememberNavBackStack(HomeScreen)
    val searchStack = rememberNavBackStack(SearchScreen)
    val profileStack = rememberNavBackStack(ProfileScreen)

    // Hilt-provided AuthViewModel
    val authViewModel: AuthViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val editProfileViewModel: EditProfileViewModel = hiltViewModel()
    val categoriesViewModel: CategoriesViewModel = hiltViewModel()
    val placesListViewModel: PlacesListViewModel = hiltViewModel()



    val uid by authViewModel.uid.collectAsState()

    val isLoggedIn = uid != null
    var selectedTab by rememberSaveable { mutableStateOf(Tab.HOME) }



    LaunchedEffect(uid) {
        if (uid != null) {
            while (authBackstack.lastOrNull() != null) {
                authBackstack.removeLastOrNull()
            }
            homeStack.clear()
            homeStack.add(HomeScreen)

            searchStack.clear()
            searchStack.add(SearchScreen)


            profileStack.clear()
            profileStack.add(ProfileScreen)

        }
    }

    val tabToStack: (Tab) -> NavBackStack = { tab ->
        when (tab) {
            Tab.HOME -> homeStack
            Tab.SEARCH -> searchStack
            Tab.PROFILE -> profileStack
            else -> throw IllegalArgumentException("Invalid tab: $tab")
        }
    }

    if (!isLoggedIn) {
        NavDisplay(
            backStack = authBackstack,
            onBack = { authBackstack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
                rememberSceneSetupNavEntryDecorator()
            ),
            entryProvider = { key ->
                when (key) {
                    is WelcomeScreen -> NavEntry(key = key) {
                        WelcomeScreen(continueButtonOnClick = { authBackstack.add(LoginScreen) })
                    }
                    is LoginScreen -> NavEntry(key = key) {
                        LoginScreen(
                            viewModel = authViewModel,
                            onSignUpClick = { authBackstack.add(SignUpScreen) },
                        )
                    }
                    is SignUpScreen -> NavEntry(key = key) {
                        SignUpScreen(
                            viewModel = authViewModel,
                            onLoginClick = { authBackstack.add(LoginScreen) },
                        )
                    }
                    else -> throw IllegalArgumentException("Invalid key: $key")
                }
            }
        )
    } else {
        val currentStack = tabToStack(selectedTab)
        val topKey = currentStack.lastOrNull()
        val showBottomBar = when (topKey) {
            else -> true
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xffE7DEAF).copy(alpha = 0.5f)),
            bottomBar = {
                if (showBottomBar) {
                    BottomNavBar(
                        selected = selectedTab,
                        onTabSelected = { tab ->
                            selectedTab = tab
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavDisplay(
                modifier = Modifier.padding(innerPadding),
                backStack = currentStack,
                onBack = { currentStack.removeLastOrNull() },
                entryDecorators = listOf(
                    rememberSavedStateNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator(),
                    rememberSceneSetupNavEntryDecorator()
                ),
                entryProvider = { key ->
                    when (key) {
                        is HomeScreen -> NavEntry(key = key) {
                            HomeScreen(
                                homeViewModel = homeViewModel,
                                contentPadding = innerPadding,
                                categoriesViewModel = categoriesViewModel,
                                onCategoryClick = {
                                    homeStack.add(PlacesScreen(it.type, it.title, it.imageRes))
                                },
                                onProfileButtonClick = { selectedTab = Tab.PROFILE }
                            )
                        }
                        is SearchScreen -> NavEntry(key = key) {
                            SearchScreen()
                        }
                        is ProfileScreen -> NavEntry(key = key) {
                            ProfileScreen(
                                profileViewModel = profileViewModel,
                                onBackClick = { backButtonHandler(currentStack) },
                                onLogoutClick = {
                                    authViewModel.logOut()
                                    authBackstack.clear()
                                    selectedTab = Tab.HOME
                                    authBackstack.add(WelcomeScreen)
                                },
                                onEditClick = { profileStack.add(EditProfileScreen) }
                            )
                        }
                        is EditProfileScreen -> NavEntry(key = key) {
                            EditProfileScreen(
                                editViewModel = editProfileViewModel,
                                onBackClick = { backButtonHandler(currentStack) },
                                onDoneClick = { backButtonHandler(currentStack) }
                            )
                        }
                        is PlacesScreen -> NavEntry(key = key) {
                            PlacesScreen(
                                type = key.type,
                                title = key.title,
                                imageRes = key.imageRes,
                                vm = placesListViewModel,
                                onPlaceClick = {  },

                            )
                        }
                        else -> throw IllegalArgumentException("Invalid key: $key")
                    }
                }
            )
        }
    }
}
