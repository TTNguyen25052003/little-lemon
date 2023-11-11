package app.kotlin.littlelemon.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.kotlin.littlelemon.R
import app.kotlin.littlelemon.ui.theme.HighlightColor
import app.kotlin.littlelemon.ui.theme.PrimaryColor
import app.kotlin.littlelemon.ui.theme.cardTitle
import app.kotlin.littlelemon.ui.theme.displayText
import app.kotlin.littlelemon.ui.theme.fontScale
import app.kotlin.littlelemon.ui.theme.highlightText
import app.kotlin.littlelemon.ui.theme.paragraphText
import app.kotlin.littlelemon.ui.theme.sectionCategory
import app.kotlin.littlelemon.ui.theme.sectionTitle
import app.kotlin.littlelemon.ui.theme.subTitle

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            TopBar(navController = navController)
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Introduction()
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            OrderForDelivery()
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            val listOfCategory: List<String> = listOf(
                "Starter",
                "Main",
                "Dessert",
                "Sides"
            )
            val listOfAction: List<() -> Unit> = listOf({}, {}, {}, {})
            SectionCategory(
                listOfAction = listOfAction,
                listOfCategory = listOfCategory
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Canvas(
                modifier = Modifier.fillMaxWidth()
            ) {
                val canvasWidth = size.width
                drawLine(
                    color = Color.Black,
                    start = Offset(x = canvasWidth, y = 0f),
                    end = Offset(x = 0f, y = 0f),
                    strokeWidth = 1f,
                    alpha = 0.5f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = FloatArray(2)
                    )
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            ListOfFoodItem(sizeOfFoodItem = 7)
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //Temp object used to align the images
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp),
            content = {}
        )
        //App logo
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "",
        )

        //User avatar
        Card(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp),
            shape = CircleShape,
            border = BorderStroke(
                width = (0.2).dp,
                color = HighlightColor.charcoalGray
            ),
            onClick = {
                navController.navigate(route = "ProfileScreen")
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Introduction() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = PrimaryColor.plateGreen
            )
            .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 20.dp
            )
    ) {
        Box(
            modifier = Modifier
                .height(224.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(116.dp)
                    .align(alignment = Alignment.TopStart)
            ) {
                Text(
                    text = stringResource(id = R.string.display_text),
                    style = displayText.fontScale(),
                    color = PrimaryColor.sunflowerYellow,
                    modifier = Modifier.align(alignment = Alignment.TopStart)
                )
                Text(
                    text = stringResource(id = R.string.sub_title),
                    style = subTitle.fontScale(),
                    color = HighlightColor.platinumGray,
                    modifier = Modifier.align(alignment = Alignment.BottomStart)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomStart),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.about),
                    style = highlightText.fontScale(),
                    color = HighlightColor.platinumGray,
                    modifier = Modifier
                        .width(192.dp)
                        .align(alignment = Alignment.Bottom),
                    softWrap = true
                )

                Card(
                    modifier = Modifier
                        .width(128.dp)
                        .height(128.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = ""
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        //SearchField
        var searchPhrase by remember {
            mutableStateOf("")
        }
        TextField(
            value = searchPhrase,
            onValueChange = { searchPhrase = it },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_phrase_placeholder),
                    style = highlightText.fontScale(),
                    color = HighlightColor.charcoalGray
                )
            },
            textStyle = highlightText.fontScale()
        )
    }
}


@Composable
fun OrderForDelivery() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "ORDER FOR DELIVERY",
            style = sectionTitle.fontScale(),
            color = HighlightColor.charcoalGray
        )

        Spacer(modifier = Modifier.width(12.dp))

        Card(
            modifier = Modifier
                .width(56.dp)
                .height(56.dp),
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = HighlightColor.charcoalGray
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.delivery_van),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}


@Composable
fun SectionCategory(
    listOfAction: List<() -> Unit>,
    listOfCategory: List<String>
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        item {
            Spacer(modifier = Modifier.width(0.dp))
        }

        items(listOfAction.size) { index ->
            Row {
                SectionCategoryTag(
                    action = listOfAction[index],
                    category = listOfCategory[index]
                )
            }
        }

        item {
            Spacer(modifier = Modifier.width(0.dp))
        }
    }
}

@Composable
fun SectionCategoryTag(
    action: () -> Unit,
    category: String
) {
    Button(
        onClick = action,
        modifier = Modifier
            .width(88.dp)
            .height(30.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = HighlightColor.platinumGray
        ),
        contentPadding = PaddingValues(all = 0.dp)
    ) {
        Text(
            text = category,
            style = sectionCategory.fontScale(),
            color = PrimaryColor.plateGreen
        )
    }
}


@Composable
fun ListOfFoodItem(
    sizeOfFoodItem: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 24.dp,
                end = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (i: Int in 0 until sizeOfFoodItem) {
            FoodItem(
                itemName = "Item name",
                itemDescription = "Item description",
                priceValue = "$10.00",
                imgSrc = R.drawable.ic_launcher_background
            )
        }
    }
}

@Composable
fun FoodItem(
    itemName: String,
    itemDescription: String,
    priceValue: String,
    imgSrc: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = HighlightColor.platinumGray)
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 16.dp,
                bottom = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.height(80.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = itemName,
                    style = cardTitle.fontScale(),
                    color = HighlightColor.charcoalGray
                )
                Text(
                    text = itemDescription,
                    style = paragraphText.fontScale(),
                    color = HighlightColor.charcoalGray
                )
            }

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = stringResource(id = R.string.price_title),
                    style = cardTitle.fontScale(),
                    color = HighlightColor.charcoalGray
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = priceValue,
                    style = highlightText.fontScale(),
                    color = HighlightColor.charcoalGray
                )
            }
        }
        Card(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp),
            shape = RectangleShape
        ) {
            Image(
                painter = painterResource(id = imgSrc),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

