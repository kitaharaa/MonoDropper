package com.kitaharaa.monodropper.ui.base

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kitaharaa.monodropper.model.card.CardType
import com.kitaharaa.monodropper.model.card.UserCard
import com.kitaharaa.monodropper.ui.theme.Income
import com.kitaharaa.monodropper.ui.theme.Outcome

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
            .background(
                when (cardInfo.cardType) {
                    CardType.Black -> Color.Black
                    else -> Color.White
                }
            )
    ) {
        Spacer(Modifier.size(40.dp))

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = cardInfo.userName,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.size(30.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 30.dp,
                    end = 15.dp
                ),
            text = cardInfo.cartNumber,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )

        Spacer(Modifier.size(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "+${cardInfo.monthReplenishment}",
                color = Income
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = "-${cardInfo.monthDebit}",
                color = Outcome
            )
        }

        Spacer(Modifier.size(40.dp))
    }
}

@Preview
@Composable
fun CardPreview() {
    CardRow(rememberScrollState(), cardList)
}