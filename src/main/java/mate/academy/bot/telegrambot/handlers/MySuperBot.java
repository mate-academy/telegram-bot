package mate.academy.bot.telegrambot.handlers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MySuperBot extends TelegramLongPollingBot {
    private static final String WHAT_THE_TIME_REQUEST = "What time is now?";
    public static final String WHAT_THE_DATE_REQUEST = "What date is today?";
    public static final String WHAT_IS_THE_CAPITAL_OF_GB_REQUEST = "What the city is the capital of GB?";
    public static final String ORDER_PIZZA_REQUEST = "Order pizza";
    public static final String DRINKS_REQUEST = "Drinks";
    public static final String PIZZA_3_REQUEST = "Pizza 3";
    public static final String MARGARITA = "Margarita";
    public static final String KARBONARA_USUAL = "Karbonara 36 as usual? " + Emoji.SMILING_FACE_WITH_OPEN_MOUTH;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        message.getPassportData();
        try {
            execute(getResponseMessage(message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage getResponseMessage(Message message) {
        switch (message.getText()) {
            case WHAT_THE_TIME_REQUEST:
                return getCurrentTimeResponse(message);
            case ORDER_PIZZA_REQUEST:
                return getOrderPizzaResponse(message);
            default:
                return greetingMessage(message);
        }
    }

    private SendMessage greetingMessage(Message message) {
        SendMessage response = new SendMessage();
        response.setText("Hello, " + message.getFrom().getFirstName()
                + ". You have send me: " + message.getText());
        response.setChatId(message.getChatId());
        response.setReplyMarkup(getMainMenu());
        return response;
    }

    @Override
    public String getBotUsername() {
        return "mate_academy_november_bot";
    }

    @Override
    public String getBotToken() {
        return "1023937085:AAGP_ALkGka7SqeVBqWvTYuBRFopRzaQ_-U";
    }

    private ReplyKeyboardMarkup getMainMenu() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        KeyboardRow row1 = new KeyboardRow();
        row1.add(WHAT_THE_TIME_REQUEST);
        row1.add(WHAT_THE_DATE_REQUEST);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(WHAT_IS_THE_CAPITAL_OF_GB_REQUEST);
        row2.add(ORDER_PIZZA_REQUEST);

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        markup.setKeyboard(rows);
        return markup;
    }

    private SendMessage getCurrentTimeResponse(Message message) {
        SendMessage response = new SendMessage();
        response.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        response.setChatId(message.getChatId());
        response.setReplyMarkup(getMainMenu());
        return response;
    }

    private ReplyKeyboardMarkup createChoosePizzaMenu() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        KeyboardRow row1 = new KeyboardRow();
        row1.add(KARBONARA_USUAL);
        row1.add(MARGARITA);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(PIZZA_3_REQUEST);
        row2.add(DRINKS_REQUEST);

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        markup.setKeyboard(rows);
        return markup;
    }

    private SendMessage getOrderPizzaResponse(Message message) {
        SendMessage response = new SendMessage();
        response.setText("Please make your choise " + Emoji.GRINNING_FACE_WITH_SMILING_EYES);
        response.setReplyMarkup(createChoosePizzaMenu());
        response.setChatId(message.getChatId());
        return response;
    }
}

enum Emoji {
    // Emoticones group
    GRINNING_FACE_WITH_SMILING_EYES('\uD83D', '\uDE01'),
    SMILING_FACE_WITH_OPEN_MOUTH('\uD83D', '\uDE03');
    Character firstChar;
    Character secondChar;

    Emoji(Character firstChar, Character secondChar) {
        this.firstChar = firstChar;
        this.secondChar = secondChar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.firstChar != null) {
            sb.append(this.firstChar);
        }
        if (this.secondChar != null) {
            sb.append(this.secondChar);
        }

        return sb.toString();
    }
}