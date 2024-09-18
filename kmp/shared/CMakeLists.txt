
cmake_minimum_required(VERSION 3.30.3)
project( rust )

set( PROJECT_ROOT_DIR ${CMAKE_CURRENT_SOURCE_DIR}/../.. )

message( PROJECT_ROOT_DIR=${PROJECT_ROOT_DIR} )

set( OUTPUT_LIB rustffi )
add_library(
        ${OUTPUT_LIB}
        SHARED
        src/cpp/dummy.cpp
)

# Header file path
include_directories( ${PROJECT_ROOT_DIR}/rust )

target_include_directories( ${OUTPUT_LIB} PRIVATE ${PROJECT_ROOT_DIR}/rust )
target_compile_features( ${OUTPUT_LIB} PUBLIC cxx_std_11)

# Map ABI to rust target
if (ANDROID_ABI STREQUAL "arm64-v8a")
    set( RUST_TARGET "aarch64-linux-android" )
elseif (ANDROID_ABI STREQUAL "armeabi-v7a")
    set( RUST_TARGET "armv7-linux-androideabi" )
elseif (ANDROID_ABI STREQUAL "x86_64")
    set( RUST_TARGET "x86_64-linux-android" )
elseif (ANDROID_ABI STREQUAL "x86")
    set( RUST_TARGET "i686-linux-android" )
else()
    message( FATAL_ERROR "Unknown Android ABI!" )
endif()

set(
        STATIC_LIBRUST_DIR
        ${PROJECT_ROOT_DIR}/rust/target/${RUST_TARGET}/debug
)

message( STATIC_LIBRUST_DIR=${STATIC_LIBRUST_DIR} )

# Paths in which the linker should search for libraries
target_link_directories(
        ${OUTPUT_LIB}
        PRIVATE
        ${STATIC_LIBRUST_DIR}
)

#find_library(
#        RUST_LIB_PATH
#        rust
#        ${STATIC_LIBRUST_DIR}
#)

message( RUST_LIB_PATH=${RUST_LIB_PATH} )

target_link_libraries(
        ${OUTPUT_LIB}
        PUBLIC
        rust
#        ../../rust/target/aarch64-linux-android/debug/librust.a
)

