package com.example.bakery.Activity.Cart

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.bakery.Activity.BaseActivity
import com.example.bakery.Activity.Dashboard.BottomMenu
import com.example.bakery.Activity.Dashboard.MainActivity
import com.example.bakery.Activity.Profile.ProfileActivity
import com.example.bakery.Activity.Favorites.FavoritesActivity
import com.example.bakery.Helper.ManagmentCart
import com.example.bakery.R
import android.content.Intent

class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CartScreen(ManagmentCart(this),
                onBackClick = { finish() },
                onFavoriteClick = {
                    startActivity(Intent(this, FavoritesActivity::class.java))
                },
                onExploreClick = {
                    startActivity(Intent(this, MainActivity::class.java))
                },
                onProfileClick = {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
            )
        }
    }
}

@Composable
fun CartScreen(
    managmentCart: ManagmentCart = ManagmentCart(LocalContext.current),
    onBackClick: () -> Unit,
    onFavoriteClick: (() -> Unit)? = null,
    onExploreClick: (() -> Unit)? = null,
    onProfileClick: (() -> Unit)? = null
){
    var cartItems = remember {mutableStateOf(managmentCart.getListCart())}
    val tax = remember { mutableStateOf(0.0) }

    calculatorCart(managmentCart, tax)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
                .padding(bottom = 96.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.padding(top = 36.dp)
            ) {
                val(backBtn, cartTxt) = createRefs()

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(cartTxt){centerTo(parent)},
                    text = "Your Cart",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(backBtn){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                        .clickable{ onBackClick()}
                )
            }

            if(cartItems.value.isEmpty()){
                Text(text = "Cart Is Empty", modifier = Modifier.align(Alignment.CenterHorizontally))
            } else{
                CartList(cartItems = cartItems.value, managmentCart) {
                    cartItems.value = managmentCart.getListCart()
                    calculatorCart(managmentCart, tax)
                }
                CartSummary(
                    itemTotal = managmentCart.getTotalFee(),
                    tax = tax.value,
                    delivery = 10.0
                )
            }
        }

        BottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomMenu) {
                    bottom.linkTo(parent.bottom)
                },
            onExploreClick = onExploreClick,
            onItemClick = null,
            onFavoriteClick = onFavoriteClick,
            onProfileClick = onProfileClick
        )
    }
}

fun calculatorCart(managmentCart: ManagmentCart, tax: MutableState<Double>){
    val percentTax = 0.02
    tax.value = Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
}
