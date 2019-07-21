#![cfg(target_os = "android")]
#![allow(non_snake_case)]

use std::ffi::{CStr, CString};

use jni::JNIEnv;
use jni::objects::{JObject, JString};
use jni::sys::{jint, jstring};
use plasma_core::data_structure::Range;

#[no_mangle]
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_ExternalMethodsKt_hello(
    env: JNIEnv,
    _: JObject,
    j_recipient: JString,
) -> jstring {
    let recipient = CString::from(CStr::from_ptr(
        env.get_string(j_recipient).unwrap().as_ptr(),
    ));

    let range = Range::new(0, 100);

    let output = env
        .new_string(
            "Hello ".to_owned() + &format!("range={:?}", range) + recipient.to_str().unwrap(),
        )
        .unwrap();
    output.into_inner()
}

#[no_mangle]
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_ExternalMethodsKt_helloFromRust(
    env: JNIEnv,
    _: JObject,
    n: jint,
    callback: JObject,
) {
    let i = n as i32;
    let res: jint = (2..i + 1).product();

    env.call_method(callback, "callBack", "(I)V", &[res.into()]).unwrap();
}
