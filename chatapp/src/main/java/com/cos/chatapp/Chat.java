package com.cos.chatapp;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "chat")
public class Chat {

    @Id
    private String id;

    private String msg;
    private String sender;
    private String receiver;
    private Integer roomNum;

    private LocalDateTime createdAt;
}

//db.runCommand({convertToCapped: 'chat', size: 8192});
//db 버퍼사이즈 크기 키우기