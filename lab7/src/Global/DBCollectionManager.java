package Global;



import Global.Exceptions.DBException;
import Global.data.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.NavigableSet;
import java.util.TreeSet;

public class DBCollectionManager {
    // LAB_WORK_TABLE
    private final String SELECT_ALL_LAB_WORK = "SELECT * FROM " + DBInteraction.LAB_WORK;
    private final String SELECT_LAB_WORK_BY_ID = SELECT_ALL_LAB_WORK + " WHERE " +
            DBInteraction.LAB_WORK_TABLE_ID_COLUMN + " = ?";
    private final String INSERT_LAB_WORK = "INSERT INTO " +
            DBInteraction.LAB_WORK + " (" +
            DBInteraction.LAB_WORK_TABLE_NAME_COLUMN + ", " +
            DBInteraction.LAB_WORK_TABLE_CREATION_DATE_COLUMN + ", " +
            DBInteraction.LAB_WORK_TABLE_MINIMAL_POINT_COLUMN + ", " +
            DBInteraction.LAB_WORK_TABLE_DESCRIPTION_COLUMN + ", " +
            DBInteraction.LAB_WORK_TABLE_TUNED_IN_WORKS_COLUMN + ", " +
            DBInteraction.LAB_WORK_TABLE_DIFFICULTY_COLUMN + ", " +
            DBInteraction.LAB_WORK_TABLE_PERSON_ID_COLUMN + ", " +
            DBInteraction.LAB_WORK_TABLE_USER_ID_COLUMN + ", " +
            DBInteraction.LAB_WORK_TABLE_ID_COLUMN +") VALUES (?, ?, ?, ?, ?, " +
            "?, ?, ?, nextval('lab_work_id'))";
    private final String UPDATE_LAB_WORK = "INSERT INTO " +
    DBInteraction.LAB_WORK + " (" +
    DBInteraction.LAB_WORK_TABLE_NAME_COLUMN + ", " +
    DBInteraction.LAB_WORK_TABLE_CREATION_DATE_COLUMN + ", " +
    DBInteraction.LAB_WORK_TABLE_MINIMAL_POINT_COLUMN + ", " +
    DBInteraction.LAB_WORK_TABLE_DESCRIPTION_COLUMN + ", " +
    DBInteraction.LAB_WORK_TABLE_TUNED_IN_WORKS_COLUMN + ", " +
    DBInteraction.LAB_WORK_TABLE_DIFFICULTY_COLUMN + ", " +
    DBInteraction.LAB_WORK_TABLE_PERSON_ID_COLUMN + ", " +
    DBInteraction.LAB_WORK_TABLE_USER_ID_COLUMN + ", " +
    DBInteraction.LAB_WORK_TABLE_ID_COLUMN +") VALUES (?, ?, ?, ?, ?, " +
            "?, ?, ?, ?)";
    private final String DELETE_LAB_WORK_BY_ID = "DELETE FROM " + DBInteraction.LAB_WORK +
            " WHERE " + DBInteraction.LAB_WORK_TABLE_ID_COLUMN + " = ?";
    // COORDINATES_TABLE
    private final String SELECT_ALL_COORDINATES = "SELECT * FROM " + DBInteraction.COORDINATES_TABLE;
    private final String SELECT_COORDINATES_BY_LAB_WORK_ID = SELECT_ALL_COORDINATES +
            " WHERE " + DBInteraction.COORDINATES_TABLE_LAB_WORK_ID_COLUMN + " = ?";
    private final String INSERT_COORDINATES = "INSERT INTO " +
            DBInteraction.COORDINATES_TABLE + " (" +
            DBInteraction.COORDINATES_TABLE_LAB_WORK_ID_COLUMN + ", " +
            DBInteraction.COORDINATES_TABLE_X_COLUMN + ", " +
            DBInteraction.COORDINATES_TABLE_Y_COLUMN+ ") VALUES (?, ?, ?)";
    // PERSON_TABLE
    private final String SELECT_ALL_CHAPTER = "SELECT * FROM " + DBInteraction.PERSON_TABLE;
    private final String SELECT_CHAPTER_BY_ID = SELECT_ALL_CHAPTER +
            " WHERE " + DBInteraction.PERSON_TABLE_ID_COLUMN + " = ?";
    private final String INSERT_PERSON = "INSERT INTO " +
            DBInteraction.PERSON_TABLE + " (" +
            DBInteraction.PERSON_TABLE_ID_COLUMN+ ", " +
            DBInteraction.PERSON_TABLE_NAME_COLUMN + ", " +
            DBInteraction.PERSON_TABLE_BIRTHDAY_COLUMN + ", " +
            DBInteraction.PERSON_TABLE_EYE_COLOR_COLUMN + ", " +
            DBInteraction.PERSON_TABLE_HAIR_COLOR_COLUMN + ", " +
            DBInteraction.PERSON_TABLE_NATIONALITY_COLUMN + ") VALUES (nextval('person_id'), ?, ?, ?, ?, ?)";
    private final String DELETE_CHAPTER_BY_ID = "DELETE FROM " + DBInteraction.PERSON_TABLE +
            " WHERE " + DBInteraction.PERSON_TABLE_ID_COLUMN + " = ?";
    private final String DELETE_COORDINATES_BY_ID = "DELETE FROM " + DBInteraction.COORDINATES_TABLE +
            " WHERE " + DBInteraction.COORDINATES_TABLE_LAB_WORK_ID_COLUMN + " = ?";
    private DBInteraction dbInteraction;
    private DBUserManager dbUserManager;

