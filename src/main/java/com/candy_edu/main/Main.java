package com.candy_edu.main;

import com.candy_edu.db.CandyDao;
import com.candy_edu.db.Database;
import com.candy_edu.db.CandyBoxDao;

public class Main {
    public static void main(String[] args) throws Exception {
        Database.init(
                "jdbc:postgresql://localhost:5432/lab1demo", "myuser", "mypassword"
        );

        CandyDao candyDao = new CandyDao(Database.get());
        CandyBoxDao candyBoxDao = new CandyBoxDao(Database.get());

        ConsoleUI ui = new ConsoleUI(candyDao, candyBoxDao);
        ui.start();

        Database.close();
    }
}
