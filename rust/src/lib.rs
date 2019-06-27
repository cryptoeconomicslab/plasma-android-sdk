#![cfg(target_os = "android")]
#![allow(non_snake_case)]

// FIXME: add package below, for now some how failed to build
// use ethereum_types::Address;
use jni::objects::{JObject, JString};
use jni::sys::jstring;
use jni::JNIEnv;
use std::ffi::{CStr, CString};

#[no_mangle]
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_ExternalMethodsKt_hello(
    env: JNIEnv,
    _: JObject,
    j_recipient: JString,
) -> jstring {
    let recipient = CString::from(CStr::from_ptr(
        env.get_string(j_recipient).unwrap().as_ptr(),
    ));

    let output = env
        .new_string("Hello ".to_owned() + recipient.to_str().unwrap())
        .unwrap();
    output.into_inner()
}
