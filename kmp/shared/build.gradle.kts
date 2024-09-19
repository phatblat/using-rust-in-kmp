import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.file.File.Companion.javaHome

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val androidNativeTargets = listOf(
        androidNativeArm64(),   // arm64-v8a
        // arm32 fails with linker errors
//        androidNativeArm32(),   // armeabi-v7a
        androidNativeX64(),     // x86_64
        androidNativeX86(),     // x86 32-bit
    )
    androidNativeTargets.forEach { target ->
        target.binaries {
            sharedLib {
                baseName = "shared"
            }
        }
        target.compilations.named("main") {
            cinterops {
                val jni by creating {
                    val ndkPath = "/Users/phatblat/Library/Android/sdk/ndk/27.1.12297006/toolchains/llvm/prebuilt/darwin-x86_64/sysroot/usr"

                    includeDirs(
                        Callable { File(ndkPath, "include") },
                        Callable { File(ndkPath, "include/android") },
                    )
                }
            }
        }
    }

    val iosTargets = listOf(
        iosArm64(),
        iosSimulatorArm64(),
        iosX64(),
    )

    iosTargets.forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    val nativeTargets = iosTargets + androidNativeTargets
    nativeTargets.forEach { target ->
        target.compilations.named("main") {
            cinterops {
                val rust by creating
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
    }
}

android {
    namespace = "live.ditto.droidcon.rust_in_kmp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        ndk {
            abiFilters.add("arm64-v8a")
        }
    }

    ndkVersion = "27.1.12297006"
}

val copyNativeLibs = tasks.register<Copy>("copyNativeLibs") {
    from(layout.buildDirectory.dir("bin/androidNativeArm64/debugShared/libshared.so"))
    into(layout.projectDirectory.dir("src/androidMain/jniLibs/arm64-v8a"))
}

//    val assemble by getting
val linkDebugSharedAndroidNativeArm64 by tasks.getting
copyNativeLibs.dependsOn(linkDebugSharedAndroidNativeArm64)

afterEvaluate {
    val mergeDebugJniLibFolders by tasks.getting
    mergeDebugJniLibFolders.dependsOn(copyNativeLibs)
    val mergeReleaseJniLibFolders by tasks.getting
    mergeReleaseJniLibFolders.dependsOn(copyNativeLibs)
}
