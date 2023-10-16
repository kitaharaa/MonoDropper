package com.kitaharaa.monodropper.ui.base

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kitaharaa.monodropper.model.card.CardType
import com.kitaharaa.monodropper.model.card.UserCard

val cardList = listOf(
    UserCard(
        userName = "Valik Boichuk",
        cartNumber = "87823472348**314",
        cardType = CardType.Black,
        monthReplenishment = 33_380,
        monthDebit = 5_702
    ),
    UserCard(
        userName = "Ilona Chernetska",
        cartNumber = "234572348**314",
        cardType = CardType.White,
        monthReplenishment = 334_380,
        monthDebit = 14_553
    ),
    UserCard(
        userName = "Ilona Chernetska",
        cartNumber = "234572348**314",
        cardType = CardType.White,
        monthReplenishment = 334_380,
        monthDebit = 14_553
    ),
    UserCard(
        userName = "Ilona Chernetska",
        cartNumber = "234572348**314",
        cardType = CardType.White,
        monthReplenishment = 334_380,
        monthDebit = 14_553
    ),
)

@Composable
fun BaseScreen(cards: List<UserCard>) {
    val state = rememberScrollState()

    LazyColumn {
        item {
            CardRow(state, cards)
        }
    }
}

@Composable
fun CardRow(state: ScrollState, cardList: List<UserCard>) {
    val localConfiguration = LocalConfiguration.current
    val screenWidth = localConfiguration.screenWidthDp.dp * .8f

    LazyRow(
        Modifier
            .horizontalScroll(state)
            .height(300.dp)
            .width(450.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(cardList) {
            UserCardView(it, screenWidth)
        }
    }
}

@Composable
fun UserCardView(cardInfo: UserCard, width: Dp) {
    ElevatedCard(
        modifier = Modifier
            .width(width)
            .height(250.dp)
            .background(
                when (cardInfo.cardType) {
                    CardType.Black -> Color.Black
                    else -> Color.White
                }
            )
    ) {
        Text(
            modifier = Modifier.padding(15.dp), text = cardInfo.userName
        )
    }
}

@Preview
@Composable
fun CardPreview() {
    CardRow(rememberScrollState(), cardList)
}