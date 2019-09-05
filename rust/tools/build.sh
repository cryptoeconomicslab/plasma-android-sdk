#!/usr/bin/env bash

file_name="libplasma_android_sdk.so"
jni_path="../plasma_android_sdk/jni-libs"
ndk_path="$HOME/.NDK"

cp config ~/.cargo/config

# Cargo don't set CC(compiler) and AR(archiver) environments now.
# This is urgent workaround.
# see https://github.com/alexcrichton/cc-rs/issues/82
 OPENSSL_DIR=/Users/tkmct/playground/plasma-android-sdk/rust/libs/arm64-v8a AR=${ndk_path}/arm64/bin/aarch64-linux-android-ar CC=${ndk_path}/arm64/bin/aarch64-linux-android-clang cargo build --target aarch64-linux-android --release
 OPENSSL_DIR=/Users/tkmct/playground/plasma-android-sdk/rust/libs/armeabi-v7a AR=${ndk_path}/arm/bin/arm-linux-androideabi-ar CC=${ndk_path}/arm/bin/arm-linux-androideabi-clang cargo build --target armv7-linux-androideabi --release
OPENSSL_DIR=/Users/tkmct/playground/plasma-android-sdk/rust/libs/x86 AR=${ndk_path}/x86/bin/i686-linux-android-ar CC=${ndk_path}/x86/bin/i686-linux-android-clang cargo build --target i686-linux-android --release

# directory name should be matched with ABI
# see https://forge.rust-lang.org/platform-support.html for rust targets
# see https://developer.android.com/ndk/guides/abis for abi name
 cp ./target/aarch64-linux-android/release/${file_name} ${jni_path}/arm64-v8a/${file_name}
 cp ./target/armv7-linux-androideabi/release/${file_name} ${jni_path}/armeabi-v7a/${file_name}
cp ./target/i686-linux-android/release/${file_name} ${jni_path}/x86/${file_name}
