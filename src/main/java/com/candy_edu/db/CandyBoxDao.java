package com.candy_edu.db;

import com.candy_edu.candy.Candy;
import com.candy_edu.container.CandyBox;
import com.candy_edu.candy.CandyType;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandyBoxDao {
    private final DataSource ds;
    private final CandyDao candyDao;

    public CandyBoxDao(DataSource ds) {
        this.ds = ds;
        this.candyDao = new CandyDao(ds);
    }

    public void insert(CandyBox box) {
        String sqlBox = "INSERT INTO candy_boxes(name) VALUES (?) RETURNING id";

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlBox)) {

            ps.setString(1, box.getName());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int boxId = rs.getInt("id");
                    box.setId(boxId);

                    for (Candy c : box.getCandies()) {
                        if (c.getId() == null) {
                            candyDao.insert(c); // вставляємо candy у таблицю candies
                        }

                        try (PreparedStatement ps2 = conn.prepareStatement(
                                "INSERT INTO candy_box_candies(box_id, candy_id) VALUES (?, ?)")) {
                            ps2.setInt(1, boxId);
                            ps2.setInt(2, c.getId());
                            ps2.executeUpdate();
                        }
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Помилка при збереженні CandyBox", e);
        }
    }

    public List<CandyBox> findAll() {
        List<CandyBox> boxes = new ArrayList<>();
        String sql = "SELECT id, name FROM candy_boxes";

        try (Connection conn = ds.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int boxId = rs.getInt("id");
                String name = rs.getString("name");

                // Підтягуємо цукерки для цієї коробки
                List<Candy> candies = new ArrayList<>();
                try (PreparedStatement ps = conn.prepareStatement(
                        "SELECT c.* FROM candies c " +
                                "JOIN candy_box_candies cb ON c.id = cb.candy_id " +
                                "WHERE cb.box_id = ?")) {
                    ps.setInt(1, boxId);
                    try (ResultSet crs = ps.executeQuery()) {
                        while (crs.next()) {
                            candies.add(mapCandy(crs)); // mapCandy — метод як у CandyDao
                        }
                    }
                }

                boxes.add(new CandyBox(boxId, name, candies));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Помилка при завантаженні коробок", e);
        }

        return boxes;
    }

    private Candy mapCandy(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        int grams = rs.getInt("grams");
        double price = rs.getDouble("price");
        int sugar = rs.getInt("sugar");
        CandyType type = CandyType.valueOf(rs.getString("type"));

        return new Candy(name, grams, price, sugar, type);
    }
}
