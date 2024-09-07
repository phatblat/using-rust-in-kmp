default:
    echo 'Hello, world!'

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
