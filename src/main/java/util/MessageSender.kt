package ru.alekseikirzhaev.telegrambot.util

import org.springframework.web.client.RestTemplate
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import ru.alekseikirzhaev.telegrambot.model.ReplyKeyboardMarkup
import java.util.logging.Level
import java.util.logging.Logger

class MessageSender {

    private val API_ENDPOINT = "https://api.telegram.org/bot"

    private val logger: Logger = Logger.getLogger("[mybrokenmusic_bot]")

    fun sendMessage(token: String, chatId: Long, text: String) {
        val restTemplate = RestTemplate()
        val url = "$API_ENDPOINT$token/sendMessage"
        val map = hashMapOf("chat_id" to chatId, "text" to text)
        val response = restTemplate.postForEntity(url, map, String::class.java)
        logger.log(Level.INFO, "Response status code: {}", response.statusCode)
    }

    fun sendMessage(
            token: String,
            chatId: Long,
            text: String?,
            inlineKeyboardMarkup: InlineKeyboardMarkup
    ) {
        val restTemplate = RestTemplate()
        val url = "$API_ENDPOINT$token/sendMessage"
        val map = hashMapOf(
                "chat_id" to chatId,
                "text" to text,
                "reply_markup" to inlineKeyboardMarkup
        )
        logger.log(
                Level.INFO,
                "Map value",
                map.toString()
        )
        val response = restTemplate.postForEntity(url, map, String::class.java)
        logger.log(Level.INFO, "Response status code: {}", response.statusCode)
    }

    fun sendMessage(
            token: String,
            chatId: Long,
            text: String?,
            replyKeyboardMarkup: ReplyKeyboardMarkup
    ) {
        val restTemplate = RestTemplate()
        val url = "$API_ENDPOINT$token/sendMessage"
        val map = hashMapOf(
                "chat_id" to chatId,
                "text" to text,
                "reply_markup" to replyKeyboardMarkup
        )
        val response = restTemplate.postForEntity(url, map, String::class.java)
        logger.log(Level.INFO, "Response status code: {}", response.statusCode)
    }
}
