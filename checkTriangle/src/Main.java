import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // создаем Scanner
        System.out.print("input a: ");
        int a = sc.nextInt(); // first side
        sc.nextLine();
        System.out.print("input b: "); // second side
        int b = sc.nextInt();
        sc.nextLine();
        System.out.print("input c: "); // third side
        int c = sc.nextInt();
        sc.nextLine();
        sc.close();
        boolean right = a * a + b * b == c * c || b * b + c * c == a * a || c * c + a * a == b * b; // check right
        boolean isosceles = a == b || b == c || c == a; // check isosceles
        boolean valid = a + b > c && b + c > a && c + a > b; // check valid
        boolean equal = a == b && b == c; // check equal sides
        if (valid) { // if triangle is valid
            if (right) { // check right
                System.out.println("a, b и c являются сторонами прямоугольного треугольника");
                return; // return to end program
            } else if (equal) { // check equal sides
                System.out.println("a, b и c являются сторонами равностороннего треугольника");
                return; // return to end program
            } else if (isosceles) { // check isosceles
                System.out.println("a, b и c являются сторонами равнобедренного треугольника");
                return; // return to end program
            }
            System.out.println("a, b и c являются сторонами обычного треугольника");
        } else { // else print no valid
            System.out.println("a, b и c не являются сторонами треугольника");
        }
    }
}