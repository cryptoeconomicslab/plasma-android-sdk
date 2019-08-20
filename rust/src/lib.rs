#![cfg(target_os = "android")]
#![allow(non_snake_case)]

use std::ffi::{CStr, CString};

use jni::objects::{JObject, JString, JValue};
use jni::sys::jstring;
use jni::JNIEnv;

use plasma_core::data_structure::Range;

#[no_mangle]
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_hello_1world_HelloWorld_hello(
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
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_database_DatabaseClient_insertFromRust(
    env: JNIEnv,
    _: JObject,
    message: jstring,
    executor: JObject,
) {
    env.call_method(
        executor,
        "insert",
        "(Ljava/lang/String;)V",
        &[JValue::Object(message.into())],
    )
    .unwrap();
}
