package com.candy_edu.main;

import com.candy_edu.candy.Candy;
import com.candy_edu.candy.CandyType;
import com.candy_edu.candy_system.CandySystem;
import com.candy_edu.container.CandyBox;
import com.candy_edu.db.CandyDao;
import com.candy_edu.db.CandyBoxDao;

import java.util.*;

public class ConsoleUI {
    private final Scanner in = new Scanner(System.in).useLocale(Locale.US);

    /** Каталог цукерок (вихідний список для вибору) */
    private final List<Candy> catalog = new ArrayList<>();
    /** Вибрані користувачем цукерки */
    private final List<Candy> selected = new ArrayList<>();

    private final CandySystem system = new CandySystem();
    private final CandyDao dao;
    private final CandyBoxDao candyBoxDao;

    // Конструктор із DAO
    public ConsoleUI(CandyDao dao, CandyBoxDao candyBoxDao) {
        this.dao = dao;
        this.candyBoxDao = candyBoxDao;
        seedCatalog();
    }

    /** Запуск головного циклу меню */
    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = in.nextLine().trim();
            switch (choice) {
                case "1" -> showCatalogAndAdd();               // вибрати з каталогу
                case "2" -> addNewCandyToCatalog();            // створити власну
                case "3" -> system.getCandies(selected);       // показати вибрані
                case "4" -> system.getTotalWeightAndPrice(selected);
                case "5" -> sortingByTypeFlow();
                case "6" -> findBySugarFlow();
                case "7" -> saveToDatabase();
                case "8" -> loadFromDatabase();
                case "9" -> {
                    System.out.print("Назва коробки: ");
                    String name = in.nextLine().trim();
                    CandyBox box = new CandyBox(0, name, new ArrayList<>(selected));
                    candyBoxDao.insert(box);
                    System.out.println("Пакунок збережено у БД.");
                }
                case "10" -> {
                    List<CandyBox> boxes = candyBoxDao.findAll();
                    System.out.println("Усі пакунки:");
                    boxes.forEach(System.out::println);
                }
                case "0" -> {
                    running = false;
                    System.out.println("Вихід. Дякую за використання!");
                }
                default -> System.out.println("Невірний пункт. Спробуйте ще раз.");
            }
        }
    }

    /** Вивід меню */
    private void printMenu() {
        System.out.println("\n========== МЕНЮ ==========");
        System.out.println("1) Додати цукерку з каталогу");
        System.out.println("2) Створити власну цукерку");
        System.out.println("3) Показати вибрані цукерки");
        System.out.println("4) Загальна вага та ціна вибраних");
        System.out.println("5) Відсортувати вибрані за типом");
        System.out.println("6) Знайти вибрані за діапазоном цукру");
        System.out.println("7) Зберегти вибрані у БД");
        System.out.println("8) Завантажити всі з БД у вибрані");
        System.out.println("9) Зберегти вибрані цукерки як CandyBox у БД");
        System.out.println("10) Завантажити всі CandyBox з БД");
        System.out.println("0) Вийти");
        System.out.print("Ваш вибір: ");
    }

    /** Початковий каталог для прикладу */
    private void seedCatalog() {
        catalog.add(new Candy("Корівка", 50, 15.0, 35, CandyType.CARAMEL));
        catalog.add(new Candy("Мілка", 90, 55.0, 48, CandyType.CHOCOLATE));
        catalog.add(new Candy("Зефір", 30, 10.0, 30, CandyType.MARSHMALLOW));
        catalog.add(new Candy("Іриска", 80, 42.0, 45, CandyType.IRIS));
        catalog.add(new Candy("Желейки", 60, 50.0, 70, CandyType.JELLY));
    }

    /** Показати каталог і додати вибір */
    private void showCatalogAndAdd() {
        if (catalog.isEmpty()) {
            System.out.println("Каталог порожній. Додайте цукерку (п.2).");
            return;
        }
        System.out.println("\n===== КАТАЛОГ ЦУКЕРОК =====");
        for (int i = 0; i < catalog.size(); i++) {
            System.out.println((i + 1) + ") " + catalog.get(i));
        }
        int idx = readInt("Вкажіть номер цукерки: ") - 1;
        if (idx < 0 || idx >= catalog.size()) {
            System.out.println("Невірний номер.");
            return;
        }
        Candy chosen = catalog.get(idx);
        Candy copy = new Candy(chosen.getName(),
                chosen.getWeight().getGrams(),
                chosen.getPrice(),
                chosen.getSugar(),
                chosen.getType());
        selected.add(copy);
        System.out.println("Додано у вибрані: " + copy);
    }

    /** Додати власну цукерку */
    private void addNewCandyToCatalog() {
        System.out.print("Назва: ");
        String name = in.nextLine().trim();
        int grams = readInt("Вага (г): ");
        int sugar = readInt("Цукор (г): ");
        double price = readDouble("Ціна (UAH): ");
        CandyType type = readCandyType();

        Candy candy = new Candy(name, grams, price, sugar, type);
        catalog.add(candy);
        System.out.println("Додано в каталог: " + candy);
    }

    /** Сортування вибраних */
    private void sortingByTypeFlow() {
        if (selected.isEmpty()) {
            System.out.println("У вибраних немає цукерок.");
            return;
        }
        CandyType type = readCandyType();
        system.sortingByType(selected, type);
    }

    /** Пошук за діапазоном цукру */
    private void findBySugarFlow() {
        if (selected.isEmpty()) {
            System.out.println("У вибраних немає цукерок.");
            return;
        }
        int min = readInt("Мінімум цукру (г): ");
        int max = readInt("Максимум цукру (г): ");
        if (min > max) {
            System.out.println("Мінімум більше за максимум.");
            return;
        }
        system.findBySugarRange(selected, min, max);
    }

    /** Зберегти у БД */
    private void saveToDatabase() {
        if (selected.isEmpty()) {
            System.out.println("Немає що зберігати.");
            return;
        }
        for (Candy c : selected) dao.insert(c);
        System.out.println("Усі вибрані збережено у БД.");
    }

    /** Завантажити з БД */
    private void loadFromDatabase() {
        selected.clear();
        selected.addAll(dao.findAll());
        System.out.println("Завантажено з БД: " + selected.size() + " цукерок.");
    }

    // ==== Введення з консолі ====

    private CandyType readCandyType() {
        while (true) {
            System.out.println("Оберіть тип цукерки:");
            CandyType[] values = CandyType.values();
            for (int i = 0; i < values.length; i++) {
                System.out.println((i + 1) + ") " + values[i]);
            }
            System.out.print("Ваш вибір: ");
            String s = in.nextLine().trim();
            try {
                int idx = Integer.parseInt(s) - 1;
                if (idx >= 0 && idx < values.length) return values[idx];
            } catch (NumberFormatException ignored) {}
            System.out.println("Невірний вибір. Спробуйте ще раз.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Введіть ціле число.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Введіть число (можна з крапкою).");
            }
        }
    }

}
