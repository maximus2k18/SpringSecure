# Используем OpenJDK(eclipse) как базовый образ
FROM eclipse-temurin:22-jdk-alpine

# Указываем рабочую директорию в контейнере
WORKDIR /app

# Копируем jar-файл приложения (замените myapp.jar на имя вашего jar)
COPY target/*.jar app.jar

# Указываем порт, который будет открыт
EXPOSE 8083

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]