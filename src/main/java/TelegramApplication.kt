package ru.alekseikirzhaev.telegrambot

import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
open class TelegramApplication

fun main(args: Array<String>) {
/*    Authenticator.setDefault(object : Authenticator() {

        public override fun getPasswordAuthentication(): PasswordAuthentication {

            return PasswordAuthentication(proxyLogin, proxyPassword.toCharArray())
        }
    })  */

    ApiContextInitializer.init()

    val app = SpringApplication(TelegramApplication::class.java)
    app.setBannerMode(Banner.Mode.OFF)
    app.run(*args)
}