    public DBCollectionManager(DBInteraction dbInteraction, DBUserManager dbUserManager) {
        this.dbInteraction = dbInteraction;
        this.dbUserManager = dbUserManager;
    }

    public DBInteraction getDbInteraction() {
        return dbInteraction;
    }

    private LabWork createLabWork(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(DBInteraction.LAB_WORK_TABLE_ID_COLUMN);
        String name = resultSet.getString(DBInteraction.LAB_WORK_TABLE_NAME_COLUMN);
        Coordinates coordinates =  getCoordinatesByLabWorkId(id);
        LocalDateTime creationDate = resultSet.getTimestamp(DBInteraction.LAB_WORK_TABLE_CREATION_DATE_COLUMN).toLocalDateTime();
        Long minimalPoint = resultSet.getLong(DBInteraction.LAB_WORK_TABLE_MINIMAL_POINT_COLUMN);
        String description = resultSet.getString(DBInteraction.LAB_WORK_TABLE_DESCRIPTION_COLUMN);
        Integer tunedInWorks = resultSet.getInt(DBInteraction.LAB_WORK_TABLE_TUNED_IN_WORKS_COLUMN);
        Difficulty difficulty = Difficulty.valueOf(resultSet.getString(DBInteraction.LAB_WORK_TABLE_DIFFICULTY_COLUMN));

        Person person = getPersonById(resultSet.getInt(DBInteraction.LAB_WORK_TABLE_PERSON_ID_COLUMN));
        User owner = dbUserManager.getUserById(resultSet.getLong(DBInteraction.LAB_WORK_TABLE_USER_ID_COLUMN));
        return new LabWork(
                id,
                name,
                coordinates,
                creationDate,
                minimalPoint,
                description,
                tunedInWorks,
                difficulty,
                person,
                owner
        );
    }

    public DBUserManager getDbUserManager() {
        return dbUserManager;
    }

    public NavigableSet<LabWork> getCollection() throws DBException{
        NavigableSet<LabWork> labWorkNavigableSet = new TreeSet<>();
        PreparedStatement preparedSelectAllStatement = null;
        try {
            preparedSelectAllStatement = dbInteraction.getPreparedStatement(SELECT_ALL_LAB_WORK, false);
            ResultSet resultSet = preparedSelectAllStatement.executeQuery();
            while (resultSet.next()) {
                labWorkNavigableSet.add(createLabWork(resultSet));
            }
        } catch (SQLException exception) {
            throw new DBException();
        } finally {
            dbInteraction.closePreparedStatement(preparedSelectAllStatement);
        }
        return labWorkNavigableSet;
    }


    private long getChapterIdByLabWorkId(long labWorkId) throws SQLException {
        long chapterId;
        PreparedStatement preparedSelectMarineByIdStatement = null;
        try {
            preparedSelectMarineByIdStatement = dbInteraction.getPreparedStatement(SELECT_LAB_WORK_BY_ID, false);
            preparedSelectMarineByIdStatement.setLong(1, labWorkId);
            ResultSet resultSet = preparedSelectMarineByIdStatement.executeQuery();
            System.out.println("Выполнен запрос SELECT_LAB_WORK_BY_ID.");
            if (resultSet.next()) {
                chapterId = resultSet.getLong(DBInteraction.LAB_WORK_TABLE_PERSON_ID_COLUMN);
            } else throw new SQLException();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_LAB_WORK_BY_ID!");
            throw new SQLException(exception);
        } finally {
            dbInteraction.closePreparedStatement(preparedSelectMarineByIdStatement);
        }
        return chapterId;
    }


