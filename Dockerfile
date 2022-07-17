FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_tg_redir_bot
ENV BOT_TOKEN=5590003102:AAE_NsuQEoWUUskalrX7qY492fGSV8BfWIQ
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "app.jar"]
