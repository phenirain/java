package org.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Castle {


    private ArrayList<Fair> allFairs = new ArrayList<Fair>() {
        {
            add(new Fair("A", 100));
            add(new Fair("B", 68));
            add(new Fair("C", 93));
            add(new Fair("D", 40));
            add(new Fair("E", 55));
        }
    };

    private ArrayList<Cooker> allCooks = new ArrayList<Cooker>() {
        {
            add(new Cooker("A", 100));
            add(new Cooker("B", 68));
            add(new Cooker("C", 93));
            add(new Cooker("D", 40));
            add(new Cooker("E", 55));
        }
    };

    private ArrayList<Ork> allOrks = new ArrayList<Ork>() {
        {
            add(new Ork("A", 100));
            add(new Ork("B", 68));
            add(new Ork("C", 93));
            add(new Ork("D", 40));
            add(new Ork("E", 55));
        }
    };

    private <T extends Resident> void listInfo(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            System.out.printf("%d: ", (i + 1));
            element.getInfo();
        }
    }

    private <T extends Resident> ArrayList<T> getResidents(ArrayList<Object> list, Class<T> clazz) {
        ArrayList<T> residents = new ArrayList<>();
        for (Object e : list) {
            if (clazz.isInstance(e)) residents.add(clazz.cast(e));
        }
        return residents;
    }
    double treasury = 0;
    int reputation = 100;
    String[] royalFamily = new String[]{"Его Величество Эрик", "Её Величество Аврелия", "Её Высочество Лорсулия", "Её Высочество Азалия", "Её Высочество Седани"};

    ArrayList<Object> residents = new ArrayList<>();
    ArrayList<Fair> fairies = new ArrayList<>();
    ArrayList<Ork> guards = new ArrayList<>();
    ArrayList<Cooker> cookers = new ArrayList<>();
    ArrayList<Object> jail = new ArrayList<>();
    String[] weather = new String[]{"солнечно.", "пасмурно.", "льёт как из ведра.", "ветер сильный", "снега намело", "жара невыносимая"};


    public void Welcome() {
        if (reputation <=20 && treasury <=10) {
            System.out.println("Народ бунтует, а казна пуста...\nВаше Величество, Вы не справились с управлением замком. Игра окончена.");
            System.exit(0);
        }
        String choice;
        Scanner sc = new Scanner(System.in); // подключаем сканер
        System.out.println("""
                
                Моё почтение, Ваше Величество. Какой указ вы отдатите?
                1. Созвать совет\s
                2. Поужинать с семьёй\s
                3. Исполнить желания народа\s
                4. Наказать за непослушание\s
                5. Пересчитать казну\s
                6. Заплатить феям за уборку\s
                7. Заплатить оркам за защиту замка""");

        choice = sc.nextLine();
        switch (choice) {
            case "1" -> Discussion();
            case "2" -> {
                try
                {
                    for (int i = 0; i < royalFamily.length; i++) {
                        if (i != 0) {
                            System.out.println("\n" + royalFamily[i] + " приглашена на ужин");
                        } else {
                            System.out.println("\n" + royalFamily[i] + " приглашён на ужин");
                        }
                        Thread.sleep(1000);
                    }
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                FamilyDinner();
            }
            case "3" -> Wishes();
            case "4" -> {
                System.out.println("\nКого наказать?\n1. Повара\n2. Фею\n3. Орка");
                choice = sc.nextLine();
                ArrayList<Cooker> cooks = getResidents(jail, Cooker.class);
                ArrayList<Ork> orks = getResidents(jail, Ork.class);
                ArrayList<Fair> fairy = getResidents(jail, Fair.class);
                switch (choice) {
                    case "1" -> {
                        if(!cooks.isEmpty()){
                            System.out.println("Выберите повара: ");
                            listInfo(cooks);
                            int i = sc.nextInt();
                            sc.nextLine();
                            if (i <= cooks.size() && i >= 1) {
                                Cooker choosed = cooks.get(i - 1);
                                int id = residents.indexOf((Object)choosed);
                                Punishment(id);
                            } else {
                                System.out.println("Выбор не понятен: ");
                                Welcome();
                            }
                        }
                        else{
                            System.out.println("\nВ Вашем замке нет ни одного повара");
                        }
                    }
                    case "2" -> {
                        if(!fairy.isEmpty()){
                            System.out.println("Выберите фею: ");
                            listInfo(fairy);
                            int i = sc.nextInt();
                            sc.nextLine();
                            if (i <= fairy.size() && i >= 1) {
                                Fair choosed = fairy.get(i - 1);
                                int id = residents.indexOf((Object)choosed);
                                Punishment(id);
                            } else {
                                System.out.println("Выбор не понятен: ");
                            }
                        }
                        else{
                            System.out.println("\nВ Вашем замке нет ни одной феи");
                        }
                    }
                    case "3" -> {
                        if(!orks.isEmpty()){
                            System.out.println("Выберите орка: ");
                            listInfo(orks);
                            int i = sc.nextInt();
                            sc.nextLine();
                            if (i <= orks.size() && i >= 1) {
                                Ork choosed = orks.get(i - 1);
                                int id = residents.indexOf((Object)choosed);
                                Punishment(id);
                            } else {
                                System.out.println("Выбор не понятен: ");
                            }
                        }
                        else{
                            System.out.println("\nВ Вашем замке нет ни одного орка");
                        }
                    }
                    default -> {
                        System.out.println("\nТакого персонала нет.");
                        Welcome();
                    }
                }
            }
            case "5" -> {
                if (treasury <= 0) {
                    System.out.println("""
                            Ваше Величество, Вы в долгах...
                            Созовите совет для пополнения казны""");
                } else {
                    System.out.println("\n" + "С вычетом неприкасаемых Ваше состояние равняется " + ReculcTreasury(treasury) + " злат");
                }
            }
            case "6" -> {
                if (treasury <= 0) {
                    System.out.println("""
                            Ваше Величество, Вы в долгах...
                            Созовите совет для пополнения казны""");
                } else {
                    if (fairies.isEmpty()) {
                        System.out.println("\nВаше Величество, в Вашем замке нет ни одной феи...\nСозовите совет, чтобы нанять персонал");
                    } else {
                        treasury = ReculcFairies(treasury);
                        System.out.println("\n" + "Феи благодарны Вам! Выше текущее состояние равно " + treasury + " злат");
                    }
                }
            }
            case "7" -> {
                if (treasury <= 0) {
                    System.out.println("""
                            Ваше Величество, Вы в долгах...
                            Созовите совет для пополнения казны""");
                } else {
                    if (guards.isEmpty()) {
                        System.out.println("\nВаше Величество, в Вашем замке нет ни одного орка...\nСозовите совет, чтобы нанять персонал");
                    } else {
                        treasury = ReculcOrcs(treasury);
                        System.out.println("\n" + "Орки рады! Выше текущее состояние равно " + treasury + " злат");
                    }
                }
            }
            default -> Welcome();
        }
        Welcome();
    }


    public void Discussion(){
        String choice;
        Scanner sc = new Scanner(System.in); // подключаем сканер
        System.out.println("\nВаше Величество, что обсудим?\n1. Нанять персонал \n2. Выпустить заключённого \n3. Собрать налоги \n4. Обсудить погоду");
        choice = sc.nextLine();
        switch (choice) {
            case "1" -> {
                System.out.println("\nКого нанять?\n1. Повара \n2. Фею-уборщицу \n3. Орка-стражника");
                choice = sc.nextLine();
                switch (choice) {
                    case "1" -> {
                        System.out.println("Выберите повара: ");
                        listInfo(allCooks);
                        int i = sc.nextInt();
                        sc.nextLine();
                        if (i <= allCooks.size() && i >= 1) {
                            cookers.add(allCooks.get(i - 1));
                            residents.add((Object)allCooks.get(i -1));
                            allCooks.remove(i - 1);
                            System.out.println("\nПовар нанят");
                        } else {
                            System.out.println("Выбор не понятен: ");
                            Discussion();
                        }
                    }
                    case "2" -> {
                        System.out.println("Выберите фею: ");
                        listInfo(allFairs);
                        int i = sc.nextInt();
                        sc.nextLine();
                        if (i <= allFairs.size() && i >= 1) {
                            fairies.add(allFairs.get(i - 1));
                            residents.add((Object)allFairs.get(i -1));
                            allFairs.remove(i - 1);
                            System.out.println("\nФея нанята.");
                        } else {
                            System.out.println("Выбор не понятен: ");
                            Discussion();
                        }
                    }
                    case "3" -> {
                        System.out.println("Выберите Орка: ");
                        listInfo(allOrks);
                        int i = sc.nextInt();
                        sc.nextLine();
                        if (i <= allOrks.size() && i >= 1) {
                            guards.add(allOrks.get(i - 1));
                            residents.add((Object)allOrks.get(i -1));
                            allOrks.remove(i - 1);
                            System.out.println("\nОрк нанята.");
                        } else {
                            System.out.println("Выбор не понятен: ");
                            Discussion();
                        }
                    }
                    default -> {
                        System.out.println("\nСовет Вас не понял.");
                        Discussion();
                    }
                }
            }
            case "2" -> {
                if(jail.isEmpty()){
                    System.out.println("\nТюрьма пуста, выпускать некого.");
                }
                else {
                    System.out.println("\nКого выпустить?\n1. Повара \n2. Фею-уборщицу \n3. Орка-стражника");
                    choice = sc.nextLine();
                    ArrayList<Cooker> cooks = getResidents(jail, Cooker.class);
                    ArrayList<Ork> orks = getResidents(jail, Ork.class);
                    ArrayList<Fair> fairy = getResidents(jail, Fair.class);
                    switch (choice) {
                        case "1" -> {
                            if (!cooks.isEmpty()){
                                System.out.println("Выберите повара: ");
                                listInfo(cooks);
                                int i = sc.nextInt();
                                sc.nextLine();
                                if (i <= cooks.size() && i >= 1) {
                                    Cooker free = cooks.get(i - 1);
                                    cookers.add(free);
                                    residents.add((Object)free);
                                    jail.remove((Object)free);
                                    System.out.println("\nПовар нанят");
                                } else {
                                    System.out.println("Выбор не понятен: ");
                                    Discussion();
                                }
                                System.out.println("\nПовар нанят обратно.");
                                reputation += 10;
                            }
                            else {
                                System.out.println("\nПоваров в тюрьме нет.");
                            }
                        }
                        case "2" -> {
                            if (!fairy.isEmpty()){
                                System.out.println("Выберите повара: ");
                                listInfo(fairy);
                                int i = sc.nextInt();
                                sc.nextLine();
                                if (i <= fairy.size() && i >= 1) {
                                    Fair free = fairy.get(i - 1);
                                    residents.add((Object)free);
                                    fairies.add(free);
                                    jail.remove((Object)free);
                                    System.out.println("\nПовар нанят");
                                } else {
                                    System.out.println("Выбор не понятен: ");
                                    Discussion();
                                }
                                System.out.println("\nФея нанята обратно.");
                                reputation += 10;
                            }
                            else {
                                System.out.println("\nФей в тюрьме нет.");
                            }
                        }
                        case "3" -> {
                            if (!orks.isEmpty()){
                                System.out.println("Выберите повара: ");
                                listInfo(orks);
                                int i = sc.nextInt();
                                sc.nextLine();
                                if (i <= orks.size() && i >= 1) {
                                    Ork free = orks.get(i - 1);
                                    residents.add((Object)free);
                                    guards.add(free);
                                    jail.remove((Object)free);
                                    System.out.println("\nПовар нанят");
                                } else {
                                    System.out.println("Выбор не понятен: ");
                                    Discussion();
                                }
                                System.out.println("\nОрк нанят обратно.");
                                reputation += 10;
                            }
                            else {
                                System.out.println("\nОрков в тюрьме нет.");
                            }
                        }
                        default -> {
                            System.out.println("\nСовет Вас не понял.");
                            Discussion();
                        }
                    }
                }
            }
            case "3" -> {
                if (reputation <= 20) {
                    System.out.println("\nНарод отказался платить...\nВыполните желания подданых, чтобы повысить репутацию.");
                } else {
                    treasury += 500;
                    System.out.println("\nВы собрали налоги с подданых. В Вашей казне прибавилось злат!");
                }
            }
            case "4" -> {
                Random random = new Random();
                int index = random.nextInt(weather.length);
                System.out.println("\n\"Да, господа, сегодня " + weather[index] + "\"");
            }
            default -> {
                System.out.println("\nСовет Вас не понял.");
                Discussion();
            }
        }
    }

    static double ReculcTreasury(double defaultTreasury){
        defaultTreasury = defaultTreasury-(defaultTreasury/100)*15;
        return defaultTreasury;
    }

    public double ReculcFairies(double defaultTreasury){
        reputation +=5;
        defaultTreasury = (defaultTreasury-(defaultTreasury/100)*15) - fairies.size()*40;
        return defaultTreasury;
    }
    public double ReculcOrcs(double defaultTreasury){
        reputation +=10;
        int orcs = guards.size();
        defaultTreasury = (defaultTreasury-(defaultTreasury/100)*15) - orcs*80 - ((double)orcs /100*10)*100;
        return defaultTreasury;
    }

    public void FamilyDinner(){
        ArrayList<Cooker> cooks  = getResidents(residents, Cooker.class);
        if (!cooks.isEmpty()){
            Random random = new Random();
            int index = random.nextInt(cooks.size());
            Cooker cook = cooks.get(index);
            System.out.println("Ваш повар сегодня: ");
            cook.getInfo();
            int cookId = residents.indexOf((Object)cook);
            String choice;
            Scanner sc = new Scanner(System.in); // подключаем сканер
            System.out.println("""

                    Ваше Величество, что сегодня на ужин?
                    1. Щупальца заморские\s
                    2. Свиные рёбра\s
                    3. Птица в кляре\s
                    4. Бедро телячье\s
                    5. Треска под соусом""");
            choice = sc.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println("\nМладшая Седани вертит нос. Поменять основное блюдо?\nДа\nНет");
                    choice = sc.nextLine();
                    if (Objects.equals(choice, "Да")) {
                        FamilyDinner();
                        return;
                    } else if (Objects.equals(choice, "Нет")) {
                        System.out.println("\nСедани обижена");
                        treasury -= 75;
                    } else {
                        System.out.println("\nДавайте попробуем заново.");
                        FamilyDinner();
                        return;
                    }
                }
                case "2" -> {
                    treasury -= 45;
                    System.out.println("\nВсе довольны.");
                }
                case "3" -> {
                    treasury -= 30;
                    System.out.println("\nВсе довольны.");
                }
                case "4" -> {
                    treasury -= 60;
                    System.out.println("\nВсе довольны.");
                }
                case "5" -> {
                    System.out.println("\nМладшая Седани вертит нос. Поменять основное блюдо?\nДа\nНет");
                    choice = sc.nextLine();
                    if (Objects.equals(choice, "Да")) {
                        FamilyDinner();
                        return;
                    } else if (Objects.equals(choice, "Нет")) {
                        System.out.println("\nСедани обижена");
                        treasury -= 65;
                    } else {
                        System.out.println("\nДавайте попробуем заново.");
                        FamilyDinner();
                        return;
                    }
                }
                default -> {
                    System.out.println("\nСожалеем, повар не знает такого блюда.\nНаказать?\nДа\nНет");
                    choice = sc.nextLine();
                    if (Objects.equals(choice, "Да")) {
                        Punishment(cookId);
                    } else if (Objects.equals(choice, "Нет")) {
                        reputation += 5;
                        System.out.println("\nПовар благодарен за Ваше милосердие. Начнём ужин снова.");
                    } else {
                        System.out.println("\nДавайте попробуем заново.");
                    }
                    FamilyDinner();
                    return;
                }
            }
            System.out.println("""
                    Ваше Величество, что сегодня на гарнир?
                    1. Рис бурый\s
                    2. Гречка сельская\s
                    3. Картошка толчёная\s
                    4. Паста с сырами\s""");
            choice = sc.nextLine();
            switch (choice) {
                case "1" -> {
                    treasury -= 15;
                    System.out.println("\nВсе довольны.");
                }
                case "2" -> {
                    System.out.println("\nМладшая Седани вертит нос. Поменять?\nДа\nНет");
                    choice = sc.nextLine();
                    if (Objects.equals(choice, "Да")) {
                        FamilyDinner();
                        return;
                    } else if (Objects.equals(choice, "Нет")) {
                        System.out.println("\nСедани обижена");
                        treasury -= 10;
                    } else {
                        System.out.println("\nДавайте попробуем заново.");
                        FamilyDinner();
                        return;
                    }
                }
                case "3" -> {
                    treasury -= 20;
                    System.out.println("\nВсе довольны.");
                }
                case "4" -> {
                    treasury -= 30;
                    System.out.println("\nВсе довольны.");
                }
                default -> {
                    System.out.println("\nСожалеем, повар не знает такого блюда.\nНаказать?\nДа\nНет");
                    choice = sc.nextLine();
                    if (Objects.equals(choice, "Да")) {
                        Punishment(cookId);

                    } else if (Objects.equals(choice, "Нет")) {
                        reputation += 5;
                        System.out.println("\nПовар благодарен за Ваше милосердие. Начнём ужин снова.");
                    } else {
                        System.out.println("\nДавайте попробуем заново.");
                    }
                    FamilyDinner();
                }
            }
            System.out.println("\nУжин закончен.");
        }
        else{
            System.out.println("\nВаше Величество, в Вашем замке нет ни одного повара...\nСозовите совет, чтобы нанять персонал.");
        }
    }

    public void Punishment(int residentId){
        String choice;
        Scanner sc = new Scanner(System.in); // подключаем сканер
        System.out.println("\nВыберете наказание.\n1. Вычет из жалования \n2. За решётку! \n3. Казнить...");
        choice = sc.nextLine();
        switch (choice) {
            case "1" -> {
                treasury += 50;
                reputation -= 15;
                System.out.println("\nВ Вашей казне прибавилось злат!");
            }
            case "2" -> {
                reputation -= 20;
                Object obj = residents.get(residentId);
                if (obj instanceof  Fair) {
                    fairies.remove((Fair)residents.get(residentId));
                } else if (obj instanceof Ork) {
                    guards.remove((Ork)residents.get(residentId));
                } else if (obj instanceof Cooker) {
                    cookers.remove((Cooker)residents.get(residentId));
                }
                jail.add(residents.get(residentId));
                residents.remove(residentId);
                System.out.println("\nРезидент в тюрьме. Созовите совет, чтобы помиловать.");
            }
            case "3" -> {
                reputation -= 40;
                Object obj = residents.get(residentId);
                if (obj instanceof  Fair) {
                    fairies.remove((Fair)residents.get(residentId));
                } else if (obj instanceof Ork) {
                    guards.remove((Ork)residents.get(residentId));
                } else if (obj instanceof Cooker) {
                    cookers.remove((Cooker)residents.get(residentId));
                }
                residents.remove(residentId);
                System.out.println("\nРезидент мёртв.");
            }
            default -> {
                System.out.println("Попробуем заново");
                Punishment(residentId);
            }
        }
    }

    public void Wishes(){
        String choice;
        Scanner sc = new Scanner(System.in); // подключаем сканер
        System.out.println("""
                Ваше Величество, какое желание исполнить?
                1. Раздать лишние златы
                2. Построить таверну
                3. Закупить заморских деликатесов""");
        choice = sc.nextLine();
        switch (choice) {
            case "1" -> {
                treasury = treasury - ((treasury * 10) / 100);
                System.out.println("\nВаша казна слегка опустела, но подданые рады!");
                reputation += (int)Math.round(((treasury * 10) / 100) / 10);
            }
            case "2" -> WishTavern();
            case "3" -> WishFood();
            default -> {
                System.out.println("\nНарод Вас не понял.");
                Welcome();
            }
        }
    }
    public void WishTavern(){
        if (treasury >= 300)
        {
            try
            {
                for (int i = 1; i <= 7; i++) {
                    switch (i){
                        case 1 -> System.out.println("\nВыбираем участок земли...");
                        case 2 -> System.out.println("\nДоговарвиаемся с дровосеками...");
                        case 3 -> System.out.println("\nЖдём поступления дерева...");
                        case 4 -> System.out.println("\nСтроим...");
                        case 5 -> System.out.println("\nРазливаем вина по бочкам...");
                        case 6 -> System.out.println("\nПриглашаем официанток...");
                        case 7 -> System.out.println("\nОткрываем двери...");
                    }
                    System.out.println("\nЭтап " + i +"/7");
                    Thread.sleep(1500);
                    if (i == 7){
                        System.out.println("\nГотово!");
                    }
                }
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            treasury -= 300;
            reputation += 25;
            System.out.println("\nВаши подданые знатно набухались!");
        }
        else {
            System.out.println("""
                            Ваше Величество, в казне недостаточно злат...
                            Созовите совет для пополнения казны""");
        }
    }
    public void WishFood(){
        if (treasury >= 170)
        {
            try
            {
                for (int i = 1; i <= 5; i++) {
                    switch (i){
                        case 1 -> System.out.println("\nВыбираем продукты...");
                        case 2 -> System.out.println("\nДоговарвиаемся с заморскими купцами...");
                        case 3 -> System.out.println("\nЖдём возвращения мореплавателей...");
                        case 4 -> System.out.println("\nДегустируем...");
                        case 5 -> System.out.println("\nВыкладываем на прилавки...");
                    }
                    System.out.println("\nЭтап " + i +"/5");
                    Thread.sleep(1500);
                    if (i == 5){
                        System.out.println("\nГотово!");
                    }
                }
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            treasury -= 170;
            reputation += 15;
            System.out.println("\nВаши подданые вкусно поели!");
        }
        else {
            System.out.println("""
                            Ваше Величество, в казне недостаточно злат...
                            Созовите совет для пополнения казны""");
        }
    }


}


