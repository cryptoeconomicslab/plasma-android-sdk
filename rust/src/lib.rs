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
use plasma_clients::plasma::PlasmaClient;
use plasma_core::data_structure::Range;
use plasma_db::impls::kvs::CoreDbMemoryImpl;
use pubsub_messaging::{connect, ClientHandler as Handler, Message, Sender};

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

#[derive(Clone)]
struct Handle();

impl Handler for Handle {
    fn handle_message(&self, msg: Message, sender: Sender) {
        println!("ClientHandler handle_message: {:?}", msg);
    }
}

#[no_mangle]
pub unsafe extern "C" fn Java_com_cryptoeconomicslab_plasma_1android_1sdk_pubsub_Client_listen(
    env: JNIEnv,
    _: JObject,
    j_endpoint: JString,
) -> jstring {
    let endpoint = CString::from(CStr::from_ptr(env.get_string(j_endpoint).unwrap().as_ptr()));
    android_logger::init_once(Config::default().with_min_level(Level::Trace));

    let handler = Handle();
    let mut client = connect(endpoint.to_str().unwrap().to_string(), handler).unwrap();
    let msg = Message::new("SERVER".to_string(), b"Hello from Android".to_vec());
    client.send(msg.clone());

    env.new_string(format!("I am client, I sent message: {:?}", msg))
        .unwrap()
        .into_inner()
}

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
