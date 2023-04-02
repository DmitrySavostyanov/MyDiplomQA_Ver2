package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.cards.CreditCardEntity;
import ru.netology.cards.DebitCardEntity;
import ru.netology.cards.OrderEntity;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.assertNull;


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
                assertNull(paymentEntity);
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


