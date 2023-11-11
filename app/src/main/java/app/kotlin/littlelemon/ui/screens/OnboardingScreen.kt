package app.kotlin.littlelemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
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
    contentOfButton: String,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.app_logo),
            contentDescription = "",
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                text = "Let you to know",
                style = subTitle.fontScale(),
                color = HighlightColor.platinumGray
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        val listLabel: List<String> = listOf(
            stringResource(id = R.string.label_1),
            stringResource(id = R.string.label_2),
            stringResource(id = R.string.label_3),
        )
        val listPlaceholderValue: List<String> = listOf(
            stringResource(id = R.string.placeholder_value_1),
            stringResource(id = R.string.placeholder_value_2),
            stringResource(id = R.string.placeholder_value_3),
        )
        InputForm(listLabel = listLabel, listPlaceholderValue = listPlaceholderValue)

        Spacer(modifier = Modifier.height(80.dp))

        FinishButton(
            content = contentOfButton,
            action = { navController.navigateUp() }
        )
    }
}

@Composable
private fun InputForm(
    listLabel: List<String>,
    listPlaceholderValue: List<String>
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
            listLabel = listLabel,
            listPlaceholderValue = listPlaceholderValue
        )
    }
}

@Composable
private fun InputField(
    listLabel: List<String>,
    listPlaceholderValue: List<String>
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        for (i in listLabel.indices) {
            InputSection(
                label = listLabel[i],
                placeholderValue = listPlaceholderValue[i],
                imeAction = if (i != listLabel.size - 1) {
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