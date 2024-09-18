package live.ditto.droidcon.rust_in_kmp

class AndroidNativePlatform : Platform {
    override val name: String = "Android version ?"
}

actual fun getPlatform(): Platform = AndroidNativePlatform()
