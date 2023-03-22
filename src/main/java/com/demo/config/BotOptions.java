package com.demo.config;

import com.demo.service.CustomBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;


@Configuration
public class BotOptions {

    private static final Logger logger = LoggerFactory.getLogger(CustomBot.class);

    @Bean
    public DefaultBotOptions defaultBotOptions() {
        logger.info("init bot options");
        DefaultBotOptions defaultBotOptions = new DefaultBotOptions();
        defaultBotOptions.setProxyHost("127.0.0.1");
        defaultBotOptions.setProxyPort(7890);
        // Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
        defaultBotOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        return defaultBotOptions;
    }

}
