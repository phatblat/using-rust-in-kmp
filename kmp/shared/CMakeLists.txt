
cmake_minimum_required(VERSION 3.30.3)
project( rust )

add_library(
    rust
    SHARED
    src/cpp/dummy.cpp
)

include_directories( ../../rust )

