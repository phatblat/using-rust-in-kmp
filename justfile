default:
    @just --list

clean:
    cargo clean \
        --manifest-path rust/Cargo.toml

    kmp/gradlew --quiet \
        --project-dir kmp/ \
        clean

build:
    @echo '🦀 Building rust'
    cargo build \
        --manifest-path rust/Cargo.toml

    @echo
    @echo '🐘 Building KMP'
    kmp/gradlew --quiet \
        --project-dir kmp/ \
        assemble

# ~/.konan/kotlin-native-prebuilt-macos-aarch64-2.0.0/bin/cinterop
interop:
    cinterop \
        -def kmp/library/src/nativeInterop/cinterop/rust.def \
        -output rust \
        -verbose
