package com.example.quiz_app.quizConfig

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz_app.R
import com.example.quiz_app.userName.shapes

@Composable
fun QuizConfigScreen(onReturnClick: () -> Unit = {}, onModeSelect: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Button(
            onClick = { onReturnClick() },
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(
                    color = colorResource(id = R.color.darkBlue),
                    shape = shapes.extraLarge
                ),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue))
        ) {
            Image(painter = painterResource(id = R.drawable.returnicon), contentDescription = "Return", colorFilter = ColorFilter.tint(
                colorResource(id = R.color.white)))
        }
    }
    Row(
       verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        ModeButton(txt = "Timed")
        Spacer(modifier = Modifier.width(20.dp))
        ModeButton(txt = "Infinite")
    }
}

@Composable
fun ModeButton(txt: String, modeSelect: () -> Unit = {}) {
    Box {
        Button(
            onClick = { modeSelect() },
            modifier = Modifier.width(160.dp).height(80.dp)
                .border(2.dp, colorResource(id = R.color.lightBlue), shapes.extraLarge)
                .background(
                    color = colorResource(id = R.color.darkBlue),
                    shape = shapes.extraLarge
                ),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue))
        ) {
            Text(
                text = txt,
                color = colorResource(id = R.color.white),
                fontSize = 24.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}