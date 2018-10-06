package ru.alekseikirzhaev.telegrambot

import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TelegramApplication

fun main(args: Array<String>) {
    val app = SpringApplication(TelegramApplication::class.java)
    app.setBannerMode(Banner.Mode.OFF)
    app.run(*args)
}
