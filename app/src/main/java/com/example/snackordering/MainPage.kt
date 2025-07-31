package com.example.snackordering

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.snackordering.ui.theme.SnackOrderingTheme

import android.content.Intent as Intent1


class MainPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackOrderingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FinalView(this)
                    val context = LocalContext.current
                    //PopularFoodColumn(context)
                }
            }
        }
    }
}

@Composable
fun TopPart() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Menu, contentDescription = "Menu Icon", tint = Color.Black)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Location", style = MaterialTheme.typography.subtitle2, color = Color.Gray)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Red)
                Text("Kolhapur", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
        Icon(Icons.Default.Notifications, contentDescription = "Notification Icon", tint = Color.Black)
    }
}

@Composable
fun CardPart() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp
    ) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.SpaceAround) {
                Text("Get Special Discounts", style = MaterialTheme.typography.h6)
                Text("Up to 85% OFF", style = MaterialTheme.typography.h6 , color = Color.Red)
                Button(onClick = {}, shape = RoundedCornerShape(8.dp)) {
                    Text("Claim Voucher")
                }
            }
            Image(
                painter = painterResource(id = R.drawable.pasta),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun PopularFood(@DrawableRes drawable: Int, @StringRes text1: Int, context: Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
                Spacer(modifier = Modifier.width(4.dp))
                Text("4.3", fontWeight = FontWeight.Bold)
            }
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = text1), style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("$50", style = MaterialTheme.typography.h6)
                IconButton(onClick = {
                    val intent = Intent1(context, TargetActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Add to Cart")
                }
            }
        }
    }
}

private val FoodList = listOf(
    R.drawable.burger to R.string.burgers,
    R.drawable.pack to R.string.pack,
    R.drawable.salad to R.string.salad,
    R.drawable.popcorn to R.string.popcorn
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text1: Int
)

@Composable
fun App(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff8f9fa))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopPart()
        Spacer(modifier = Modifier.height(16.dp))
        CardPart()
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Popular Food", style = MaterialTheme.typography.h6)
            Text("View All", style = MaterialTheme.typography.body2, color = Color.Blue)
        }
        Spacer(modifier = Modifier.height(16.dp))
        PopularFoodColumn(context)
    }
}

@Composable
fun PopularFoodColumn(context: Context) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(FoodList) { item ->
            PopularFood(context = context, drawable = item.drawable, text1 = item.text1)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FinalView(mainPage: MainPage) {
    SnackOrderingTheme {
        Scaffold {
            val context = LocalContext.current
            App(context)
        }
    }
}
