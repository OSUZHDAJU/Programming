package Global;

import Global.Exceptions.DBException;
import Global.data.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserManager {
    // USER_TABLE
    private final String SELECT_USER_BY_ID = "SELECT * FROM " + DBInteraction.USER_TABLE +
            " WHERE " + DBInteraction.USER_TABLE_ID_COLUMN + " = ?";
    private final String SELECT_USER_BY_USERNAME = "SELECT * FROM " + DBInteraction.USER_TABLE +
            " WHERE " + DBInteraction.USER_TABLE_USERNAME_COLUMN + " = ?";
    private final String SELECT_USER_BY_USERNAME_AND_PASSWORD = SELECT_USER_BY_USERNAME + " AND " +
            DBInteraction.USER_TABLE_PASSWORD_COLUMN + " = ?";
    private final String INSERT_USER = "INSERT INTO " +
            DBInteraction.USER_TABLE + " (" +
            DBInteraction.USER_TABLE_ID_COLUMN + ", "+
            DBInteraction.USER_TABLE_USERNAME_COLUMN + ", " +
            DBInteraction.USER_TABLE_PASSWORD_COLUMN + ") VALUES (nextval('user_id'),?, ?)";

    private DBInteraction dbInteraction;

    public DBUserManager(DBInteraction dbInteraction){
        this.dbInteraction = dbInteraction;
    }

    public User getUserById(long userId) throws SQLException {
        User user;
        PreparedStatement preparedSelectUserByIdStatement = null;
        try {
            preparedSelectUserByIdStatement =
                    dbInteraction.getPreparedStatement(SELECT_USER_BY_ID, false);
            preparedSelectUserByIdStatement.setLong(1, userId);
            ResultSet resultSet = preparedSelectUserByIdStatement.executeQuery();
            System.out.println("Выполнен запрос SELECT_USER_BY_ID.");
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString(DBInteraction.USER_TABLE_USERNAME_COLUMN),
                        resultSet.getString(DBInteraction.USER_TABLE_PASSWORD_COLUMN)
                );
            } else throw new SQLException();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_USER_BY_ID!");
            throw new SQLException(exception);
        } finally {
            dbInteraction.closePreparedStatement(preparedSelectUserByIdStatement);
        }
        return user;
    }


    public boolean checkUserByUsernameAndPassword(User user) throws DBException {
        PreparedStatement preparedSelectUserByUsernameAndPasswordStatement = null;
        try {
            preparedSelectUserByUsernameAndPasswordStatement =
                    dbInteraction.getPreparedStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD, false);
            preparedSelectUserByUsernameAndPasswordStatement.setString(1, user.getUsername());
            preparedSelectUserByUsernameAndPasswordStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedSelectUserByUsernameAndPasswordStatement.executeQuery();
            System.out.println("Выполнен запрос SELECT_USER_BY_USERNAME_AND_PASSWORD.");
            return resultSet.next();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_USER_BY_USERNAME_AND_PASSWORD!");
            throw new DBException();
        } finally {
            dbInteraction.closePreparedStatement(preparedSelectUserByUsernameAndPasswordStatement);
        }
    }


    public long getUserIdByUsername(User user) throws DBException {
        long userId;
        PreparedStatement preparedSelectUserByUsernameStatement = null;
        try {
            preparedSelectUserByUsernameStatement =
                    dbInteraction.getPreparedStatement(SELECT_USER_BY_USERNAME, false);
            preparedSelectUserByUsernameStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedSelectUserByUsernameStatement.executeQuery();
            System.out.println("Выполнен запрос SELECT_USER_BY_USERNAME.");
            if (resultSet.next()) {
                userId = resultSet.getLong(DBInteraction.USER_TABLE_ID_COLUMN);
            } else userId = -1;
            return userId;
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_USER_BY_USERNAME!");
            throw new DBException();
        } finally {
            dbInteraction.closePreparedStatement(preparedSelectUserByUsernameStatement);
        }
    }

    public boolean insertUser(User user) throws DBException {
        PreparedStatement preparedInsertUserStatement = null;
        try {
            if (getUserIdByUsername(user) != -1) return false;
            preparedInsertUserStatement =
                    dbInteraction.getPreparedStatement(INSERT_USER, false);
            preparedInsertUserStatement.setString(1, user.getUsername());
            preparedInsertUserStatement.setString(2, user.getPassword());
            if (preparedInsertUserStatement.executeUpdate() == 0) throw new SQLException();
            System.out.println("Выполнен запрос INSERT_USER.");
            return true;
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса INSERT_USER!");
            throw new DBException();
        } finally {
            dbInteraction.closePreparedStatement(preparedInsertUserStatement);
        }
    }
}