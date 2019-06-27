#!/usr/bin/env bash

# NDK_HOME should be like
# /Users/user/Library/Android/sdk/ndk-bundle

${NDK_HOME}/build/tools/make_standalone_toolchain.py --force --api 28 --arch arm64 --install-dir ${HOME}/.NDK/arm64;
${NDK_HOME}/build/tools/make_standalone_toolchain.py --force --api 28 --arch arm --install-dir ${HOME}/.NDK/arm;
${NDK_HOME}/build/tools/make_standalone_toolchain.py --force --api 28 --arch x86 --install-dir ${HOME}/.NDK/x86;