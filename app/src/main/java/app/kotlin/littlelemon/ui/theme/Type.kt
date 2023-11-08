package app.kotlin.littlelemon.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.kotlin.littlelemon.R

private val markaziTextFamily = FontFamily(
    Font(R.font.markazitext_regular, FontWeight.Normal),
    Font(R.font.markazitext_regular,FontWeight.Medium),
    Font(R.font.markazitext_regular, FontWeight.SemiBold),
    Font(R.font.markazitext_regular,FontWeight.Bold)
)

private val karlaFamily = FontFamily(
    Font(R.font.karla_regular, FontWeight.ExtraLight),
    Font(R.font.karla_regular, FontWeight.Light),
    Font(R.font.karla_regular, FontWeight.Normal),
    Font(R.font.karla_regular, FontWeight.Medium),
    Font(R.font.karla_regular, FontWeight.Bold),
    Font(R.font.karla_regular, FontWeight.ExtraBold)
)

val displayText = TextStyle(
    fontFamily = markaziTextFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 64.sp
)

val subTitle = TextStyle(
    fontFamily = markaziTextFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 40.sp
)

val leadText = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp
)

val sectionTitle = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 20.sp
)

val sectionCategory = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 16.sp
)

val cardTitle = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp
)

val paragraphText = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 28.sp
)

val highlightText = TextStyle(
    fontFamily = karlaFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp
)