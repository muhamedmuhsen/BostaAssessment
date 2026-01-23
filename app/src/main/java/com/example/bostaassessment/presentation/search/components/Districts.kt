package com.example.bostaassessment.presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.bostaassessment.R
import com.example.bostaassessment.domain.model.District
import com.example.bostaassessment.presentation.utils.locale.AppLanguage

@Composable
fun DistrictsSection(
    modifier: Modifier = Modifier,
    districts: List<District>,
    currentLanguage: String
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 8.dp)
    ) {
        districts.forEach { district ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                val zoneName = if (currentLanguage == AppLanguage.ENGLISH.code) district.zoneName else district.zoneOtherName
                val districtName = if (currentLanguage == AppLanguage.ENGLISH.code) district.districtName else district.districtOtherName
                Text(
                    text = stringResource(R.string.district_format, zoneName, districtName),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = if (district.isCovered()) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.weight(1f)
                )
                IsCoveredAreaBox(isCovered = district.isCovered())
            }
        }
    }
}

@Composable
fun IsCoveredAreaBox(isCovered: Boolean) {
    if (!isCovered) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.errorContainer)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.uncovered),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.surfaceBright
            )
        }
    }
}
