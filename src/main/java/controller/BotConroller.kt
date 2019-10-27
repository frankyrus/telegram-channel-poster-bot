package ru.alekseikirzhaev.telegrambot.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import ru.alekseikirzhaev.telegrambot.util.MessageSender
import java.util.logging.Level
import java.util.logging.Logger

@Component
class BotConroller : TelegramWebhookBot() {

    @Value("\${bot.username}")
    lateinit var botName: String

    @Value("\${bot.token}")
    lateinit var token: String

    override fun getBotPath(): String {
        return botName
    }

    override fun getBotUsername(): String {
        return botName
    }

    override fun onWebhookUpdateReceived(update: Update?): BotApiMethod<*> {
        logger.log(Level.INFO, update?.message?.text)
        return onUpdate(update)
    }

    override fun getBotToken(): String {
        return token
    }

    companion object {
        private const val START_COMMAND = "/start"
        private const val ECHO_COMMAND = "/echo"
        private const val INLINE_COMMAND = "/testInline"
        private const val REPLY_COMMAND = "/testReply"
    }

    private val logger: Logger = Logger.getLogger("[mybrokenmusic_bot]")
    private val sender: MessageSender = MessageSender()

    private fun onUpdate(update: Update?): BotApiMethod<*> {
        logger.log(Level.INFO, "Got update: $update")

        if (update?.message != null) {
            val message = update.message
            val chatId = message.chatId
            val text = message.text
            when {
                text?.startsWith(START_COMMAND)!! -> onStartCommand(chatId)
                text.startsWith(ECHO_COMMAND) -> onEchoCommand(chatId, text)
                text.startsWith(INLINE_COMMAND) -> onInlineCommand(chatId, text)
            }
        }

        return SendMessage()
    }

    private fun onStartCommand(chatId: Long) {
/*        sender.sendMessage(
                token,
                chatId,
                "Hello! I'm mybrokenmusic_bot."
        )*/
        val message = SendMessage()
        message.chatId = chatId.toString()
        message.text = "First Text"
        val inlineKeyboardMarkup = InlineKeyboardMarkup()
        inlineKeyboardMarkup.keyboard = listOf(listOf(InlineKeyboardButton("Press Me!")))
        message.replyMarkup = inlineKeyboardMarkup
        execute(message)

    }

    private fun onEchoCommand(chatId: Long, text: String?) {
        val response = text!!.subSequence(ECHO_COMMAND.length, text.length).trim().toString()
        if (response.isEmpty()) sender.sendMessage(
                token,
                chatId,
                "Please add text after echo command"
        ) else {
            sender.sendMessage(token, chatId, response)
        }
    }

    private fun onInlineCommand(chatId: Long, text: String?) {
        val inlineKeyboardButtons = arrayListOf(arrayListOf(
                InlineKeyboardButton(
                        text
                ).setUrl("https://google.com"),
                InlineKeyboardButton(
                        "Text 2")
        ))
        val inlineKeyboardMarkup = InlineKeyboardMarkup()
        inlineKeyboardMarkup.keyboard = inlineKeyboardButtons.toList()
        logger.log(Level.INFO, "Inline list", inlineKeyboardMarkup)
        //TODO bad mapping
        try {
            sender.sendMessage(token, chatId, text, inlineKeyboardMarkup)
        } catch (e: Exception) {
            sender.sendMessage(token, chatId, e.localizedMessage)
        }
    }
}
