#!/usr/bin/env bash

file_name="libplasma_android_sdk.so"
jni_path="../plasma_android_sdk/jni-libs"

cp config ~/.cargo/config

cargo build --target aarch64-linux-android --release
cargo build --target armv7-linux-androideabi --release
cargo build --target i686-linux-android --release

cp ./target/aarch64-linux-android/release/${file_name} ${jni_path}/arm64/${file_name}
cp ./target/armv7-linux-androideabi/release/${file_name} ${jni_path}/armeabi/${file_name}
cp ./target/i686-linux-android/release/${file_name} ${jni_path}/x86/${file_name}
