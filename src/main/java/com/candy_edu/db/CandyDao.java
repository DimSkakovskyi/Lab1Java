package com.candy_edu.db;

import com.candy_edu.candy.Candy;
import com.candy_edu.candy.CandyType;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandyDao {
    private final DataSource ds;

    public CandyDao(DataSource ds) {
        this.ds = ds;
    }

    public void insert(Candy candy) {
        String sql = "INSERT INTO candies(name, grams, price, sugar, type) VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, candy.getName());
            ps.setInt(2, candy.getWeight().getGrams());
            ps.setDouble(3, candy.getPrice());
            ps.setInt(4, candy.getSugar());
            ps.setString(5, candy.getType().name());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    candy.setId(rs.getInt("id")); // <-- збереження id з БД
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при збереженні Candy", e);
        }
    }

    public List<Candy> findAll() {
        String sql = "SELECT id, name, grams, price, sugar, type FROM candies ORDER BY id";
        List<Candy> list = new ArrayList<>();
        try (Connection cn = ds.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при завантаженні всіх Candy", e);
        }
        return list;
    }

    public List<Candy> findBySugarRange(int min, int max) {
        String sql = "SELECT id, name, grams, price, sugar, type FROM candies WHERE sugar BETWEEN ? AND ? ORDER BY id";
        List<Candy> list = new ArrayList<>();
        try (Connection cn = ds.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, min);
            ps.setInt(2, max);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при пошуку Candy за діапазоном цукру", e);
        }
        return list;
    }

    public int totalWeight() {
        try (Connection cn = ds.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COALESCE(SUM(grams),0) FROM candies")) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при підрахунку ваги", e);
        }
    }

    public double totalPrice() {
        try (Connection cn = ds.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COALESCE(SUM(price),0) FROM candies")) {
            rs.next();
            return rs.getBigDecimal(1).doubleValue();
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при підрахунку ціни", e);
        }
    }

    private Candy map(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        int grams = rs.getInt("grams");
        double price = rs.getBigDecimal("price").doubleValue();
        int sugar = rs.getInt("sugar");
        CandyType type = CandyType.valueOf(rs.getString("type"));

        Candy c = new Candy(name, grams, price, sugar, type);
        c.setId(rs.getInt("id")); // <-- тут теж присвоюємо id
        return c;
    }
}
