package com.demo.service;

import com.alibaba.fastjson.JSON;
import com.demo.config.BotConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CustomBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(CustomBot.class);

    public CustomBot(DefaultBotOptions options) {
        super(options);
    }

    @Autowired
    private BotConfigProperties configProperties;

    @Override
    public String getBotUsername() {
        logger.info("load bot username [{}]", configProperties.getUsername());
        return configProperties.getUsername();
    }

    @Override
    public String getBotToken() {
        logger.info("load bot token [{}]", configProperties.getToken());
        return configProperties.getToken();
    }

    @Override
    public void onUpdateReceived(Update receiveData) {
        try {
            logger.info("received msg [{}]", JSON.toJSONString(receiveData, true));
            SendMessage message = new SendMessage();
            //文本消息 接收到文本消息 返回原始消息
            if (StringUtils.hasText(receiveData.getMessage().getText())) {

                message.setChatId(receiveData.getMessage().getChatId().toString());
                message.setText(receiveData.getMessage().getText());
            }
            //贴纸消息
            if (receiveData.getMessage().getSticker() != null) {
                message.setChatId(receiveData.getMessage().getChatId().toString());
                message.setText("贴纸暂不支持回复");
            }
            execute(message);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
