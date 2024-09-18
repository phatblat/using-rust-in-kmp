package live.ditto.droidcon.rust_in_kmp

import kotlinx.cinterop.ExperimentalForeignApi
import rust.add_numbers

@OptIn(ExperimentalForeignApi::class)
actual fun add(a: Int, b: Int): Int {
    return add_numbers(a, b)
}
