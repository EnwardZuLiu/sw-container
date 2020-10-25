//package com.safeweb.cluster;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * author liuzm
// * createTime 2020/10/11 14:59
// */
//@Configuration
//public class DockerClientConfig {
//
//    @Bean
//    public String dockerEvent() {
//        var client = DockerClientFactory.getClientByHost("192.168.50.85");
////        client.pingCmd().exec();
//        var info = client.infoCmd().exec();
//        System.out.println(info.toString());
////        var resultCallBack = client.eventsCmd().exec(new ResultCallback<Event>() {
////            @Override
////            public void onStart(Closeable closeable) {
////
////            }
////
////            @Override
////            public void onNext(Event object) {
////                System.out.println(object.toString());
////            }
////
////            @Override
////            public void onError(Throwable throwable) {
////
////            }
////
////            @Override
////            public void onComplete() {
////
////            }
////
////            @Override
////            public void close() throws IOException {
////
////            }
////        });
//        return null;
//    }
//
//}
