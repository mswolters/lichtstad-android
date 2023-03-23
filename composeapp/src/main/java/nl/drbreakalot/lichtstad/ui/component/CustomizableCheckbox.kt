package nl.drbreakalot.lichtstad.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@Composable
fun CustomizableCheckbox(
    checked: Boolean,
    modifier: Modifier = Modifier,
    checkedImage: Painter = rememberVectorPainter(Icons.Outlined.CheckBox),
    uncheckedImage: Painter = rememberVectorPainter(Icons.Outlined.CheckBoxOutlineBlank),
    colors: CustomizableCheckboxColors = CustomizableCheckboxColors.defaults(),
    onCheckedChanged: ((Boolean) -> Unit)? = null,
) {
    val clickModifier: Modifier = if (onCheckedChanged == null) Modifier else {
        Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = false)
        ) { onCheckedChanged(!checked) }
    }
    Box(
        modifier = clickModifier.then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = if (checked) checkedImage else uncheckedImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            tint = colors.iconTint(enabled = onCheckedChanged != null, checked = checked)
        )
    }
}

@Immutable
data class CustomizableCheckboxColors(
    val checkedColor: Color,
    val uncheckedColor: Color,
    val disabledCheckedColor: Color,
    val disabledUncheckedColor: Color,
) {

    fun iconTint(enabled: Boolean, checked: Boolean): Color {
        return when (enabled to checked) {
            true to true -> checkedColor
            true to false -> uncheckedColor
            false to true -> disabledCheckedColor
            false to false -> disabledUncheckedColor
            else -> Color.Unspecified
        }
    }

    companion object {
        @Composable
        fun defaults(
            checkedColor: Color = MaterialTheme.colorScheme.primary,
            uncheckedColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledCheckedColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            disabledUncheckedColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        ): CustomizableCheckboxColors {
            return CustomizableCheckboxColors(
                checkedColor,
                uncheckedColor,
                disabledCheckedColor,
                disabledUncheckedColor
            )
        }
    }

}
