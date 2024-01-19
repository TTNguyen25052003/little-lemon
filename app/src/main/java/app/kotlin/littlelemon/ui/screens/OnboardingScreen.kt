package app.kotlin.littlelemon.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.kotlin.littlelemon.R
import app.kotlin.littlelemon.data.LocalUsersRepository
import app.kotlin.littlelemon.ui.theme.HighlightColor
import app.kotlin.littlelemon.ui.theme.PrimaryColor
import app.kotlin.littlelemon.ui.theme.fontScale
import app.kotlin.littlelemon.ui.theme.paragraphText
import app.kotlin.littlelemon.ui.theme.sectionCategory
import app.kotlin.littlelemon.ui.theme.subTitle
import app.kotlin.littlelemon.ui.viewmodels.OnboardingScreenViewModel
import app.kotlin.littlelemon.ui.viewmodels.OnboardingScreenViewModelFactory
import app.kotlin.littlelemon.ui.viewmodels.UiState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking

@Composable
fun OnboardingScreen(
    context: Context,
    listOfLabel: List<String>,
    listOfPlaceholder: List<String>,
    navController: NavController,
    viewModel: OnboardingScreenViewModel = viewModel(
        factory = OnboardingScreenViewModelFactory(
            usersRepository = LocalUsersRepository(context = context)
        )
    ),
    modifier: Modifier,
) {
    val uiState: State<UiState> = viewModel.uiState.collectAsState()
    LazyColumn(modifier = modifier) {
        //Top app bar
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
        //

        item { Spacer(modifier = Modifier.height(16.dp)) }

        //LET GET YOU TO KNOW
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
        //

        item { Spacer(modifier = Modifier.height(40.dp)) }

        //Input form
        item {
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
                Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    for (i: Int in listOfLabel.indices) {
                        InputSection(
                            label = listOfLabel[i],
                            placeholderValue = listOfPlaceholder[i],
                            imeAction = if (i != listOfLabel.size - 1) {
                                ImeAction.Next
                            } else {
                                ImeAction.Done
                            },
                            stateUpdateAction = viewModel.listOfUpdateAction[i],
                            viewModel = viewModel,
                            showWarningValue = !uiState.value.listOfValidation[i]
                        )
                    }
                }
            }
        }
        //

        item { Spacer(modifier = Modifier.height(48.dp)) }

        //Finish button
        item {
            Column {
                val isCompletedInputField: Boolean = !uiState
                    .value
                    .listOfValidation
                    .contains(element = false)
                FinishButton(
                    strSrc = R.string.register_button,
                    action = {
                        runBlocking { viewModel.createAccount() }
                        navController.popBackStack()
                    },
                    canPress = isCompletedInputField
                )
            }
        }
        //

        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSection(
    label: String,
    placeholderValue: String,
    imeAction: ImeAction,
    stateUpdateAction: (String) -> Unit,
    viewModel: OnboardingScreenViewModel,
    showWarningValue: Boolean
) {
    var currentInput: String by remember { mutableStateOf(value = "") }

    Column(modifier = Modifier.fillMaxWidth()) {
        var showWarning: Boolean by remember { mutableStateOf(value = false) }
        //label
        showWarning = showWarningValue
        Row {
            Text(
                text = label,
                style = sectionCategory.fontScale(),
                color = HighlightColor.charcoalGray
            )

            //if the current input value is invalid show the waring
            Spacer(modifier = Modifier.width(16.dp))
            if (showWarning) {
                Text(
                    text = if (label == stringResource(id = R.string.label_email)) {
                        "Email is invalid or existed"
                    } else {
                        stringResource(id = R.string.warning_message_1)
                    },
                    style = paragraphText.fontScale(),
                    color = HighlightColor.charcoalGray,
                    modifier = Modifier.align(alignment = Alignment.Bottom)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = currentInput,
            onValueChange = {
                currentInput = it
                if (label == "Email") {
                    // This code is make sure that userGot updated before checking
                    runBlocking(IO) { viewModel.getUser(emailInput = currentInput) }
                }
                stateUpdateAction(it)
            },
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
                    color = if (showWarning) {
                        Color.Red
                    } else {
                        HighlightColor.platinumGray
                    },
                    shape = RoundedCornerShape(10.dp)
                ),
            textStyle = paragraphText.fontScale(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction,
                capitalization = KeyboardCapitalization.None
            ),
        )
    }
}