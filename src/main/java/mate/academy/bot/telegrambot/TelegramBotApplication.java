package mate.academy.bot.telegrambot;

import mate.academy.bot.telegrambot.handlers.MySuperBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) throws TelegramApiRequestException {
        SpringApplication.run(TelegramBotApplication.class, args);

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(new MySuperBot());
    }

}
