package app.kotlin.littlelemon.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.kotlin.littlelemon.R
import app.kotlin.littlelemon.ui.theme.HighlightColor
import app.kotlin.littlelemon.ui.theme.fontScale
import app.kotlin.littlelemon.ui.theme.highlightText
import app.kotlin.littlelemon.ui.theme.sectionTitle
import app.kotlin.littlelemon.ui.theme.subTitle
import app.kotlin.littlelemon.ui.viewmodels.HomeScreenUiState
import app.kotlin.littlelemon.ui.viewmodels.HomeScreenViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun FoodItemScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel,
    modifier: Modifier
) {
    val uiState: State<HomeScreenUiState> = viewModel.uiState.collectAsState()
    Box(modifier = modifier) {
        Column(modifier = Modifier.align(alignment = Alignment.TopStart)) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(uiState.value.listOfFoodItem.menu[uiState.value.foodChosenIndex].image)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(id = R.drawable.image_placeholder)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = uiState.value.listOfFoodItem.menu[uiState.value.foodChosenIndex].title,
                style = sectionTitle.fontScale(),
                color = HighlightColor.charcoalGray,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = uiState.value.listOfFoodItem.menu[uiState.value.foodChosenIndex].description,
                style = highlightText.fontScale(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp
                    ),
                color = HighlightColor.charcoalGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Price: $${uiState.value.listOfFoodItem.menu[uiState.value.foodChosenIndex].price}",
                style = subTitle.fontScale(),
                color = HighlightColor.charcoalGray,
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(bottom = 40.dp, end = 40.dp)
                .align(alignment = Alignment.BottomEnd)
        ) {
            Text(
                text = "Back",
                style = highlightText.fontScale(),
                color = HighlightColor.charcoalGray
            )
        }
    }

}
