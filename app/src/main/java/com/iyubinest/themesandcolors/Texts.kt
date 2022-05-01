package com.iyubinest.themesandcolors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

private const val debug = false

enum class TextVariant {
  TITLE,
  BODY,
  CAPTION,
}

@Composable
fun Title(
  text: String,
  modifier: Modifier = Modifier,
  color: Color = MaterialTheme.colors.onBackground,
  textAlign: TextAlign? = TextAlign.Start,
  underline: Boolean = false,
  leadingIcon: Int? = null,
  trailingIcon: Int? = null,
  iconSize: Dp? = null,
  overflow: TextOverflow? = null,
) {
  BaseText(
    text = text,
    modifier = modifier,
    variant = TextVariant.TITLE,
    color = color,
    textAlign = textAlign,
    underline = underline,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    iconSize = iconSize,
    overflow = overflow,
  )
}

@Composable
fun Body(
  text: String,
  modifier: Modifier = Modifier,
  color: Color = MaterialTheme.colors.onBackground,
  textAlign: TextAlign? = TextAlign.Start,
  underline: Boolean = false,
  leadingIcon: Int? = null,
  trailingIcon: Int? = null,
  iconSize: Dp? = null,
  overflow: TextOverflow? = null,
) {
  BaseText(
    text = text,
    modifier = modifier,
    variant = TextVariant.BODY,
    color = color,
    textAlign = textAlign,
    underline = underline,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    iconSize = iconSize,
    overflow = overflow,
  )
}

@Composable
fun Caption(
  text: String,
  modifier: Modifier = Modifier,
  color: Color = MaterialTheme.colors.onBackground,
  textAlign: TextAlign? = TextAlign.Start,
  underline: Boolean = false,
  leadingIcon: Int? = null,
  trailingIcon: Int? = null,
  iconSize: Dp? = null,
  overflow: TextOverflow? = null,
) {
  BaseText(
    text = text,
    modifier = modifier,
    variant = TextVariant.CAPTION,
    color = color,
    textAlign = textAlign,
    underline = underline,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    iconSize = iconSize,
    overflow = overflow,
  )
}

@Composable
fun BaseText(
  text: String,
  modifier: Modifier = Modifier,
  variant: TextVariant = TextVariant.BODY,
  color: Color = MaterialTheme.colors.onBackground,
  textAlign: TextAlign? = TextAlign.Start,
  underline: Boolean = false,
  leadingIcon: Int? = null,
  trailingIcon: Int? = null,
  iconSize: Dp? = null,
  overflow: TextOverflow? = null,
) {
  val style = styleFrom(variant).copy(
    color = color,
    textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
  )
  val defaultSize = style.fontSize.toDp()
  val iconHeight = iconSize ?: defaultSize - 3.dp
  Row(
    modifier = modifier.background(if (debug) Color.Red else Color.Transparent),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = basedOn(textAlign)
  ) {
    if (leadingIcon != null) {
      Row(
        modifier = Modifier.padding(end = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Icon(
          modifier = Modifier.height(iconHeight),
          painter = painterResource(leadingIcon),
          tint = color,
          contentDescription = "",
        )
      }
    }
    Text(
      text = text,
      style = style,
      textAlign = textAlign,
      overflow = overflow ?: TextOverflow.Clip,
      maxLines = if (overflow == TextOverflow.Ellipsis) 1 else Int.MAX_VALUE,
    )
    if (trailingIcon != null) {
      Row(
        modifier = Modifier.padding(start = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Icon(
          modifier = Modifier.height(iconHeight),
          painter = painterResource(trailingIcon),
          contentDescription = "",
        )
      }
    }
  }
}

fun basedOn(textAlign: TextAlign?): Arrangement.Horizontal {
  return when (textAlign) {
    TextAlign.Left -> Arrangement.Start
    TextAlign.Right -> Arrangement.End
    TextAlign.Center -> Arrangement.Center
    TextAlign.Start -> Arrangement.Start
    TextAlign.End -> Arrangement.End
    TextAlign.Justify -> Arrangement.SpaceEvenly
    else -> Arrangement.Center
  }
}

@Composable
fun styleFrom(variant: TextVariant): TextStyle {
  return when (variant) {
    TextVariant.TITLE -> Fonts.Title
    TextVariant.BODY -> Fonts.Body
    TextVariant.CAPTION -> Fonts.Caption
  }
}

@Composable
fun TextUnit.toDp(): Dp {
  val lineHeightSp = this
  return with(LocalDensity.current) {
    lineHeightSp.toDp()
  }
}
