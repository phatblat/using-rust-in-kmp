@file:OptIn(ExperimentalForeignApi::class)

package live.ditto.droidcon.rust_in_kmp

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIDevice
import rust.add_numbers

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

fun add(a: Int, b: Int) {
    add_numbers(a, b)
}
