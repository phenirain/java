import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] titles = new String[]{"ROLEX", "CASIO", "CARTIER", "PATEK PHILIPPE"};
        String[][] marks = new String[][]{
                {"ROLEX", "aaaa"},
                {"CASIO","bbbb"},
                {"CARTIER", "cccc"},
                {"PATEK PHILIPPE", "eeee"}
        };

        ArrayList<Object> customers = new ArrayList<Object>();
        Scanner sc = new Scanner(System.in);
        String customer, email, phone, choice;
        int count;
        while (true) {
            System.out.println("Выберите действие: ");
            System.out.println("1 - купить");
            System.out.println("2 - посмотреть все заказы");
            System.out.println("3 - завершить программу");
            choice = sc.nextLine();
            if (choice.equals("1")) {
                System.out.print("Введите Ваше ФИО: ");
                customer = sc.nextLine();
                System.out.print("Введите Вашу почту: ");
                email = sc.nextLine();
                System.out.print("Введите Ваш телефон: ");
                phone = sc.nextLine();
                System.out.println("Выберите товар: ");
                for (int i=0; i< marks.length; i++) {
                    if (marks[i][0] != null) System.out.printf("Товар %d: %s - %s,%n", i + 1,marks[i][0], marks[i][1]);
                }
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Введите количество: ");
                count = sc.nextInt();
                sc.nextLine();
                customers.add(new Object[]
                        {customer, email, phone, new String[] {titles[id - 1], marks[id - 1][1]}, count});
                marks[id - 1][0] = null;
            } else if (choice.equals("2")) {
                if (customers.isEmpty()) System.out.println("Нет ни одного покупателя");
                else {
                    for (int i = 0; i < customers.size(); i++) {
                        Object[] purchase = (Object[]) customers.get(i);
                        String[] item = (String[]) purchase[3];
                        System.out.printf("Заказчик: %s%nКонтакты: %s - %s%nТовар: %s - %s%nКоличество: %d%n" +
                                        "////////////////////////%n",
                                purchase[0],
                                purchase[1],
                                purchase[2],
                                item[0],
                                item[1],
                                (int)purchase[4]
                        );
                    }
                }
            } else {
                System.out.println("Пока");
                sc.close();
                break;
            }
        }

    }
}