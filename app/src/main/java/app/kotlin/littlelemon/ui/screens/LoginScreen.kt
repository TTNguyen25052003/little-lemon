package app.kotlin.littlelemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.kotlin.littlelemon.R
import app.kotlin.littlelemon.ui.theme.HighlightColor
import app.kotlin.littlelemon.ui.theme.fontScale
import app.kotlin.littlelemon.ui.theme.leadText
import app.kotlin.littlelemon.ui.theme.paragraphText
import app.kotlin.littlelemon.ui.viewmodels.LoginProfileScreenViewModel
import kotlinx.coroutines.runBlocking
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginProfileScreenViewModel,
    modifier: Modifier
) {
    val screenHeight: Int = LocalConfiguration.current.screenHeightDp
    LazyColumn(modifier = modifier) {
        var showWarning: Boolean by mutableStateOf(value = false)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = (abs(n = screenHeight - 464) / 2).dp),
            ) {
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //App logo in log in screen
                    Card(
                        modifier = Modifier
                            .height(248.dp)
                            .width(122.dp),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.app_logo_login),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email login input field

                    var emailInput: String by remember { mutableStateOf(value = "") }
                    TextField(
                        value = emailInput,
                        onValueChange = {
                            emailInput = it
                            viewModel.updateEmail(emailInput = emailInput)
                            runBlocking { viewModel.getUser(emailInput = emailInput) }
                        },
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                end = 16.dp
                            )
                            .fillMaxWidth()
                            .height(56.dp)
                            .border(
                                width = 2.dp,
                                color = HighlightColor.platinumGray,
                                shape = RoundedCornerShape(28.dp)
                            ),
                        shape = RoundedCornerShape(28.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = HighlightColor.charcoalGray,
                            textColor = HighlightColor.charcoalGray,
                        ),
                        textStyle = leadText.fontScale(),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.label_email),
                                style = leadText.fontScale(),
                                color = HighlightColor.charcoalGray,
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                            capitalization = KeyboardCapitalization.None
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    //Password login input field
                    var passwordInput: String by remember { mutableStateOf(value = "") }
                    TextField(
                        value = passwordInput,
                        onValueChange = {
                            passwordInput = it
                            viewModel.updatePassword(passwordInput = passwordInput)
                        },
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                end = 16.dp
                            )
                            .fillMaxWidth()
                            .height(56.dp)
                            .border(
                                width = 2.dp,
                                color = HighlightColor.platinumGray,
                                shape = RoundedCornerShape(28.dp)
                            ),
                        shape = RoundedCornerShape(28.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = HighlightColor.charcoalGray,
                            textColor = HighlightColor.charcoalGray,
                        ),
                        textStyle = leadText.fontScale(),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.label_password),
                                style = leadText.fontScale(),
                                color = HighlightColor.charcoalGray
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            capitalization = KeyboardCapitalization.None
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    //Log in button
                    FinishButton(
                        strSrc = R.string.login_button,
                        action = {
                            if (viewModel.isLoginSuccessfully()) {
                                navController.navigate(route = "HomeScreen") { popUpTo(id = 0) }
                            } else {
                                showWarning = true
                            }
                        }
                    )

                    //Warning
                    if (showWarning) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Email is or password was wrong",
                            style = paragraphText.fontScale(),
                            color = HighlightColor.charcoalGray
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(((screenHeight - 464) / 2 - 48 - 20).dp)) }

        //Create a new account button
        item {
            FinishButton(
                strSrc = R.string.create_new_account_button,
                action = { navController.navigate(route = "OnboardingScreen") },
                isFilledButton = false
            )
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}