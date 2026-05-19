package com.elango.run.presentation.active_run.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elango.core.presentation.designsystem.RunTheme
import com.elango.core.presentation.ui.R
import com.elango.core.presentation.ui.formatted
import com.elango.core.presentation.ui.toFormattedKM
import com.elango.core.presentation.ui.toFormattedPace
import com.elango.run.domain.RunData
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes


@Composable
fun RunDataCard(
    modifier: Modifier = Modifier,
    ellipseTime: Duration,
    runData: RunData
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RunDataCardItem(
            title = stringResource(R.string.duration),
            value = runData.pace.formatted(),
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            RunDataCardItem(
                title = stringResource(R.string.distance),
                value = (runData.distanceMeters / 1000.0).toFormattedKM()
            )

            RunDataCardItem(
                title = stringResource(R.string.pace),
                value = ellipseTime.toFormattedPace(
                    distance = runData.distanceMeters / 1000.0
                )
            )
        }
    }

}

@Composable
fun RunDataCardItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    fontSize: TextUnit = 15.sp
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = fontSize,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

}


@Preview
@Composable
private fun RunDataCardPreview() {
    RunTheme {
        RunDataCard(
            ellipseTime = 10.minutes,
            runData = RunData(
                distanceMeters = 3000,
                pace = 8.minutes
            )
        )
    }
}