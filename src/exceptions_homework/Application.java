package exceptions_homework;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите Фамилию, Имя, Отчество, дату рождения (в формате dd.mm.yyyy), номер телефона и пол (f-женщина или m-мужчина), разделенные пробелом:");

            String[] userData = scanner.nextLine().split("\\s+");

            if (userData.length != 6) {
                throw new IllegalArgumentException("Неверное количество аргументов. Пожалуйста, введите Фамилию, Имя, Отчество, дату рождения, номер телефона и пол.");
            }

            String lastName = userData[0];
            String firstName = userData[1];
            String patronymic = userData[2];
            String dateOfBirth = userData[3];
            String phoneNumber = userData[4];
            String gender = userData[5];

            // Проверка формата данных
            validateData(lastName, firstName, patronymic, dateOfBirth, phoneNumber, gender);

            // Создание строки для записи в файл
            String userDataString = lastName + " " + firstName + " " + patronymic + " " + dateOfBirth + " " + phoneNumber + " " + gender;

            // Запись в файл
            writeToFile(lastName, userDataString);

            scanner.close();
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateData(String lastName, String firstName, String patronymic, String dateOfBirth, String phoneNumber, String gender) {
        // Проверка формата даты рождения
        if (!dateOfBirth.matches("\\d{2}.\\d{2}.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Используйте формат dd.mm.yyyy.");
        }

        // Проверка формата номера телефона
        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат номера телефона. Введите целое беззнаковое число без форматирования.");
        }

        // Проверка пола
        if (!gender.matches("[mf]")) {
            throw new IllegalArgumentException("Неверное значение пола. Используйте 'f' для женского и 'm' для мужского.");
        }
    }

    private static void writeToFile(String lastName, String userDataString) throws IOException {
        try (FileWriter writer = new FileWriter(lastName + ".txt", true)) {
            writer.write(userDataString + "\n");
        }
    }
}
