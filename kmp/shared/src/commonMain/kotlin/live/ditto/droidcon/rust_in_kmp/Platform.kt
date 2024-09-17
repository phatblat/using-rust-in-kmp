package live.ditto.droidcon.rust_in_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform