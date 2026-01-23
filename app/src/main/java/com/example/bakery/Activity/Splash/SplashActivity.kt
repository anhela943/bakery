package com.example.bakery.Activity.Splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakery.Activity.BaseActivity
import com.example.bakery.Activity.Dashboard.MainActivity
import com.example.bakery.R

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen(
                onClick = {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            )
        }
    }
}

@Composable
@Preview
fun SplashScreen(onClick:() -> Unit={}){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.brown))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(top=48.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Text(text="Satisfy Your Cravings with our\n" +
                " Fresh Cakes, Donuts\n" +
                " and Pastries",
            color = colorResource(R.color.darkBrown),
            textAlign = TextAlign.Center, fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(text="Browse the best edibles from top seller\n" +
                " Get personalized recommendations\n" +
                " Enjoy fast, free shipping",
            color = colorResource(R.color.darkBrown),
            textAlign = TextAlign.Center, fontSize = 16.sp,
            lineHeight = 30.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(onClick = {onClick()},
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.green)
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(50.dp)
        ){
            Text(
                text = "Let`s Get Startes",
                fontSize = 18.sp,
                color = Color.White
            )
        }
        Text(
            text = "Already have an account? Sign in",
            color = colorResource(R.color.darkBrown),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }

}