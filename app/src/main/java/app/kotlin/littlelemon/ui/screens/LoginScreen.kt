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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.kotlin.littlelemon.R
import app.kotlin.littlelemon.ui.theme.HighlightColor
import app.kotlin.littlelemon.ui.theme.fontScale
import app.kotlin.littlelemon.ui.theme.leadText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    //navController for navigation
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 64.dp)
                .align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //App logo in log in screen
            Image(
                painter = painterResource(id = R.drawable.app_logo_login),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(16.dp))

            var emailInput: String by remember {
                mutableStateOf(value = "")
            }
            TextField(
                value = emailInput,
                onValueChange = { emailInput = it },
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
                        text = "Email",
                        style = leadText.fontScale(),
                        color = HighlightColor.charcoalGray,
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(16.dp))

            var passwordInput: String by remember {
                mutableStateOf(value = "")
            }
            TextField(
                value = passwordInput,
                onValueChange = { passwordInput = it },
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
                        text = "Password",
                        style = leadText.fontScale(),
                        color = HighlightColor.charcoalGray
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )

            Spacer(modifier = Modifier.height(24.dp))

            FinishButton(
                content = "Log in",
                action = {navController.navigate(route = "HomeScreen")}
            )
        }
        Box(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(alignment = Alignment.BottomCenter),
            contentAlignment = Alignment.BottomCenter
        ) {
            FinishButton(
                content = "Create a new account",
                action = { navController.navigate(route = "OnboardingScreen") },
                isFilledButton = false
            )
        }
    }
}