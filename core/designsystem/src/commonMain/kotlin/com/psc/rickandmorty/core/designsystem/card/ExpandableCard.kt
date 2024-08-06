package com.psc.rickandmorty.core.designsystem.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psc.rickandmorty.core.designsystem.resources.Res
import com.psc.rickandmorty.core.designsystem.resources.ic_arrow_down
import com.psc.rickandmorty.core.designsystem.spacer.HorizontalSpacer
import com.psc.rickandmorty.core.designsystem.spacer.VerticalSpacer
import com.psc.rickandmorty.core.designsystem.theme.AppTheme
import com.psc.rickandmorty.core.designsystem.util.responsiveDp
import com.psc.rickandmorty.core.designsystem.util.responsiveSp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExpandableContentCard(
    title: String,
    expandedContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    cardColor: Color = MaterialTheme.colorScheme.surface,
    onCardColor: Color = MaterialTheme.colorScheme.onSurface,
    titleStyle: TextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
    cardStrokeWidth: Int = 0,
    cardStrokeColor: Color = Color.Unspecified
) {
    ExpandableCard(
        title = title,
        expandedContent = expandedContent,
        modifier = modifier,
        cardColor = cardColor,
        onCardColor = onCardColor,
        titleStyle = titleStyle,
        cardStrokeWidth = cardStrokeWidth,
        cardStrokeColor = cardStrokeColor
    )
}

@Composable
fun ExpandableDescriptionCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    cardColor: Color = MaterialTheme.colorScheme.surface,
    onCardColor: Color = MaterialTheme.colorScheme.onSurface,
    titleStyle: TextStyle = TextStyle(fontSize = 18.responsiveSp, fontWeight = FontWeight.Medium),
    descriptionStyle: TextStyle = TextStyle(
        fontSize = 14.responsiveSp,
        fontWeight = FontWeight.Normal
    ),
    cardStrokeWidth: Int = 0,
    cardStrokeColor: Color = Color.Unspecified
) {
    ExpandableCard(
        title = title,
        modifier = modifier,
        cardColor = cardColor,
        onCardColor = onCardColor,
        titleStyle = titleStyle,
        cardStrokeWidth = cardStrokeWidth,
        cardStrokeColor = cardStrokeColor,
        expandedContent = {
            Text(
                text = description,
                style = descriptionStyle
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExpandableCard(
    title: String,
    expandedContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    cardColor: Color = MaterialTheme.colorScheme.surface,
    onCardColor: Color = MaterialTheme.colorScheme.onSurface,
    titleStyle: TextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
    cardStrokeWidth: Int = 0,
    cardStrokeColor: Color = Color.Unspecified
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val rotationAnimation by animateFloatAsState(targetValue = if (isExpanded) 180f else 0F)

    Card(
        shape = RoundedCornerShape(8.responsiveDp),
        border = BorderStroke(width = cardStrokeWidth.dp, color = cardStrokeColor),
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
            contentColor = onCardColor
        ),
        onClick = { isExpanded = !isExpanded },
        modifier = Modifier
            .animateContentSize()
            .then(modifier)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(horizontal = 12.responsiveDp, vertical = 8.responsiveDp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = title, style = titleStyle, modifier = Modifier.weight(1F))
                HorizontalSpacer(width = 16.responsiveDp)
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_down),
                    contentDescription = null,
                    tint = onCardColor,
                    modifier = Modifier
                        .size(20.responsiveDp)
                        .rotate(rotationAnimation)
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column {
                    VerticalSpacer(height = 12.responsiveDp)
                    expandedContent()
                }
            }
        }
    }
}

@Preview
@Composable
private fun ExpandableDescriptionCardPreview() {
    AppTheme {
        ExpandableDescriptionCard(
            title = "Titulo bacana",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur auctor sed est nec cursus. Praesent elementum turpis sed turpis porttitor, vitae venenatis mauris scelerisque. Sed vestibulum purus ante, vel gravida augue ullamcorper tincidunt. Duis pellentesque volutpat porttitor. Quisque vestibulum, est sed sollicitudin bibendum, libero mauris efficitur nisi, in suscipit elit neque eget nunc. Duis non sagittis eros. Nulla a semper sapien."
        )
    }
}