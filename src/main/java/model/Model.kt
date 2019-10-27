package ru.alekseikirzhaev.telegrambot.model

import java.util.*

data class PhotoSize(
        val file_id: String,
        val width: Int,
        val height: Int,
        val file_size: Long?
)

data class ChatPhoto(
        val small_file_id: String,
        val big_file_id: String
)

data class InlineKeyboardButton(
        val text: String?,
        val url: String?,
        val callback_data: String?,
        val pay: Boolean?
)

data class KeyboardButton(
        val text: String,
        val request_contact: Boolean,
        val request_location: Boolean
)

data class InlineKeyboardMarkup(
        var inline_keyboard: ArrayList<ArrayList<InlineKeyboardButton>>
)

data class ReplyKeyboardMarkup(
        val keyboard: ArrayList<ArrayList<KeyboardButton>>,
        val resize_keyboard: Boolean,
        val one_time_keyboard: Boolean,
        val selective: Boolean
)

data class MessageEntity(
        val type: String,
        val offset: Int,
        val length: Int,
        val url: String?,
        val user: User?
)

data class User(
        val id: Long,
        val is_bot: Boolean,
        val first_name: String,
        val last_name: String?,
        val username: String?,
        val language_code: String?
)

data class Update(
        val update_id: Long,
        val message: Message?,
        val channel_post: Message?
)

data class Message(
        val message_id: Long,
        val date: Date,
        val chat: Chat,
        val text: String?,
        val from: User?,
        val entities: ArrayList<MessageEntity>?,
        val caption_entities: ArrayList<MessageEntity>?,
        val photo: PhotoSize?
)

data class Chat(
        val id: Long,
        val type: String,
        val title: String?,
        val username: String?,
        val first_name: String?,
        val last_name: String?,
        val all_members_are_administrators: Boolean?,
        val description: String?,
        val invite_link: String?,
        val sticker_set_name: String?,
        val can_set_sticker_set: String?,
        val pinned_message: Message?,
        val photo: ChatPhoto?
)
