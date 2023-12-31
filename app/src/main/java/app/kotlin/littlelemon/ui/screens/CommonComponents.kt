package app.kotlin.littlelemon.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.kotlin.littlelemon.ui.theme.HighlightColor
import app.kotlin.littlelemon.ui.theme.PrimaryColor
import app.kotlin.littlelemon.ui.theme.SecondaryColor
import app.kotlin.littlelemon.ui.theme.fontScale
import app.kotlin.littlelemon.ui.theme.leadText

@Composable
fun FinishButton(
    isFilledButton: Boolean = true,
    strSrc: Int,
    action: () -> Unit,
    canPress: Boolean = true
) {
    Button(
        onClick = action,
        modifier = Modifier
            .height(48.dp)
            .padding(
                start = 16.dp,
                end = 16.dp,
            )
            .fillMaxWidth(),
        enabled = canPress,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFilledButton) {
                if (canPress) {
                    PrimaryColor.sunflowerYellow
                } else {
                    HighlightColor.platinumGray
                }
            } else {
                Color.Transparent
            },
        ),
        contentPadding = PaddingValues(all = 0.dp),
        border = BorderStroke(
            width = 2.dp,
            color = if (canPress) {
                SecondaryColor.coralPink
            } else {
                SecondaryColor.palePink
            }
        )
    ) {
        Text(
            text = stringResource(id = strSrc),
            style = leadText.fontScale(),
            color = HighlightColor.charcoalGray,
        )
    }
}

