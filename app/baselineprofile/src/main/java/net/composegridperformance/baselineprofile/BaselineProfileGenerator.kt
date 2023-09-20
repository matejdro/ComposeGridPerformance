package net.composegridperformance.baselineprofile

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This test class generates a basic startup baseline profile for the target package.
 *
 * We recommend you start with this but add important user flows to the profile to improve their performance.
 * Refer to the [baseline profile documentation](https://d.android.com/topic/performance/baselineprofiles)
 * for more information.
 *
 * You can run the generator with the Generate Baseline Profile run configuration,
 * or directly with `generateBaselineProfile` Gradle task:
 * ```
 * ./gradlew :app:generateReleaseBaselineProfile -Pandroid.testInstrumentationRunnerArguments.androidx.benchmark.enabledRules=BaselineProfile
 * ```
 * The run configuration runs the Gradle task and applies filtering to run only the generators.
 *
 * Check [documentation](https://d.android.com/topic/performance/benchmarking/macrobenchmark-instrumentation-args)
 * for more information about available instrumentation arguments.
 *
 * After you run the generator, you can verify the improvements running the [StartupBenchmarks] benchmark.
 **/
@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() {
        rule.collect("net.composegridperformance") {
            coilImageGridJourney()
            glideImageGridJourney()
            paginatedImagesGridJourney()
            paginatedColorsGridJourney()
        }
    }
}

internal fun MacrobenchmarkScope.coilImageGridJourney() {
    pressHome()
    startActivityAndWait()

    device.wait(Until.hasObject(By.res("menu_images_list")), 5_000)
    device.findObject(By.res("menu_images_list")).click()

    device.wait(Until.hasObject(By.res("items_grid")), 5_000)
    val contentList = device.findObject(By.res("items_grid"))
    contentList?.wait(Until.hasObject(By.res("grid_item")), 5_000)

    // Set gesture margin to avoid triggering gesture navigation.
    contentList?.setGestureMargin(device.displayWidth / 3)
    contentList?.fling(Direction.DOWN)
    contentList?.fling(Direction.DOWN)
    contentList?.let { device.waitForIdle() }
}

internal fun MacrobenchmarkScope.glideImageGridJourney() {
    pressHome()
    startActivityAndWait()

    device.wait(Until.hasObject(By.res("menu_glide_images_list")), 5_000)
    device.findObject(By.res("menu_images_list")).click()

    device.wait(Until.hasObject(By.res("items_grid")), 5_000)
    val contentList = device.findObject(By.res("items_grid"))
    contentList?.wait(Until.hasObject(By.res("grid_item")), 5_000)

    // Set gesture margin to avoid triggering gesture navigation.
    contentList?.setGestureMargin(device.displayWidth / 3)
    contentList?.fling(Direction.DOWN)
    contentList?.fling(Direction.DOWN)
    contentList?.let { device.waitForIdle() }
}

internal fun MacrobenchmarkScope.paginatedImagesGridJourney() {
    pressHome()
    startActivityAndWait()

    device.wait(Until.hasObject(By.res("menu_paginated_images_list")), 5_000)
    device.findObject(By.res("menu_paginated_colors_list")).click()

    device.wait(Until.hasObject(By.res("items_grid")), 5_000)
    val contentList = device.findObject(By.res("items_grid"))
    contentList?.wait(Until.hasObject(By.res("grid_item")), 5_000)

    // Set gesture margin to avoid triggering gesture navigation.
    contentList?.setGestureMargin(device.displayWidth / 3)
    contentList?.fling(Direction.DOWN)
    contentList?.fling(Direction.DOWN)
    contentList?.let { device.waitForIdle() }
}

internal fun MacrobenchmarkScope.paginatedColorsGridJourney() {
    pressHome()
    startActivityAndWait()

    device.wait(Until.hasObject(By.res("menu_paginated_colors_list")), 5_000)
    device.findObject(By.res("menu_paginated_colors_list")).click()

    device.wait(Until.hasObject(By.res("items_grid")), 5_000)
    val contentList = device.findObject(By.res("items_grid"))
    contentList?.wait(Until.hasObject(By.res("grid_item")), 5_000)

    // Set gesture margin to avoid triggering gesture navigation.
    contentList?.setGestureMargin(device.displayWidth / 3)
    contentList?.fling(Direction.DOWN)
    contentList?.fling(Direction.DOWN)
    contentList?.let { device.waitForIdle() }
}