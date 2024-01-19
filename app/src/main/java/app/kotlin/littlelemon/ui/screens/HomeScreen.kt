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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.kotlin.littlelemon.R
import app.kotlin.littlelemon.model.FoodItem
import app.kotlin.littlelemon.model.ListOfFoodItem
import app.kotlin.littlelemon.ui.theme.HighlightColor
import app.kotlin.littlelemon.ui.theme.PrimaryColor
import app.kotlin.littlelemon.ui.theme.SecondaryColor
import app.kotlin.littlelemon.ui.theme.cardTitle
import app.kotlin.littlelemon.ui.theme.displayText
import app.kotlin.littlelemon.ui.theme.fontScale
import app.kotlin.littlelemon.ui.theme.highlightText
import app.kotlin.littlelemon.ui.theme.paragraphText
import app.kotlin.littlelemon.ui.theme.sectionCategory
import app.kotlin.littlelemon.ui.theme.sectionTitle
import app.kotlin.littlelemon.ui.theme.subTitle
import app.kotlin.littlelemon.ui.viewmodels.ConnectionState
import app.kotlin.littlelemon.ui.viewmodels.HomeScreenUiState
import app.kotlin.littlelemon.ui.viewmodels.HomeScreenViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.runBlocking

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel,
    modifier: Modifier
) {
    val homeScreenUiState: State<HomeScreenUiState> = viewModel.uiState.collectAsState()
    LazyColumn(modifier = modifier) {
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { TopBar(navController = navController) }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        item { Introduction(action = viewModel.searchAction) }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item { OrderForDelivery() }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            SectionCategory(
                listOfAction = viewModel.listOfFilterAction,
                listOfCategory = viewModel.listOfCategory,
                listOfStatus = homeScreenUiState.value.listOfFilterStatus
            )
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        item {
            Canvas(modifier = Modifier.fillMaxWidth()) {
                val canvasWidth: Float = size.width
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
            ListOfFoodItemColumn(
                connectionState = viewModel.connectionState,
                listOfFoodItem = homeScreenUiState.value.listOfFoodItem,
                retryAction = { viewModel.getListOfFoodItem() },
                navController = navController,
                viewModel = viewModel
            )
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }
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
            onClick = { navController.navigate(route = "ProfileScreen") },
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
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
fun Introduction(action: (String) -> Unit) {
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
                        painter = painterResource(id = R.drawable.hero_image),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = ""
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        //Search bar
        var searchPhrase: String by remember { mutableStateOf(value = "") }
        TextField(
            value = searchPhrase,
            onValueChange = {
                searchPhrase = it
                action(it)
            },
            modifier = Modifier.fillMaxWidth(),
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
            textStyle = highlightText.fontScale(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.None
            ),
            singleLine = true
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
    listOfCategory: List<String>,
    listOfStatus: List<Boolean>
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        item {
            Spacer(modifier = Modifier.width(0.dp))
        }

        items(listOfAction.size) { index ->
            Row {
                SectionCategoryTag(
                    action = listOfAction[index],
                    category = listOfCategory[index],
                    isPressed = listOfStatus[index]
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
    category: String,
    isPressed: Boolean
) {
    Button(
        onClick = action,
        modifier = Modifier
            .width(88.dp)
            .height(30.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isPressed) {
                PrimaryColor.sunflowerYellow
            } else {
                HighlightColor.platinumGray
            }
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
fun ListOfFoodItemColumn(
    connectionState: ConnectionState,
    listOfFoodItem: ListOfFoodItem,
    retryAction: () -> Unit,
    viewModel: HomeScreenViewModel,
    navController: NavController
) {
    when (connectionState) {
        ConnectionState.Loading -> LoadingScreen()
        ConnectionState.Error -> ErrorScreen(retryAction = retryAction)
        ConnectionState.Successful -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 24.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                for (i: Int in listOfFoodItem.menu.indices) {
                    FoodItemTag(
                        listOfFoodItem.menu[i],
                        onClickAction = {
                            runBlocking {
                                viewModel.chooseFoodItemAction(i)
                            }
                            navController.navigate(route = "FoodItemScreen")
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItemTag(
    foodItem: FoodItem,
    onClickAction: () -> Unit
) {
    Card(
        onClick = onClickAction,
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = HighlightColor.platinumGray)
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
        ) {
            Card(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .align(alignment = Alignment.CenterEnd),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(foodItem.image)
                        .crossfade(enable = true)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.image_placeholder)
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp)
                    .align(Alignment.CenterStart)
                    .height(80.dp),
            ) {
                Column {
                    Text(
                        text = foodItem.title,
                        style = cardTitle.fontScale(),
                        color = HighlightColor.charcoalGray
                    )
                    Text(
                        text = foodItem.description,
                        style = paragraphText.fontScale(),
                        color = HighlightColor.charcoalGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 80.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
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
                        text = foodItem.price,
                        style = highlightText.fontScale(),
                        color = HighlightColor.charcoalGray
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Loading...",
            style = paragraphText.fontScale(),
            color = HighlightColor.charcoalGray
        )
    }
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = retryAction,
            border = BorderStroke(
                width = 2.dp,
                color = SecondaryColor.coralPink
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor.sunflowerYellow
            )
        ) {
            Text(
                text = "Reload menu",
                style = sectionCategory.fontScale(),
                color = PrimaryColor.plateGreen
            )
        }
    }
}



