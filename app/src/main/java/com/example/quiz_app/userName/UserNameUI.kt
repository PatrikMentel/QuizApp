package com.example.quiz_app.userName

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz_app.AppData
import com.example.quiz_app.R

val shapes = Shapes(
    extraSmall = RoundedCornerShape(12.dp),
    small = RoundedCornerShape(28.dp),
    medium = RoundedCornerShape(40.dp),
    large = RoundedCornerShape(52.dp),
    extraLarge = RoundedCornerShape(72.dp)
)

@Composable
fun UserNameScreen(onPlayClick: () -> Unit = {}) {
    val userNameField = remember {mutableStateOf("")}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Image(painter = painterResource(id = R.drawable.user_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(0.dp, 100.dp, 50.dp, 40.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .scale(1.6f)
            )
        }
        Row(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(30.dp, 60.dp, 20.dp, 60.dp)
        ) {
            TextField(
                value = userNameField.value,
                onValueChange = { userNameField.value = it },
                colors = TextFieldDefaults.colors(colorResource(id = R.color.darkBlue), colorResource(id = R.color.darkBlue), colorResource(id = R.color.darkBlue)),
                modifier = Modifier
                    .width(320.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .background(
                        color = colorResource(R.color.darkBlue)
                    )
                    .border(
                        2.dp,
                        colorResource(id = R.color.lightBlue),
                        MaterialTheme.shapes.extraSmall
                    ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(text = stringResource(R.string.userName), color = colorResource(R.color.white))
                },
                textStyle = TextStyle(color = colorResource(R.color.white), background = colorResource(R.color.darkBlue))
            )
        }
        Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
            val sharedPreferences = LocalContext.current.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            Button(
                onClick = { saveAndPlay(userNameField.value, sharedPreferences, onPlayClick) },
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.darkBlue),
                        shape = shapes.extraLarge
                    )
                    .width(300.dp)
                    .height(100.dp)
                    .border(2.dp, colorResource(id = R.color.lightBlue), shapes.extraLarge),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue),
                    contentColor = colorResource(id = R.color.darkBlue),
                    disabledContainerColor = colorResource(id = R.color.darkBlue),
                    disabledContentColor = colorResource(id = R.color.darkBlue))
            ) {
                Text(
                    text = stringResource(id = R.string.play),
                    color = colorResource(id = R.color.white),
                    fontSize = 32.sp
                )
            }
        }
    }
}

fun saveAndPlay(username: String, sharedPreferences: SharedPreferences, onPlayClick: () -> Unit = {}) {
    val editor = sharedPreferences.edit()
    editor.putString("username", username)
    editor.putString("coins", "100")
    AppData.coins = 100
    editor.apply()
    onPlayClick()
}