    private Coordinates getCoordinatesByLabWorkId(long labworkId) throws SQLException {
        Coordinates coordinates;
        PreparedStatement preparedSelectCoordinatesByLabWorkIdStatement = null;
        try {
            preparedSelectCoordinatesByLabWorkIdStatement =
                    dbInteraction.getPreparedStatement(SELECT_COORDINATES_BY_LAB_WORK_ID, false);
            preparedSelectCoordinatesByLabWorkIdStatement.setLong(1, labworkId);
            ResultSet resultSet =  preparedSelectCoordinatesByLabWorkIdStatement.executeQuery();
            System.out.println("Выполнен запрос SELECT_COORDINATES_BY_LAB_WORK_ID.");
            if (resultSet.next()) {
                coordinates = new Coordinates(
                        resultSet.getLong(DBInteraction.COORDINATES_TABLE_X_COLUMN),
                        resultSet.getFloat(DBInteraction.COORDINATES_TABLE_Y_COLUMN)
                );
            } else throw new SQLException();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_COORDINATES_BY_LAB_WORK_ID!");
            throw new SQLException(exception);
        } finally {
            dbInteraction.closePreparedStatement(preparedSelectCoordinatesByLabWorkIdStatement);
        }
        return coordinates;
    }


    private Person getPersonById(Integer chapterId) throws SQLException {
        Person person;
        PreparedStatement preparedSelectPersonByIdStatement = null;
        EyeColor eyeColor = EyeColor.BLUE;
        try {
            preparedSelectPersonByIdStatement =
                    dbInteraction.getPreparedStatement(SELECT_CHAPTER_BY_ID, false);
            preparedSelectPersonByIdStatement.setInt(1, chapterId);
            ResultSet resultSet = preparedSelectPersonByIdStatement.executeQuery();
            System.out.println("Выполнен запрос SELECT_PERSON_BY_ID.");
            if (resultSet.next()) {
                if (resultSet.getString(DBInteraction.PERSON_TABLE_EYE_COLOR_COLUMN).trim().equals("YELLOW")){
                    eyeColor = EyeColor.YELLOW;
                }
                if (resultSet.getString(DBInteraction.PERSON_TABLE_EYE_COLOR_COLUMN).trim().equals("BLUE")){
                    eyeColor = EyeColor.BLUE;
                }
                if (resultSet.getString(DBInteraction.PERSON_TABLE_EYE_COLOR_COLUMN).trim().equals("ORANGE")){
                    eyeColor = EyeColor.ORANGE;
                }
                person = new Person(
                        resultSet.getString(DBInteraction.PERSON_TABLE_NAME_COLUMN),
                        resultSet.getTimestamp(DBInteraction.PERSON_TABLE_BIRTHDAY_COLUMN).toLocalDateTime(),
                        eyeColor,
                        HairColor.valueOf(resultSet.getString(DBInteraction.PERSON_TABLE_HAIR_COLOR_COLUMN)),
                        Country.valueOf(resultSet.getString(DBInteraction.PERSON_TABLE_NATIONALITY_COLUMN)));
            } else throw new SQLException();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_PERSON_BY_ID!");
            throw new SQLException(exception);
        } finally {
            dbInteraction.closePreparedStatement(preparedSelectPersonByIdStatement);
        }
        return person;
    }


