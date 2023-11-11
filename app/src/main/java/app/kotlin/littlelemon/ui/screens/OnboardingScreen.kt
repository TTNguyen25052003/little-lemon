package app.kotlin.littlelemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.kotlin.littlelemon.R
import app.kotlin.littlelemon.ui.theme.HighlightColor
import app.kotlin.littlelemon.ui.theme.PrimaryColor
import app.kotlin.littlelemon.ui.theme.fontScale
import app.kotlin.littlelemon.ui.theme.paragraphText
import app.kotlin.littlelemon.ui.theme.sectionCategory
import app.kotlin.littlelemon.ui.theme.subTitle

@Composable
fun OnboardingScreen(
    listOfLabel: List<String>,
    listOfPlaceholder: List<String>,
    navController: NavController
) {
    LazyColumn {
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
                    painterResource(id = R.drawable.app_logo),
                    contentDescription = "",
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(
                        color = PrimaryColor.plateGreen
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.let_get_you_to_know),
                    style = subTitle.fontScale(),
                    color = HighlightColor.platinumGray
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }


        item {
            InputForm(
                listOfLabel = listOfLabel,
                listOfPlaceholder = listOfPlaceholder
            )
        }

        item {
            Spacer(modifier = Modifier.height(48.dp))
        }

        item {
            FinishButton(
                strSrc = R.string.register_button,
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
private fun InputForm(
    listOfLabel: List<String>,
    listOfPlaceholder: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Text(
            text = stringResource(id = R.string.personal_info),
            style = subTitle.fontScale(),
            color = HighlightColor.charcoalGray
        )
        InputField(
            listOfLabel = listOfLabel,
            listOfPlaceholder = listOfPlaceholder
        )
    }
}

@Composable
private fun InputField(
    listOfLabel: List<String>,
    listOfPlaceholder: List<String>
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        for (i in listOfLabel.indices) {
            InputSection(
                label = listOfLabel[i],
                placeholderValue = listOfPlaceholder[i],
                imeAction = if (i != listOfLabel.size - 1) {
                    ImeAction.Next
                } else {
                    ImeAction.Done
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSection(
    label: String,
    placeholderValue: String,
    imeAction: ImeAction
) {
    var currentInput by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        //label
        Text(
            text = label,
            style = sectionCategory.fontScale(),
            color = HighlightColor.charcoalGray
        )

        Spacer(modifier = Modifier.height(8.dp))

        //input field
        TextField(
            value = currentInput,
            onValueChange = { currentInput = it },
            placeholder = {
                Text(
                    text = placeholderValue,
                    style = paragraphText.fontScale(),
                    color = HighlightColor.charcoalGray
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                textColor = HighlightColor.charcoalGray,
                cursorColor = HighlightColor.charcoalGray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = HighlightColor.platinumGray,
                    shape = RoundedCornerShape(10.dp)
                ),
            textStyle = paragraphText.fontScale(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction)
        )
    }
}