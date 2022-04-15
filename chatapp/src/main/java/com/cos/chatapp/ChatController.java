package com.cos.chatapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

// https://github.com/codingspecialist/springboot-webflux-mongo-chatapp/tree/master/chatapp/src/main/java/com/cos/chatapp
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatRepository chatRepository;

    // 귓속말할때 사용
    @CrossOrigin
    @GetMapping(value = "/sender/{sender}/receiver/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // produces = MediaType.TEXT_EVENT_STREAM_VALUE 데이터가 생길때마다 전송한다.
    public Flux<Chat> getMsg(@PathVariable String sender, @PathVariable String receiver){
        return chatRepository.mFindBySender(sender,receiver).subscribeOn(Schedulers.boundedElastic());
    }

    @CrossOrigin
    @GetMapping(value = "/chat/roomNum/{roomNum}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> findByRoomNum(@PathVariable Integer roomNum) {
        return chatRepository.mFindByRoomNum(roomNum)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @CrossOrigin
    @PostMapping("/chat")
    public Mono<Chat> setMsg(@RequestBody Chat chat){
        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat); // Object를 리턴하면 자동으로 JSON 변환 (MessageConverter)
    }
}
