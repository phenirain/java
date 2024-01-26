import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
// импортируем все необходимые данные


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            Scanner sc = new Scanner(System.in);
            // Создание объекта Scanner для считывания ввода с клавиатуры
            PrintWriter pw = new PrintWriter(new FileWriter("students.txt", true));
            // Создание объекта PrintWriter для записи в файл "students.txt" с возможностью добавления данных в конец файла
            for (int i = 0; i < 3; i++) { // Цикл, выполняющийся три раза для запроса и записи данных о студентах
                System.out.print("Введите имя: ");
                // Вывод для ввода имени студента
                String name = sc.nextLine(); // Считывание имени студента с клавиатуры
                System.out.print("Введите возраст: ");
                //  ввода возраста студента
                int age = sc.nextInt(); // Считывание возраста студента с клавиатуры
                System.out.print("Введите средний балл студента: ");
                // Вывод ввода среднего балла студента
                double average = sc.nextDouble(); // Считывание среднего балла студента с клавиатуры
                pw.printf("Имя студента: %s; Возраст: %d; Средний балл: %f\n", name, age, average); // Запись данных о студенте в файл в формате "Имя студента: [имя]; Возраст: [возраст]; Средний балл: [средний балл]"
                sc.nextLine(); // Очистка символа новой строки из входного потока
            }
            pw.close(); // Закрытие потока PrintWriter для освобождения ресурсов
            sc.close(); // Закрытие объекта Scanner для освобождения ресурсов
        }
        catch (IOException e) { // Обработка исключений
            System.out.print(e.getMessage()); // Вывод сообщения об ошибке в случае возникновения исключения
        }
    }
}
