package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    private static Connection connect;
    private static Statement state;

    public DBManager() {

    }

    /*Execute les requêtes de type SELECT*/
    public static ResultSet executeQuery(String sqlQuery) throws SQLException {
        try {
            if (!(connect instanceof Connection)) {
                initConnexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/comptes", "root", "");
            }
            state = connect.createStatement();
            ResultSet rs = state.executeQuery(sqlQuery);
            return rs;
        } catch (SQLException e) {
            throw e;
        }
    }

    public static Connection getConnect() throws SQLException {
        if (!(connect instanceof Connection)) {
            initConnexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/comptes", "root", "");
        }
        return connect;
    }

    /*Execute les requêtes de type INSERT, DELETE, UPDATE*/
    public static int updateQuery(String sqlQuery) throws SQLException {
        try {
            if (connect == null) {
                initConnexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/comptes", "root", "");
            }

            DBManager.state = connect.createStatement();

            int result = state.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS);

            // Valide la requete
            connect.commit();

            return result;
        } catch (SQLException e) {
            throw e;
        }
    }

    /*Instantiation de la connexion à la base de données*/
    private static void initConnexion(String driver, String url, String login, String password) throws
            SQLException {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, login, password);
            connect.setAutoCommit(false);
            System.out.println("Connexion reussie");
        } catch (ClassNotFoundException e) {
            System.out.println("erreur chargement pilote JDBC \n");
            System.exit(0);
        } catch (SQLException e) {
            System.out.println("erreur connexion base de données \n" + e.getMessage());
            System.exit(0);
        }
    }
}