    public LabWork insertLabWork(LabWork labWork, User user) throws DBException{
        LabWork labWork1;
        PreparedStatement preparedInsertLabWorkStatement = null;
        PreparedStatement preparedInsertCoordinatesStatement = null;
        PreparedStatement preparedInsertChapterStatement = null;
        try {

            dbInteraction.setCommitMode();
            dbInteraction.setSavepoint();

            LocalDateTime creationTime = LocalDateTime.now();

            preparedInsertLabWorkStatement = dbInteraction.getPreparedStatement(INSERT_LAB_WORK, true);
            preparedInsertCoordinatesStatement = dbInteraction.getPreparedStatement(INSERT_COORDINATES, true);
            preparedInsertChapterStatement = dbInteraction.getPreparedStatement(INSERT_PERSON, true);

            preparedInsertChapterStatement.setString(1, labWork.getAuthor().getName());
            preparedInsertChapterStatement.setTimestamp(2,Timestamp.valueOf(labWork.getAuthor().getBirthday()));
            preparedInsertChapterStatement.setString(3,labWork.getAuthor().getEyeColor().toString());
            preparedInsertChapterStatement.setString(4,labWork.getAuthor().getHairColor().toString());
            preparedInsertChapterStatement.setString(5,labWork.getAuthor().getNationality().toString());

            if (preparedInsertChapterStatement.executeUpdate() == 0) throw new SQLException();

            ResultSet generatedChapterKeys = preparedInsertChapterStatement.getGeneratedKeys();
            long chapterId;
            if (generatedChapterKeys.next()) {
                chapterId = generatedChapterKeys.getLong(1);
            } else throw new SQLException();
            System.out.println("Выполнен запрос INSERT_PERSON.");

            preparedInsertLabWorkStatement.setString(1, labWork.getName());
            preparedInsertLabWorkStatement.setTimestamp(2, Timestamp.valueOf(creationTime));
            preparedInsertLabWorkStatement.setLong(3, labWork.getMinimalPoint());
            preparedInsertLabWorkStatement.setString(4, labWork.getDescription());
            preparedInsertLabWorkStatement.setInt(5, labWork.getTunedInWorks());
            preparedInsertLabWorkStatement.setString(6, labWork.getDifficulty().toString());
            preparedInsertLabWorkStatement.setLong(7, chapterId);
            preparedInsertLabWorkStatement.setLong(8, dbUserManager.getUserIdByUsername(user));

            if (preparedInsertLabWorkStatement.executeUpdate() == 0) throw new SQLException();

            ResultSet generatedMarineKeys = preparedInsertLabWorkStatement.getGeneratedKeys();
            long labWorkId;
            if (generatedMarineKeys.next()) {
                labWorkId = generatedMarineKeys.getLong(9);
            } else throw new SQLException();

            System.out.println("Выполнен запрос INSERT_LAB_WORK.");

            preparedInsertCoordinatesStatement.setLong(1, labWorkId);
            preparedInsertCoordinatesStatement.setLong(2, labWork.getCoordinates().getX());
            preparedInsertCoordinatesStatement.setFloat(3, labWork.getCoordinates().getY());
            if (preparedInsertCoordinatesStatement.executeUpdate() == 0) throw new SQLException();
            System.out.println("Выполнен запрос INSERT_COORDINATES.");

            labWork1 = new LabWork(
                    labWorkId,
                    labWork.getName(),
                    labWork.getCoordinates(),
                    creationTime,
                    labWork.getMinimalPoint(),
                    labWork.getDescription(),
                    labWork.getTunedInWorks(),
                    labWork.getDifficulty(),
                    labWork.getAuthor(),
                    user
            );

            dbInteraction.commit();
            return labWork1;
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении группы запросов на добавление нового объекта!");
            dbInteraction.rollback();
            throw new DBException();
        } finally {
            dbInteraction.closePreparedStatement(preparedInsertLabWorkStatement);
            dbInteraction.closePreparedStatement(preparedInsertCoordinatesStatement);
            dbInteraction.closePreparedStatement(preparedInsertChapterStatement);
            dbInteraction.setNormalMode();
        }
    }

