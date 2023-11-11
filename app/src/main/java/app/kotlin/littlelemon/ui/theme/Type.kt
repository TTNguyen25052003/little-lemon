package app.kotlin.littlelemon.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.kotlin.littlelemon.R

private val markaziTextFamily = FontFamily(
    Font(R.font.markazitext_regular, FontWeight.Normal),
    Font(R.font.markazitext_medium, FontWeight.Medium),
    Font(R.font.markazitext_semibold, FontWeight.SemiBold),
    Font(R.font.markazitext_bold, FontWeight.Bold)
)

private val karlaFamily = FontFamily(
    Font(R.font.karla_extralight, FontWeight.ExtraLight),
    Font(R.font.karla_light, FontWeight.Light),
    Font(R.font.karla_regular, FontWeight.Normal),
    Font(R.font.karla_medium, FontWeight.Medium),
    Font(R.font.karla_bold, FontWeight.Bold),
    Font(R.font.karla_extrabold, FontWeight.ExtraBold)
)

@Composable
fun TextStyle.fontScale(): TextStyle {
    val fontScale = LocalConfiguration.current.fontScale
    return this.copy(fontSize = this.fontSize / fontScale)
}



val displayText = TextStyle(
    fontFamily = markaziTextFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 64.sp,
)

val subTitle = TextStyle(
    fontFamily = markaziTextFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 40.sp,
)

val leadText = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
)

val sectionTitle = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 20.sp,
)

val sectionCategory = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 16.sp,
)

val cardTitle = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
)

val paragraphText = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 28.sp,
)

val highlightText = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
)