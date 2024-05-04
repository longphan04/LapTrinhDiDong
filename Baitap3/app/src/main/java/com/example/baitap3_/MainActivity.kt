package com.example.baitap3_

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.baitap3_.ui.theme.Baitap3_Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.coroutineScope

//import androidx.compose.ui.unit.sp
//import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baitap3_Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    val artArray = arrayOf(
        ImageDesc(R.drawable.img1, "Description for Image 1", author =  "Mai Nguyen", year =  2024 ),
        ImageDesc(R.drawable.img2, "Description for Image 2", author = "Thien Tran", year =  2020),
        ImageDesc(R.drawable.img3, "Description for Image 3", author = "Anh Le", year =  2022),
    )

    var currentImageID by remember{mutableStateOf(0)}
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            Modifier
                .background(Color.White)
                .shadow(4.dp)
        ) {
            Image(
                painter = painterResource(id = artArray[currentImageID].imageID),
                contentDescription = artArray[currentImageID].desc,
                modifier = Modifier
                    .width(240.dp)
                    .padding(28.dp),
                contentScale = ContentScale.Fit)
        }
        Spacer(Modifier.height(16.dp))
        Text(text =  "Description for Image 1")
        Spacer(Modifier.weight(1f))
        Row(
            Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
             Button(
                onClick = {
                    coroutineScope.launch{
                        currentImageID = (currentImageID - 1 + artArray.size) % artArray.size
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous image"
                )
            }

                Button(
                onClick = {
                    coroutineScope.launch {
                        currentImageID = (currentImageID + 1) % artArray.size
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next image"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = artArray[currentImageID].desc,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
    }
}

data class ImageDesc(
    val drawable: Int,
    val desc: String,
    val author: Any?,
    val year: Any?,
    val i: Int
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Baitap3_Theme {
        ArtSpace()
    }
}