    public LabWork updateLabWork(LabWork labWork, User user, Long idLabWork) throws DBException{
        LabWork labWork1;
        PreparedStatement preparedInsertLabWorkStatement = null;
        PreparedStatement preparedInsertCoordinatesStatement = null;
        PreparedStatement preparedInsertChapterStatement = null;
        try {

            dbInteraction.setCommitMode();
            dbInteraction.setSavepoint();

            LocalDateTime creationTime = LocalDateTime.now();

            preparedInsertLabWorkStatement = dbInteraction.getPreparedStatement(UPDATE_LAB_WORK, true);
            preparedInsertCoordinatesStatement = dbInteraction.getPreparedStatement(INSERT_COORDINATES, true);
            preparedInsertChapterStatement = dbInteraction.getPreparedStatement(INSERT_PERSON, true);

            preparedInsertChapterStatement.setString(1, labWork.getAuthor().getName());
            preparedInsertChapterStatement.setTimestamp(2,Timestamp.valueOf(labWork.getAuthor().getBirthday()));
            preparedInsertChapterStatement.setString(3,labWork.getAuthor().getEyeColor().toString());
            preparedInsertChapterStatement.setString(4,labWork.getAuthor().getHairColor().toString());
            preparedInsertChapterStatement.setString(5,labWork.getAuthor().getNationality().toString());

            if (preparedInsertChapterStatement.executeUpdate() == 0) throw new SQLException();

            ResultSet generatedChapterKeys = preparedInsertChapterStatement.getGeneratedKeys();
            long chapterId;
            if (generatedChapterKeys.next()) {
                chapterId = generatedChapterKeys.getLong(1);
            } else throw new SQLException();
            System.out.println("Выполнен запрос INSERT_PERSON.");

            preparedInsertLabWorkStatement.setString(1, labWork.getName());
            preparedInsertLabWorkStatement.setTimestamp(2, Timestamp.valueOf(creationTime));
            preparedInsertLabWorkStatement.setLong(3, labWork.getMinimalPoint());
            preparedInsertLabWorkStatement.setString(4, labWork.getDescription());
            preparedInsertLabWorkStatement.setInt(5, labWork.getTunedInWorks());
            preparedInsertLabWorkStatement.setString(6, labWork.getDifficulty().toString());
            preparedInsertLabWorkStatement.setLong(7, chapterId);
            preparedInsertLabWorkStatement.setLong(8, dbUserManager.getUserIdByUsername(user));
            preparedInsertLabWorkStatement.setLong(9, idLabWork);

            if (preparedInsertLabWorkStatement.executeUpdate() == 0) throw new SQLException();
            System.out.println("Выполнен запрос INSERT_LAB_WORK.");

            preparedInsertCoordinatesStatement.setLong(1, idLabWork);
            preparedInsertCoordinatesStatement.setLong(2, labWork.getCoordinates().getX());
            preparedInsertCoordinatesStatement.setFloat(3, labWork.getCoordinates().getY());
            if (preparedInsertCoordinatesStatement.executeUpdate() == 0) throw new SQLException();
            System.out.println("Выполнен запрос INSERT_COORDINATES.");

            labWork1 = new LabWork(
                    idLabWork,
                    labWork.getName(),
                    labWork.getCoordinates(),
                    creationTime,
                    labWork.getMinimalPoint(),
                    labWork.getDescription(),
                    labWork.getTunedInWorks(),
                    labWork.getDifficulty(),
                    labWork.getAuthor(),
                    user
            );

            dbInteraction.commit();
            return labWork1;
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении группы запросов на добавление нового объекта!");
            dbInteraction.rollback();
            throw new DBException();
        } finally {
            dbInteraction.closePreparedStatement(preparedInsertLabWorkStatement);
            dbInteraction.closePreparedStatement(preparedInsertCoordinatesStatement);
            dbInteraction.closePreparedStatement(preparedInsertChapterStatement);
            dbInteraction.setNormalMode();
        }
    }

    public void deleteLabWorkById(long labWorkId) throws DBException {
        PreparedStatement preparedDeleteLabWorkByIdStatement = null;
        PreparedStatement preparedDeleteCoordinatesById = null;
        PreparedStatement preparedDeleteChapterByIdStatement = null;
        try {
            preparedDeleteCoordinatesById = dbInteraction.getPreparedStatement(DELETE_COORDINATES_BY_ID, false);
            preparedDeleteCoordinatesById.setLong(1,labWorkId);
            if (preparedDeleteCoordinatesById.executeUpdate() == 0) throw new SQLException();
            System.out.println("Выполнен запрос DELETE_COORDINATES_BY_LAB_WORK_ID.");

            Long chapterIdByLabWorkId = getChapterIdByLabWorkId(labWorkId);

            preparedDeleteLabWorkByIdStatement = dbInteraction.getPreparedStatement(DELETE_LAB_WORK_BY_ID, false);
            preparedDeleteLabWorkByIdStatement.setLong(1, labWorkId);
            if (preparedDeleteLabWorkByIdStatement.executeUpdate() == 0) throw new SQLException();
            System.out.println("Выполнен запрос DELETE_LAB_WORK_BY_ID.");

            preparedDeleteChapterByIdStatement = dbInteraction.getPreparedStatement(DELETE_CHAPTER_BY_ID, false);
            preparedDeleteChapterByIdStatement.setLong(1, chapterIdByLabWorkId);
            if (preparedDeleteChapterByIdStatement.executeUpdate() == 0) throw new SQLException();
            System.out.println("Выполнен запрос DELETE_PERSON_BY_LAB_WORK_ID.");
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса DELETE_LAB_WORK_BY_ID!");
            throw new DBException();
        } finally {
            dbInteraction.closePreparedStatement(preparedDeleteCoordinatesById);
            dbInteraction.closePreparedStatement(preparedDeleteLabWorkByIdStatement);
            dbInteraction.closePreparedStatement(preparedDeleteChapterByIdStatement);
        }
    }
}