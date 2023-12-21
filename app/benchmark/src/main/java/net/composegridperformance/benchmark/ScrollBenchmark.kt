package net.composegridperformance.benchmark

import android.util.TypedValue
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.roundToInt

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ScrollBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun scrollImageList() = benchmarkRule.measureRepeated(
        packageName = "net.composegridperformance",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.WARM,
        setupBlock = {
            pressHome()
            startActivityAndWait()

            device.wait(Until.findObject(By.text("Image List (Coil)")), 10_000)
            device.findObject(By.text("Image List (Coil)")).click()

            Thread.sleep(500)
        }

    ) {
        val scrollOffset = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            SCROLL_LENGTH_DP,
            InstrumentationRegistry.getInstrumentation().context.resources.displayMetrics
        ).roundToInt()

        device.swipe(
            /* startX = */ device.displayWidth / 2,
            /* startY = */ device.displayHeight / 2 + scrollOffset / 2,
            /* endX = */ device.displayWidth / 2,
            /* endY = */ device.displayHeight / 2 - scrollOffset / 2,
            /* steps = */ SCROLL_STEPS
        )
        Thread.sleep(SCROLL_SLEEP)

        device.swipe(
            /* startX = */ device.displayWidth / 2,
            /* startY = */ device.displayHeight / 2 - scrollOffset / 2,
            /* endX = */ device.displayWidth / 2,
            /* endY = */ device.displayHeight / 2 + scrollOffset / 2,
            /* steps = */ SCROLL_STEPS
        )
        Thread.sleep(SCROLL_SLEEP)
    }
}

private const val SCROLL_SLEEP = 1000L
private const val SCROLL_STEPS = 3
private const val SCROLL_LENGTH_DP = 1000f
