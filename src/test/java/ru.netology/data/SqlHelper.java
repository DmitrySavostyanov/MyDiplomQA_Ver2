package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.cards.CreditCardEntity;
import ru.netology.cards.DebitCardEntity;
import ru.netology.cards.OrderEntity;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertNull;

//public class SqlHelper {

/*
    public static Connection getConn() throws SQLException{
        String url = System.getProperty("url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        }catch (SQLException Exception){
            Exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDataBase() {//entity- сущность (запись в БД)
        String deleteCreditRequest = "DELETE FROM credit_request_entity"; //Удалить из таблицы в БД (credit_request_entity ---- > (назв таблицы в БД)
        String deleteOrderEntity = "DELETE FROM order_entity";
        String deletePaymentEntity = "DELETE FROM payment_entity";
        QueryRunner runner = new QueryRunner(); //то что будет запускать комманды
        Connection conn = getConn();
        runner.update(conn, deleteCreditRequest);// runner - будет запускать команды (conn (соединение), команда -удал)
        runner.update(conn, deleteOrderEntity);
        runner.update(conn, deletePaymentEntity);

    }

    @SneakyThrows
    public static String getPaymentEntity() { //getPaymentStatus ///JDBC VS Hibernate
        Connection conn = getConn();
        Statement countStmt = conn.createStatement();
        String paymentStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";// сортировака по полю создания и огранченная (лимит) одной записью
        //строчка создает посл созданную запись в БД (OrderBy- сортировка по)//забираем только  поле статус!!!
        ResultSet resultSet = countStmt.executeQuery(paymentStatus);
//        System.out.println(resultSet.getString("status"));
//        System.out.println(resultSet.getString("transaction_id"));
//        System.out.println(resultSet.getString("created"));
//        System.out.println(resultSet.getString("id"));
        if (resultSet.next()) { //resultSet - это то что вернул запрос (результ набор) (метод next()- это перебор строк
            return resultSet.getString("status");
        }

        return null;
    }

    @SneakyThrows
    public static String getCreditEntity() {//
        Connection conn = getConn(); // тип данных коннекшн
        Statement countStmt = conn.createStatement(); //через даннную переменную будем выполнять команды
        String creditStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        // забираем последнюю созданную запись (забираем статус из посл созданной записи)///Данная строка это только написание команды,а не ее выполнние
        // которую мы дальше передаем для выполнения
        ResultSet resultSet = countStmt.executeQuery(creditStatus);// выполняем команду и сразу присваиваем ее результат в переменную для дальнейшей работы
        if (resultSet.next()) {
            return resultSet.getString("status");
        }

        return null;
    }
}
//Сигнатура метода? (изучить)
*/

    public class SqlHelper {



        private static String url = System.getProperty("url");

        private static String username = System.getProperty("username");

        private static String password = System.getProperty("password");

        @SneakyThrows
        public static DebitCardEntity getEntryFromPaymentEntity() {
            QueryRunner runner = new QueryRunner();
                Connection conn = DriverManager.getConnection(url,username,password);
                return runner.query(conn, "SELECT * FROM payment_entity ORDER BY created DESC", new BeanHandler<>(DebitCardEntity.class));
            }


        @SneakyThrows
        public static CreditCardEntity getEntryFromCreditRequestEntity() {
            QueryRunner runner = new QueryRunner();
            Connection conn = DriverManager.getConnection(url,username, password);
                return runner.query(conn, "SELECT * FROM credit_request_entity ORDER BY created DESC", new BeanHandler<>(CreditCardEntity.class));
            }


        @SneakyThrows
        public static OrderEntity getEntryFromOrderEntity() {
            QueryRunner runner = new QueryRunner();
                Connection conn = DriverManager.getConnection(url, username, password);
                return runner.query(conn, "SELECT * FROM order_entity ORDER BY created DESC", new BeanHandler<>(OrderEntity.class));
            }


        @SneakyThrows
        public static void checkEmptyPaymentEntity() {
            QueryRunner runner = new QueryRunner();
                Connection conn = DriverManager.getConnection(url, username, password);
                DebitCardEntity paymentEntity = runner.query(conn, "SELECT * FROM payment_entity;", new BeanHandler<>(DebitCardEntity.class));
//                DebitCardEntity paymentEntity = runner.query(conn, "SELECT * FROM payment_entity ORDER BY created DESC LIMIT 1,4;", new BeanHandler<>(DebitCardEntity.class));
//                System.out.println(paymentEntity.getClass());
//                System.out.println("id from paymentEntity ---> " + paymentEntity.getId());
//                System.out.println("Amount from paymentEntity ---> " + paymentEntity.getAmount());
//                System.out.println("Created from paymentEntity ---> " + paymentEntity.getCreated());
//                System.out.println("Status from paymentEntity ---> " + paymentEntity.getStatus());
//                System.out.println("transaction from paymentEntity ---> " + paymentEntity.getTransaction_id());

                assertNull(paymentEntity);// ожидаем что таблица не заполнена/ см видео про ассерты!!!!!!
               // System.out.println(paymentEntity.getClass());
            }


        @SneakyThrows
        public static void checkEmptyCreditRequestEntity() {
            QueryRunner runner = new QueryRunner();
                Connection conn = DriverManager.getConnection(url, username, password);
                CreditCardEntity creditRequestEntity = runner.query(conn, "SELECT * FROM credit_request_entity;", new BeanHandler<>(CreditCardEntity.class));
                assertNull(creditRequestEntity);
            }


        @SneakyThrows
        public static void checkEmptyOrderEntity() {
            QueryRunner runner = new QueryRunner();
                Connection conn = DriverManager.getConnection(url, username, password);
                OrderEntity orderEntity = runner.query(conn, "SELECT * FROM order_entity;", new BeanHandler<>(OrderEntity.class));
                assertNull(orderEntity);
            }


        @SneakyThrows
        public static void cleanDataBase() {
            QueryRunner runner = new QueryRunner();
            Connection conn = DriverManager.getConnection(url, username, password);
                runner.update(conn, "DELETE FROM payment_entity;");
                runner.update(conn, "DELETE FROM credit_request_entity;");
                runner.update(conn, "DELETE FROM order_entity;");
            }
        }


