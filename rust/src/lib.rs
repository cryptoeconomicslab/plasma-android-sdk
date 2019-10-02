#![cfg(target_os = "android")]
#![allow(non_snake_case)]

use std::ffi::{CStr, CString};
use std::thread;

use jni::objects::{JObject, JString, JValue};
use jni::sys::{jint, jstring};
use jni::JNIEnv;

#[macro_use]
extern crate log;
extern crate android_logger;

use android_logger::{Config, FilterBuilder};
use bytes::Bytes;
use ethereum_types::Address;
use log::Level;
use plasma_clients::AndroidClient;

#[no_mangle]
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_hello_1world_HelloWorld_hello(
    env: JNIEnv,
    _: JObject,
    j_recipient: JString,
) -> jstring {
    let recipient = CString::from(CStr::from_ptr(
        env.get_string(j_recipient).unwrap().as_ptr(),
    ));

    let output = env
        .new_string(
            "Hello ".to_owned() + recipient.to_str().unwrap(),
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

#[no_mangle]
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_pubsub_Client_createAccount(
    env: JNIEnv,
    _: JObject
) -> jstring {
    android_logger::init_once(Config::default().with_min_level(Level::Trace));

    let session = AndroidClient::create_account();

    env.new_string(format!("{:?}", session))
        .unwrap()
        .into_inner()
}

#[no_mangle]
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_pubsub_Client_getAddress(
    env: JNIEnv,
    _: JObject,
    j_session: JString,
) -> jstring {
    let session = CString::from(CStr::from_ptr(env.get_string(j_session).unwrap().as_ptr()));
    android_logger::init_once(Config::default().with_min_level(Level::Trace));

    let address = AndroidClient::get_address(session.to_str().unwrap().to_string());

    env.new_string(format!("{:?}", address))
        .unwrap()
        .into_inner()
}

#[no_mangle]
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_pubsub_Client_getBalance(
    env: JNIEnv,
    _: JObject,
    j_session: JString,
) -> jstring {
    let session = CString::from(CStr::from_ptr(env.get_string(j_session).unwrap().as_ptr()));
    android_logger::init_once(Config::default().with_min_level(Level::Trace));

    let balance = AndroidClient::get_balance(session.to_str().unwrap().to_string());

    env.new_string(format!("{:?}", balance))
        .unwrap()
        .into_inner()
}

/*

#[no_mangle]
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_pubsub_Client_send(
    env: JNIEnv,
    _: JObject,
    j_endpoint: JString,
    j_secretkey: JString,
    j_start: jint,
    j_end: jint,
    j_to: JString,
) -> jstring {
    let endpoint = CString::from(CStr::from_ptr(env.get_string(j_endpoint).unwrap().as_ptr()));
    let secretkey = CString::from(CStr::from_ptr(
        env.get_string(j_secretkey).unwrap().as_ptr(),
    ));
    let to = CString::from(CStr::from_ptr(env.get_string(j_to).unwrap().as_ptr()));

    android_logger::init_once(Config::default().with_min_level(Level::Trace));
    let client = PlasmaClient::<CoreDbMemoryImpl>::new(
        Address::zero(),
        endpoint.to_str().unwrap().to_string(),
        secretkey.to_str().unwrap(),
    );

    let tx = client.create_transaction(
        Range::new(j_start as u64, j_end as u64),
        Bytes::from(hex::decode(to.to_str().unwrap()).unwrap()),
    );
    client.send_transaction(tx);

    env.new_string(format!("Sended!")).unwrap().into_inner()
}
*/