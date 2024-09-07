default:
    echo 'Hello, world!'

build:
    @echo '🦀 Building rust'
    cargo build --manifest-path rust/Cargo.toml

    @echo
    @echo '🐘 Building KMP'
    kmp/gradlew --quiet \
        --project-dir kmp/ \
        assemble
