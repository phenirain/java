import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final float RADIUS = 6371; // задаем константу радиусу
        Scanner sc = new Scanner(System.in); // создаем объект сканера
        System.out.println("Первая точка");
        System.out.print("Широта: ");
        double lat1 = Math.toRadians(sc.nextFloat()); // запращиваем широту первой точки и конвертируем в радианы
        sc.nextLine();
        System.out.print("Долгота: ");
        double lon1 = Math.toRadians(sc.nextFloat()); // запращиваем долготу первой точки и конвертируем в радианы
        sc.nextLine();
        System.out.println("Вторая точка");
        System.out.print("Широта: ");
        double lat2 = Math.toRadians(sc.nextFloat()); // запращиваем широту второй точки и конвертируем в радианы
        sc.nextLine();
        System.out.print("Долгота: ");
        double lon2 = Math.toRadians(sc.nextFloat()); // запращиваем долготу второй точки и конвертируем в радианы
        sc.nextLine();
        double latDelta = lat2 - lat1; // ищем разницу в ширине
        double lonDelta = lon2 - lon1; // ищем разницу в долготе
        double temporary = Math.pow(Math.sin(latDelta / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(lonDelta / 2), 2); // выполняем промежуточные подсчеты
        double result = 2 * Math.atan2(Math.sqrt(temporary), Math.sqrt(1 - temporary)) * RADIUS; // считаем и умножем результат на радиус
        System.out.println(result); // выводим результат
        sc.close();
    }
}