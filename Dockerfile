FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_tg_redir_bot
ENV BOT_TOKEN=password
ENV BOT_DB_USERNAME=tgnb_db_user
ENV BOT_DB_PASSWORD=tgnb_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}","-Dspring.datasource.username=${BOT_DB_USERNAME}" "-jar", "app.jar"]
