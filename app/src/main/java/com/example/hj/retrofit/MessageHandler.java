package com.example.hj.retrofit;

public class MessageHandler {
    private static MessageHandler handler;

    private MessageHandler()
    {

    }

    public static MessageHandler msgHandler()
    {
        return handler;
    }
}
