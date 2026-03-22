package ru.iteco.fmhandroid.ui.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    private static final Faker faker = new Faker();

    //Авторизация
    public static String getValidLogin() {
        return ("login2");
    }

    public static String getValidPassword() {
        return ("password2");
    }

    public static String emptyLogin() {
        return ("");
    }

    public static String emptyPassword() {
        return ("");
    }

    public static String getWrongLogin() {
        int wrongLogin = faker.random().nextInt(5);
        switch (wrongLogin) {
            case 0:
                return faker.name().firstName().toLowerCase();
            case 1:
                return faker.name().firstName().toUpperCase();
            case 2:
                return faker.regexify("[a-z]{5}[0-9]{3}");
            case 3:
                return faker.regexify("[a-z]{5}[!@#$%^&*()_+?><]{1}");
            case 4:
                return faker.regexify("[a-z]{1}");
            default:
                return faker.name().username();
        }
    }

    public static String getWrongPassword() {
        int wrongPass = faker.random().nextInt(5);
        switch (wrongPass) {
            case 0:
                return faker.internet().password().toLowerCase();
            case 1:
                return faker.internet().password().toUpperCase();
            case 2:
                return faker.regexify("[a-z]{5}[0-9]{3}");
            case 3:
                return faker.regexify("[a-z]{5}[!@#$%^&*()_+?><]{1}");
            case 4:
                return faker.regexify("[a-z]{1}");
            default:
                return faker.internet().password();
        }
    }
    public static String getSpacesInFrontLogin() {
        return ("   " + getValidLogin());
    }
    public static String getSpacesInFrontPassword() {
        return ("   " + getValidPassword());
    }
    public static String getSpacesAfterLogin() {
        return (getValidLogin() + "        ");
    }
    public static String getSpacesAfterPassword() {
        return (getValidPassword() + "        ");
    }

    //Сообщения об ошибках
    public static String emptyFieldsError() {
        return ("Login and password cannot be empty");
    }
    public static String retryLaterError() {
        return ("Something went wrong. Try again later.");
    }
    public static String emptyFieldsAddNewsError() {
        return ("Fill empty fields");
    }
    public static String savingFailedError() {return ("Saving failed. Try again later.");}
    public static String wrongPeriodError() {return ("Wrong period");}
    public static String nothingHereError() {return ("There is nothing here yet…");}


    //Выбор номера карточки с цитатой
    public static int getNumberCard() {
        int num  = faker.random().nextInt(8);
        return num;
    }
    //Карточки с цитатами
    public static String getTitle(int num) {

        switch (num) {
            case 0:
                return "Хоспис для меня";
            case 1:
                return "Хоспис в своем истинном понимании";
            case 2:
                return "В хосписе не работают плохие люди";
            case 3:
                return "«Хоспис – это философия";
            case 4:
                return "Служение человеку с теплом, любовью и заботой";
            case 5:
                return "Хоспис продлевает жизнь";
            case 6:
                return "Двигатель хосписа - милосердие";
            case 7:
                return "Важен каждый!";
            default:
                return "";
        }
    }
    public static String getQuote(int num) {

        switch (num) {
            case 0:
                return "Ну, идеальное устройство мира в моих глазах";
            case 1:
                return "Нет шаблона и стандарта, есть только дух";
            case 2:
                return "Все сотрудники хосписа - это адвокаты пациента";
            case 3:
                return "Творчески и осознанно подойти";
            case 4:
                return "Если пациента нельзя вылечить";
            case 5:
                return "Хоспис - это мои новые друзья";
            case 6:
                return "Делай добро";
            case 7:
                return "Каждый, кто оказывается в стенах хосписа";
            default:
                return "";
        }
    }
    //Создание новости
    //Категория
    public static String getDefaultCategory(int num) {

        switch (num) {
            case 0:
                return "Объявление";
            case 1:
                return "День рождения";
            case 2:
                return "Зарплата";
            case 3:
                return "Профсоюз";
            case 4:
                return "Праздник";
            case 5:
                return "Массаж";
            case 6:
                return "Благодарность";
            case 7:
                return "Нужна помощь";
            default:
                return "";
        }
    }
    public static String getEmptyCategory() {
        return ("");
    }
    public static String getOwnCategory(String s) {
        return (s);
    }
    //Титул
    public static String getEmptyTitle() {
        return ("");
    }
    public static String getOwnTitle() {
        return ("тест: " + faker.regexify("[a-z]{5}[0-9]{3}[a-z]{3}"));
    }
    //Дата
    public static String getOwnDate(int num, int num2) {
        return LocalDate.now().plusDays(num).minusDays(num2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    public static String getEmptyDate() {
        return ("");
    }
    public static String getFutureDate(int num) {
        return LocalDate.now().plusDays(num).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    public static String currentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    //Время
    public static String getEmptyTime() {
        return ("");
    }
    public static String currentTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    public static String futureTime(int num) {
        return LocalTime.now().plusHours(num).format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    //Текст
    public static String getEmptyDescription() {
        return ("");
    }
    public static String getDescription() {
        return ("Новость создана " + currentDate() + " в " + currentTime());
    }




















}
