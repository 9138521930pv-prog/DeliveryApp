import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(50);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(50);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(50);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            String cmd = scanner.nextLine();
            switch(cmd) {
                case "1" -> addParcel();
                case "2" -> sendParcels();
                case "3" -> calculateCosts();
                case "0" -> {running = false;
                    break;}
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Показать содержимое коробки"); // Новая опция
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        int timeToLive = 0;
        System.out.println("Выберите тип посылки:");
        System.out.println("1 — Стандартная");
        System.out.println("2 — Хрупкая");
        System.out.println("3 — Cкоропортящаяся посылка");
        String typeChoice = scanner.nextLine();
        System.out.println("Краткое описание");
        String destruction = scanner.nextLine();
        System.out.println("Введите вес посылки (кг):");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите адрес доставки:");
        String address = scanner.nextLine();
        System.out.println("День месяца отправки посылки");
        int sendDay = Integer.parseInt(scanner.nextLine());
        if (typeChoice.equals("3")) {
            System.out.println("Срок в днях, за который посылка не испортится");
            timeToLive = Integer.parseInt(scanner.nextLine());

        }

        switch(typeChoice) {
            case "1" -> {
                StandardParcel sp = new StandardParcel(destruction,weight, address,sendDay);
                standardBox.addParcel(sp); // Добавляем сразу в нужную коробку
            }
            case "2" -> {
                FragileParcel fp = new FragileParcel(destruction,weight, address,sendDay);
                fragileBox.addParcel(fp); // Добавляем сразу в нужную коробку
            }
            case "3" -> {
                PerishableParcel pp = new PerishableParcel(destruction,weight, address,sendDay,timeToLive);
                perishableBox.addParcel(pp); // Добавляем сразу в нужную коробку
            }

            default -> System.out.println("Неверный тип посылки.");
        }

    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
    }

}


/*
List<FragileParcel> trackableParcels = new ArrayList<>(); // Список для отслеживания

// При добавлении новой посылки
trackableParcels.add(newFragileParcel);

// В меню консольного интерфейса
case "обновить местоположение":
    String newLocation = scanner.nextLine();
    for (FragileParcel parcel : trackableParcels) {
        parcel.reportStatus(newLocation);
    }
    break;

* */


/*
 class ParcelBox<T extends Parcel> {
 private List<T> parcels;
 private int maxWeight;

 public ParcelBox(int maxWeight) {
 this.maxWeight = maxWeight;
 parcels = new ArrayList<>();
 }

 void addParcel(T parcel) {
 if (getTotalWeight() + parcel.weight <= maxWeight) {
 parcels.add(parcel);
 } else {
 System.out.println("Превышен максимальный вес коробки!");
 }
 }

 List<T> getAllParcels() {
 return parcels;
 }

 // Метод для обновления местоположения всех посылок, поддерживающих трекинг
 void updateLocation(String newLocation) {
 for (Parcel parcel : parcels) {
 if (parcel instanceof Trackable) {
 ((Trackable) parcel).reportStatus(newLocation);
 }
 }
 }

 private int getTotalWeight() {
 int totalWeight = 0;
 for (Parcel parcel : parcels) {
 totalWeight += parcel.weight;
 }
 return totalWeight;
 }
 }

 * */