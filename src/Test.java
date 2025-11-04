public class Test {
}
/*
java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    //allParcels больше не нужен, используем коробки
    //private static List<Parcel> allParcels = new ArrayList<>();

    // Создаем три специализированные коробки с максимальным весом 50 кг каждая
    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(50);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(50);
    // Третья коробка, если бы был третий тип посылок, но пока используем две.

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addParcel();
                        break;
                    case 2:
                        sendParcels();
                        break;
                    case 3:
                        calculateCosts();
                        break;
                    case 4:
                        showBoxContents();
                        break; // Новая опция
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите число.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать общую стоимость");
        System.out.println("4 — Показать содержимое коробки"); // Новая опция
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 — Стандартная");
        System.out.println("2 — Хрупкая");
        System.out.println("3 — Cкоропортящаяся посылка");
        String typeChoice = scanner.nextLine();

        System.out.println("Введите вес посылки (кг):");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите адрес доставки:");
        String address = scanner.nextLine();

        if (typeChoice.equals("1")) {
            StandardParcel sp = new StandardParcel(weight, address);
            standardBox.addParcel(sp); // Добавляем сразу в нужную коробку
        } else if (typeChoice.equals("2")) {
            FragileParcel fp = new FragileParcel(weight, address);
            fragileBox.addParcel(fp); // Добавляем сразу в нужную коробку
        } else {
            System.out.println("Неверный тип посылки.");
        }
    }

    private static void sendParcels() {
        System.out.println("--- Отправка стандартных посылок ---");
        for (StandardParcel parcel : standardBox.getAllParcels()) {
            parcel.packageItem();
            parcel.deliver();
        }
        standardBox = new ParcelBox<>(50); // Очищаем коробку, создавая новую

        System.out.println("\n--- Отправка хрупких посылок ---");
        for (FragileParcel parcel : fragileBox.getAllParcels()) {
            parcel.packageItem();
            parcel.deliver();
        }
        fragileBox = new ParcelBox<>(50); // Очищаем коробку, создавая новую
    }

    private static void calculateCosts() {
        double totalCost = 0;
        // Перебираем обе коробки
        for (StandardParcel parcel : standardBox.getAllParcels()) {
            totalCost += parcel.calculateCost();
        }
        for (FragileParcel parcel : fragileBox.getAllParcels()) {
            totalCost += parcel.calculateCost();
        }
        System.out.printf("Общая стоимость доставки всех посылок: %.2f руб.\n", totalCost);
    }

    private static void showBoxContents() {
        System.out.println("Выберите коробку для просмотра:");
        System.out.println("1 — Стандартная");
        System.out.println("2 — Хрупкая");
        String boxChoice = scanner.nextLine();

        if (boxChoice.equals("1")) {
            System.out.println("Содержимое стандартной коробки (вес " + standardBox.getTotalWeight() + " кг):");
            for (StandardParcel parcel : standardBox.getAllParcels()) {
                System.out.println(" - Вес: " + parcel.weight + " кг, Адрес: " + parcel.deliveryAddress);
            }
        } else if (boxChoice.equals("2")) {
            System.out.println("Содержимое хрупкой коробки (вес " + fragileBox.getTotalWeight() + " кг):");
            for (FragileParcel parcel : fragileBox.getAllParcels()) {
                System.out.println(" - Вес: " + parcel.weight + " кг, Адрес: " + parcel.deliveryAddress + ", Статус: " + parcel.getStatus());
            }
        } else {
            System.out.println("Неверный выбор коробки.");
        }
    }
}


// --- Определение вспомогательных классов и интерфейсов ---
// Эти классы лучше держать в отдельных файлах (Parcel.java, StandardParcel.java и т.д.)
// для чистоты проекта.

abstract class Parcel {
    protected int weight;
    protected String deliveryAddress;

    public Parcel(int weight, String deliveryAddress) {
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
    }

    abstract double calculateCost();
    abstract void packageItem();
    abstract void deliver();
}

class StandardParcel extends Parcel {
    public StandardParcel(int weight, String deliveryAddress) {
        super(weight, deliveryAddress);
    }
    @Override
    double calculateCost() { return weight * 100; }
    @Override
    void packageItem() { System.out.println("Упаковка стандартной посылки."); }
    @Override
    void deliver() { System.out.println("Доставка стандартной посылки по адресу: " + deliveryAddress); }
}

class FragileParcel extends Parcel {
    private String status = "На складе";
    public FragileParcel(int weight, String deliveryAddress) {
        super(weight, deliveryAddress);
    }
    public String getStatus() { return status; } // Добавлен геттер для вывода статуса
    @Override
    double calculateCost() { return weight * 150 + 500; }
    @Override
    void packageItem() { System.out.println("Особая упаковка хрупкой посылки."); }
    @Override
    void deliver() { System.out.println("Экспресс-доставка хрупкой посылки по адресу: " + deliveryAddress); }
}

// --- Класс ParcelBox ---

class ParcelBox<T extends Parcel> {
    private List<T> parcels;
    private int maxWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        parcels = new ArrayList<>();
    }

    // Метод добавления с проверкой веса
    void addParcel(T parcel) {
        if (getTotalWeight() + parcel.weight <= maxWeight) {
            parcels.add(parcel);
            System.out.println("Посылка добавлена в коробку.");
        } else {
            System.out.println("ПРЕДУПРЕЖДЕНИЕ: Превышен максимальный вес коробки! Посылка не добавлена.");
        }
    }

    // Метод получения списка посылок
    List<T> getAllParcels() {
        return parcels;
    }

    // Вспомогательный метод для расчета текущего веса
    int getTotalWeight() {
        int totalWeight = 0;
        for (Parcel parcel : parcels) {
            totalWeight += parcel.weight;
        }
        return totalWeight;
    }
}
Используйте код с осторожностью.

* */