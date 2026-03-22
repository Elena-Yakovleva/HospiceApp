## Дипломный проект по профессии «Инженер по тестированию»

[Ссылка на проект](https://github.com/netology-code/qamid-diplom)

### Описание приложения:

Приложение даёт функционал по работе с новостями хосписа и включает в себя:

- информацию о новостях и функционал для работы с ними;
- тематические цитаты;
- информацию о приложении.

### Документация:

- [Чек-лист](https://github.com/Elena-Yakovleva/HospiceApp/blob/main/documentation/Check-list.xlsx)
- [Тест-кейсы](https://github.com/Elena-Yakovleva/HospiceApp/blob/main/documentation/Cases.xlsx)
- [План](https://github.com/Elena-Yakovleva/HospiceApp/blob/main/documentation/Plan.md)
- [Отчет](https://github.com/Elena-Yakovleva/HospiceApp/blob/main/documentation/Result.md)

### Запуск авто тестов:

- Необходимо склонировать проект из [репозитория](https://github.com/Elena-Yakovleva/HospiceApp) командой `git clone`
- Запустить Android Studio и создать эмулятор Эмулятор Pixel 4 API 29 Android 10.0("Q") x86
- Запуск автотестов осуществляется командой `./gradlew connectedAndroidTest`
- Запуск отдельного теста осуществляется кнопкой Play в тестовом классе.
- После завершения автотестов необходимо на эмуляторе( с помощью Device Explorer), перейти в директорию `/data/data/ru.iteco.fmhandroid.ui/files/allure-results` , и с помощью `Save As` сохранить отчеты в папку allure-result в корневой директории проекта.
- Для запуска отчета используется команда `allure serve` в папке проекта.
