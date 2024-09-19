package live.ditto.droidcon.rust_in_kmp

// dummy function to satisfy contract
actual fun add(a: Int, b: Int): Int {
    return CalcBridge.jniAdd(a, b)
}

class CalcBridge {
    companion object {
        external fun jniAdd(a: Int, b: Int): Int
    }
}
