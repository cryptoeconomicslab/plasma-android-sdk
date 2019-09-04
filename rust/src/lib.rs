#![cfg(target_os = "android")]
#![allow(non_snake_case)]

use std::ffi::{CStr, CString};
use std::thread;

use jni::objects::{JObject, JString, JValue};
use jni::sys::jstring;
use jni::JNIEnv;


#[macro_use]
extern crate log;
extern crate android_logger;

use android_logger::{Config,FilterBuilder};
use log::Level;

use plasma_core::data_structure::Range;
use pubsub_messaging:: {connect, ClientHandler as Handler, Message, Sender};

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
    message: jstring,
) -> jstring {
    android_logger::init_once(Config::default().with_min_level(Level::Trace));

    let handler = Handle();
    let mut client = connect("10.0.2.2:8080", handler).unwrap();
    let msg = Message::new("SERVER".to_string(), b"Hello from Android".to_vec());
    client.send(msg.clone());

    env.new_string(format!("I am client, I sent message: {:?}", msg))
        .unwrap()
        .into_inner()
}

