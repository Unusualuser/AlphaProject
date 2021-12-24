# Тестовое задание от Альфа-Банка
## Описание
> **Создать сервис, который обращается к сервису курсов валют, и отображает gif:** <br>
• если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich <br>
• если ниже - отсюда https://giphy.com/search/broke <br>
***Ссылки*** <br>
• REST API курсов валют - https://docs.openexchangerates.org/ <br>
• REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide <br>
***Must Have*** <br>
• Сервис на Spring Boot 2 + Java / Kotlin <br>
• Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD <br>
• Для взаимодействия с внешними сервисами используется Feign <br>
• Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки <br>
• На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock) <br>
• Для сборки должен использоваться Gradle<br>
• Результатом выполнения должен быть репо на GitHub с инструкцией по запуску <br>
***Nice to Have*** <br>
• Сборка и запуск Docker контейнера с этим сервисом <br>

## API
> */api/gif?currencyCode={currencyCode}* <br>

Принимает на вход currencyCode в виде 3-х символьного обозначения валюты: <br>
* Пример запроса: `http://localhost:8080/api/gif?currencyCode=RUB` <br>

Возвращает gif в соответствии с условием

## Инструкция по сборке и запуску Docker-контейнера с этим сервисом
1. Скачать репозиторий: <br> `git clone https://github.com/Unusualuser/AlphaProject.git` <br>
2. Из корневой директории выполнить команду для сборки приложения: <br> `gradlew build` <br>
3. Создать docker-образ командой: <br> `docker build -t alpha-project .` <br>
4. Запустить docker-контейнер: <br> `docker run -p 8080:8080 alpha-project` <br>
