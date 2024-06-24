package tech.dagimtesfaye.cbe_balance_tracker.view.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import tech.dagimtesfaye.cbe_balance_tracker.data.model.SmsData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun LineGraph(smsDataList: List<SmsData>) {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -7)
    val sevenDaysAgo = calendar.timeInMillis

    val recentSmsData = smsDataList.filter {
        it.date >= sevenDaysAgo
    }

    val dailyBalances = recentSmsData.groupBy {
        // Truncate the time part to group by date only
        val calendar2 = Calendar.getInstance()
        calendar2.timeInMillis = it.date
        calendar2.set(Calendar.HOUR_OF_DAY, 0)
        calendar2.set(Calendar.MINUTE, 0)
        calendar2.set(Calendar.SECOND, 0)
        calendar2.set(Calendar.MILLISECOND, 0)
        calendar2.timeInMillis
    }.mapValues { entry ->
        entry.value.first().remainingBalance.toFloat()
    }

    val pointsData: List<Point> =
        dailyBalances.entries.sortedBy { it.key }.mapIndexed { index, entry ->
            Log.d("Line Graph: ", "Point(x: ${entry.key}, y: ${entry.value})")
            Point(index.toFloat(), entry.value.toInt().toFloat())
        }
    val dateKeys: List<Long> = dailyBalances.keys.toList().reversed()

    val xAxisData = AxisData.Builder().axisStepSize(63.dp)
        .backgroundColor(Color.Transparent).steps(pointsData.lastIndex)
        .labelAndAxisLinePadding(15.dp)
        .labelData { i ->
            val date = Date(dateKeys[i])
            val dateString = SimpleDateFormat("dd", Locale.getDefault()).format(date)
            dateString
        }.labelAndAxisLinePadding(15.dp)
        .startPadding(20.dp)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(6)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / 6
            ((i * yScale) + yMin).toInt().toString()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.primary),
                    SelectionHighlightPoint(
                        color = MaterialTheme.colorScheme.primary),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = MaterialTheme.colorScheme.background
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colorScheme.surface),
        lineChartData = lineChartData
    )
}
