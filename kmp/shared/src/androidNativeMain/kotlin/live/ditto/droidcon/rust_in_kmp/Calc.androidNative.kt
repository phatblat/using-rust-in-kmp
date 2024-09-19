package live.ditto.droidcon.rust_in_kmp

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import platform.android.JNIEnvVar
import platform.android.jclass
import platform.android.jint
import rust.add_numbers
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
@CName("Java_live_ditto_droidcon_rust_1in_1kmp_CalcBridge_00024Companion_jniAdd")
fun calcBridge(env: CPointer<JNIEnvVar>, clazz: jclass, a: jint, b: jint): jint {
    println("Inside calcBridge")
    return add(a, b)
}

@OptIn(ExperimentalForeignApi::class)
actual fun add(a: Int, b: Int): Int {
    return add_numbers(a, b)
}

