package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {

    public static Connection getConn() throws SQLException {
        String url = System.getProperty("url");
        // String url = "jdbc:postgresql://localhost:5432/app";
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException err) {
            //System.out.println("йцуке");
            err.printStackTrace(); // java.sql.SQLException: No suitable driver found for  -
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDataBase() {//entity- сущность (запись в БД)
        val deleteCreditRequest = "DELETE FROM credit_request_entity"; //Удалить из таблицы в БД (credit_request_entity ---- > (назв таблицы в БД)
        val deleteOrderEntity = "DELETE FROM order_entity";
        val deletePaymentEntity = "DELETE FROM payment_entity";
        val runner = new QueryRunner(); //то что будет запускать комманды
        try (val conn = getConn();
        ) {
            runner.update(conn, deleteCreditRequest);// runner - будет запускать команды (conn (соединение), команда -удал)
            runner.update(conn, deleteOrderEntity);
            runner.update(conn, deletePaymentEntity);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @SneakyThrows
    public static String getPaymentEntity() { //getPaymentStatus ///JDBC VS Hibernate
        try (val conn = getConn();
             val countStmt = conn.createStatement()) {
            val paymentStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";// сортировака по полю создания и огранченная (лимит) одной записью
            //строчка создает посл созданную запись в БД (OrderBy- сортировка по)//забираем только  поле статус!!!
            val resultSet = countStmt.executeQuery(paymentStatus);
            if (resultSet.next()) { //resultSet - это то что вернул запрос (результ набор) (метод next()- это перебор строк
                return resultSet.getString("status");
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static String getCreditEntity() {
        try (val conn = getConn();
             val countStmt = conn.createStatement()) {//через даннную переменную будем выполнять команды
            val creditStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
            // забираем последнюю созданную запись (забираем статус из посл созданной записи)///Данная строка это только написание команды,а не ее выполнние
            // которую мы дальше передаем для выполнения
            val resultSet = countStmt.executeQuery(creditStatus);// выполняем команду и сразу присваиваем ее результат в переменную для дальнейшей работы
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        } catch (SQLException err) {
            //System.out.println("Не получилось подключиться к базе данных");
            err.printStackTrace();
        }
        return null;
    }
}
