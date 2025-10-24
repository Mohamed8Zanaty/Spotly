package com.creator.spotly.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry

import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.creator.spotly.LoginScreen
import com.creator.spotly.ui.components.BottomNavBar
import com.creator.spotly.ui.components.Tab
import com.creator.spotly.ui.screens.home.HomeScreen
import com.creator.spotly.ui.screens.messages.MessagesScreen
import com.creator.spotly.ui.screens.notifications.NotificationScreen
import com.creator.spotly.ui.screens.search.SearchScreen
import com.creator.spotly.ui.screens.startup.WelcomeScreen


@Composable
fun NavigationRoot() {
    val authBackstack = rememberNavBackStack(WelcomeScreen)

    val homeStack = rememberNavBackStack(HomeScreen)
    val searchStack = rememberNavBackStack(SearchScreen)
    val messagesStack = rememberNavBackStack(MessagesScreen)

    var isLoggedIn by remember { mutableStateOf(false) }
    var selectedTab by rememberSaveable { mutableStateOf(Tab.HOME) }

    val tabToStack: (Tab) -> NavBackStack = { tab ->
        when (tab) {
            Tab.HOME -> homeStack
            Tab.SEARCH -> searchStack
            Tab.MESSAGES -> messagesStack
            else -> throw IllegalArgumentException("Invalid tab: $tab")

        }
    }



    if(!isLoggedIn) {
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
                            onLoginClick = { isLoggedIn = true }
                        )
                    }
                    else -> throw IllegalArgumentException("Invalid key: $key")
                }
            }
        )
    } else {
        val currentStack = tabToStack(selectedTab)

        Scaffold(
            bottomBar = {
                BottomNavBar(
                    selected = selectedTab,
                    onTabSelected = { tab ->
                        selectedTab = tab
                    }
                )
            }
        ) {
            innerPadding ->
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
                    when(key) {
                        is HomeScreen -> NavEntry(key = key) {
                            HomeScreen(
                                contentPadding = innerPadding,
                                onNotificationsButtonClick = { homeStack.add(NotificationScreen) }
                            )
                        }
                        is SearchScreen -> NavEntry(key = key) {
                            SearchScreen(
                                onBack = { currentStack.removeLastOrNull() },
                            )
                        }
                        is MessagesScreen -> NavEntry(key = key) {
                            MessagesScreen(
                                contentPadding = innerPadding
                            )
                        }
                        is NotificationScreen -> NavEntry(key = key) {
                            NotificationScreen(
                                onBack = { currentStack.removeLastOrNull() },
                            )
                        }
                        else -> throw IllegalArgumentException("Invalid key: $key")
                    }
                }
            )

        }
    }

}