package live.ditto.droidcon.rust_in_kmp

import kotlinx.cinterop.ExperimentalForeignApi
import rust.add_numbers

class AndroidNativePlatform : Platform {
    override val name: String = "Android version ?"
}

actual fun getPlatform(): Platform = AndroidNativePlatform()
