package app.kotlin.littlelemon.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.kotlin.littlelemon.R
import app.kotlin.littlelemon.ui.theme.HighlightColor
import app.kotlin.littlelemon.ui.theme.fontScale
import app.kotlin.littlelemon.ui.theme.paragraphText
import app.kotlin.littlelemon.ui.theme.sectionCategory
import app.kotlin.littlelemon.ui.theme.subTitle

@Composable
fun ProfileScreen(
    //Vars are filled in InfoField
    listOfLabel: List<String>,
    listOfContent: List<String>,

    //NavController for navigation
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //App logo and back button
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = HighlightColor.charcoalGray
                    ),
                    modifier = Modifier.align(alignment = Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = ""
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "",
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }


        //User avatar
        item {
            Card(
                modifier = Modifier
                    .width(148.dp)
                    .height(148.dp),
                shape = CircleShape,
                border = BorderStroke(
                    width = (0.2).dp,
                    color = HighlightColor.charcoalGray.copy(alpha = 0.2f)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }



        item {

            InfoField(
                listOfLabel = listOfLabel,
                listOfContent = listOfContent
            )
        }

        item {
            Spacer(modifier = Modifier.height(48.dp))
        }

        item {
            FinishButton(
                strSrc = R.string.login_button,
                action = {
                    navController.navigate(route = "LoginScreen") {
                        popUpTo(id = 0)
                    }
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun InfoField(
    listOfLabel: List<String>,
    listOfContent: List<String>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        //Label
        Text(
            text = stringResource(id = R.string.personal_info),
            style = subTitle.fontScale(),
            color = HighlightColor.charcoalGray
        )

        //Info shown
        InfoContent(
            listOfLabel = listOfLabel,
            listOfContent = listOfContent
        )
    }
}

@Composable
private fun InfoContent(
    listOfLabel: List<String>,
    listOfContent: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        for (i: Int in listOfLabel.indices) {
            InfoSection(
                label = listOfLabel[i],
                content = listOfContent[i]
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoSection(
    label: String,
    content: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        //Label
        Text(
            text = label,
            style = sectionCategory.fontScale(),
            color = HighlightColor.charcoalGray
        )

        //Info TextView
        TextField(
            value = content,
            onValueChange = {},
            readOnly = true,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = HighlightColor.platinumGray,
                    shape = RoundedCornerShape(10.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = HighlightColor.charcoalGray
            ),
            textStyle = paragraphText.fontScale()
        )
    }
}
