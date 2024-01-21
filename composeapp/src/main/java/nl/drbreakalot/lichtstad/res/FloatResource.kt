package nl.drbreakalot.lichtstad.res

import android.util.TypedValue
import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

@Composable
@ReadOnlyComposable
fun floatResource(@DimenRes id: Int): Float {
    val context = LocalContext.current
    val typedValue = TypedValue()
    context.resources.getValue(id, typedValue, true)
    return typedValue.float
}