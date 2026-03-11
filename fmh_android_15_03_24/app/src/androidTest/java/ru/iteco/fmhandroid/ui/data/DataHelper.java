package ru.iteco.fmhandroid.ui.data;

import com.github.javafaker.Faker;

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
    //Выбор номера карточки с цитатой
    public static int getNumberCard() {
        int num  = faker.random().nextInt(8);
        return num;
    }
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



}
