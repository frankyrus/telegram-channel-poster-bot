package ru.alekseikirzhaev.telegrambot.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import ru.alekseikirzhaev.telegrambot.model.Update
import java.util.logging.Level
import java.util.logging.Logger

@RestController
class BotConroller {

    companion object {
        private val API_ENDPOINT = "https://api.telegram.org/bot"
        private val START_COMMAND = "/start"
        private val ECHO_COMMAND = "/echo"
    }

    private val logger: Logger = Logger.getLogger("[mybrokenmusic_bot]")

    @Value("\${token}")
    lateinit var token: String

    @PostMapping
    fun onUpdate(@RequestBody update: Update) {
        logger.log(Level.INFO, "Got update: $update")

        if (update.message != null) {
            val chatId = update.message.chat.id
            val text = update.message.text

            when {
                text.startsWith(START_COMMAND) -> onStartCommand(chatId)
                text.startsWith(ECHO_COMMAND) -> onEchoCommand(chatId, text)
            }
        }
    }

    private fun onStartCommand(chatId: Long) {
        sendMessage(chatId, "Hello! I'm mybrokenmusic_bot.")
    }

    private fun onEchoCommand(chatId: Long, text: String) {
        val response = text.subSequence(ECHO_COMMAND.length, text.length).trim().toString()
        if (response.isEmpty()) {
            sendMessage(chatId, "Please add text after echo command")
        } else {
            sendMessage(chatId, response)
        }
    }

    private fun sendMessage(chatId: Long, text: String) {
        val restTemplate = RestTemplate()
        val url = "$API_ENDPOINT$token/sendMessage"
        val map = hashMapOf("chat_id" to chatId, "text" to text)
        val response = restTemplate.postForEntity(url, map, String::class.java)
        logger.log(Level.INFO, "Response status code: {}", response.statusCode);
    }
}