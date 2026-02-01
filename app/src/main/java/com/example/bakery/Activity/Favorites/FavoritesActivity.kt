package com.example.bakery.Activity.Favorites

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.bakery.Activity.BaseActivity
import com.example.bakery.Activity.Cart.CartActivity
import com.example.bakery.Activity.Dashboard.BottomMenu
import com.example.bakery.Activity.Dashboard.MainActivity
import com.example.bakery.Activity.Dashboard.ListItemsFullSizeVertical
import com.example.bakery.Activity.Profile.ProfileActivity
import com.example.bakery.Domain.ItemsModel
import com.example.bakery.R
import com.example.bakery.Repository.FavoritesRepository

class FavoritesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FavoritesScreen(
                onBackClick = { finish() },
                onCartClick = { startActivity(Intent(this, CartActivity::class.java)) },
                onExploreClick = { startActivity(Intent(this, MainActivity::class.java)) },
                onProfileClick = { startActivity(Intent(this, ProfileActivity::class.java)) }
            )
        }
    }
}

@Composable
private fun FavoritesScreen(
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
    onExploreClick: () -> Unit,
    onProfileClick: () -> Unit,
    favorites: List<ItemsModel> = FavoritesRepository.favorites
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (content, bottomMenu) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 16.dp, bottom = 96.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                val (backBtn, title) = createRefs()

                Text(
                    text = "Favorites",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(title) { centerTo(parent) }
                )

                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(60.dp)
                        .padding(top = 8.dp)
                        .constrainAs(backBtn) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                ) {
                    Image(
                        painter = painterResource(R.drawable.back),
                        contentDescription = null
                    )
                }
            }

            if (favorites.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "No favorites yet",
                        color = colorResource(R.color.midBrown),
                        fontSize = 14.sp
                    )
                }
            } else {
                ListItemsFullSizeVertical(items = favorites)
            }
        }

        BottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomMenu) {
                    bottom.linkTo(parent.bottom)
                },
            onExploreClick = onExploreClick,
            onItemClick = onCartClick,
            onFavoriteClick = null,
            onProfileClick = onProfileClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesScreenPreview() {
    val previewItems = listOf(
        ItemsModel(
            title = "Chocolate Cake",
            price = 12.5,
            rating = 4.7
        ),
        ItemsModel(
            title = "Strawberry Donut",
            price = 3.2,
            rating = 4.3
        )
    )

    FavoritesScreen(
        onBackClick = {},
        onCartClick = {},
        onExploreClick = {},
        onProfileClick = {},
        favorites = previewItems
    )
}
