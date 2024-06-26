package com.example.quiz_app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quiz_app.home.HomeScreen
import com.example.quiz_app.quiz.QuizScreen
import com.example.quiz_app.quizComplete.QuizCompleteScreen
import com.example.quiz_app.shop.ShopScreen
import com.example.quiz_app.userName.UserNameScreen
import com.example.quiz_app.ui.theme.Quiz_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainPreview()
        }
    }
}

@Composable
fun Background() {
    Image(painter = painterResource(R.drawable.bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize().scale(1.0f, 1.1f)
    )
}
@Preview
@Composable
fun MainPreview() {
    Quiz_AppTheme {
        Background()
        Box(modifier = Modifier.fillMaxSize()) {
            QuizAppStart()
        }
    }
}

enum class ScreenNav(@StringRes val title:Int)
{
    Start(title =  R.string.start_screen),
    Home(title =  R.string.home_screen),
    Shop(title =  R.string.shop_screen),
    Quiz(title =  R.string.quiz_screen),
    QuizComplete(title = R.string.quiz_complete)
}

@Composable
fun QuizAppStart(navController: NavHostController = rememberNavController())
{
    val sharedPreferences = LocalContext.current.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val username = sharedPreferences.getString("username", null)
    var startScreen = ScreenNav.Start.name
    if (username != null) {
        startScreen = ScreenNav.Home.name
        val coins = sharedPreferences.getString("coins", null)
        AppData.userName = username
        if (coins != null) {
            AppData.coins = coins.toInt()
        }
    }

    NavHost(
        navController = navController,
        startDestination = startScreen
    ) {
        composable (route = ScreenNav.Start.name)
        {
            UserNameScreen(onPlayClick = { navigateTo(navController, ScreenNav.Home.name)})
        }
        composable (route = ScreenNav.Home.name)
        {
            HomeScreen(
                onShopClick = { navigateTo(navController, ScreenNav.Shop.name) },
                onQuizSelect = { navigateTo(navController, ScreenNav.Quiz.name) }
            )
        }
        composable (route = ScreenNav.Shop.name)
        {
            ShopScreen(onHomeClick = { navigateTo(navController, ScreenNav.Home.name) })
        }
        composable (route = ScreenNav.Quiz.name)
        {
            QuizScreen(onQuizComplete = { navigateTo(navController, ScreenNav.QuizComplete.name)})
        }
        composable (route = ScreenNav.QuizComplete.name)
        {
            QuizCompleteScreen(onContinueClick = { navigateTo(navController, ScreenNav.Home.name)})
        }
    }
}

fun navigateTo(
    navController: NavHostController,
    name: String
)
{
    navController.navigate(name)
}