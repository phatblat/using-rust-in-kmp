targets := "aarch64-apple-ios aarch64-apple-ios-sim x86_64-apple-ios aarch64-linux-android armv7-linux-androideabi x86_64-linux-android i686-linux-android"

default:
    @just --list

install-targets:
    for target in {{targets}}; do \
        rustup target add $target; \
    done

clean:
    cargo clean \
        --manifest-path rust/Cargo.toml

    kmp/gradlew --quiet \
        --project-dir kmp/ \
        clean

build:
    @echo '🦀 Building rust'
    for target in {{targets}}; do \
        cargo build \
            --target=$target \
            --manifest-path rust/Cargo.toml; \
    done

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
