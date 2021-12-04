package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.overview.OverviewBody
import org.junit.Rule
import org.junit.Test
import java.util.*

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = {},
                currentScreen = RallyScreen.Accounts
            )
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase(Locale.getDefault())) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }


}

class OverviewScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun overviewScreen_alertsDisplayed() {
        composeTestRule.setContent {
            OverviewBody()
        }

        composeTestRule
            .onNodeWithText("Alerts")
            .assertIsDisplayed()
    }
}

class RallyTopAppBarTestClickTabs{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest_clickTabs() {
        var currentScreen:RallyScreen = RallyScreen.Overview
        composeTestRule.setContent {
            RallyApp(currentScreen = currentScreen, onTabSelected = {screen -> currentScreen = screen})
        }

        RallyScreen.values().forEach {
            composeTestRule
                .onNodeWithContentDescription(it.name)
                .performClick()
            assert(currentScreen == it)
        }
    }
}