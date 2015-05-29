package Backend.DAO;

import Backend.ContextListener;
import Backend.DeletedPurchase;
import Backend.Purchase;
import Backend.rowMapper.DeletedPurchaseRowMapper;
import Backend.rowMapper.PurchaseRowMapper;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Isak on 2015-04-23.
 */
@Service
public class PurchaseDAO implements PurchaseDAOInterface {
    private DataSource dataSource;

    @Autowired
    public JdbcTemplate db;

    @Autowired
    PurchaseRowMapper purchaseRowMapper;

    @Autowired
    DeletedPurchaseRowMapper deletedPurchaseRowMapper;

    /**
     * Takes a Purchase object as input and adds it to the database.
     * Takes a String as input that represents the name of the User that adds the object to the Database for
     * logging purposes.
     * Takes two long as input that represent the Ids of a Manufacturer and a Distributor.
     * Returns the Purchase_ID of the newly inserted Purchase in the database.
     *
     * @param pur Purchase that is to be added in the database.
     * @param userName Name of the user that is adding the object to the database.1
     * @param distrId Id of the distributor in the database.
     * @param manuId Id of the manufacturer in the database.
     * @return Id of new created purchase.
     * @throws IllegalArgumentException If any input is null.
     */
    @Transactional
    public long addPurchase(final Purchase pur, final String userName, final long distrId, final long manuId) throws IllegalArgumentException {
        final KeyHolder holder = new GeneratedKeyHolder();
        if (pur == null){
          ContextListener.log.error("Purchase cannot be null.", new IllegalArgumentException());
        } else if(userName == null){
            ContextListener.log.error("userName cannot be null.", new IllegalArgumentException());
            throw new IllegalArgumentException(userName);
        } else if (distrId == 0){
            ContextListener.log.error("Distributor id must be higher than 0.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        } else if (manuId == 0){
            ContextListener.log.error("Manufacturer id must be higher than 0.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }

        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO PURCHASE(MANUFACTURER_ID, PRODUCT_NAME, LICENSE_TYPE, DISTRIBUTOR_ID, FREE_TEXT) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, manuId);
                ps.setString(2, pur.getProductName());
                ps.setString(3, pur.getType());
                ps.setLong(4, distrId);
                ps.setString(5, pur.getFreeText());
                return ps;
            }
        }, holder);

        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO CREATOR(CREATED_BY, CREATED_DATE, C_PURCHASE_ID) VALUES(?, ?, ?)");
                ps.setString(1, userName);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setLong(3, holder.getKey().longValue());
                return ps;
            }
        });
        return holder.getKey().longValue();
    }

    /**
     * Takes a purchase object that is to be deleted as input and adds it to the deleted_purchase table in the database.
     * @param pur The purchase to be deleted.
     * @param userName The name of the user deleting.
     * @throws IllegalArgumentException If any input is null.
     */
    @Override
    public void deletePurchase(final Purchase pur, final String userName) throws IllegalArgumentException{
        if (pur == null){
            ContextListener.log.error("Purchase cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        } else if (userName == null){
            ContextListener.log.error("userName cannot be null.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }

       db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO DELETED_PURCHASE(DELETED_BY, DELETED_DATE, D_PURCHASE_ID) VALUES(?, ?, ?)");
                ps.setString(1, userName);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setLong(3, pur.getPurchaseId());
                return ps;
            }
        });
    }

    /**
     * Returns all the deleted_purchases in the database.
     * @return List of all the deleted_purchase in the database.
     */
    public List<DeletedPurchase> searchDeletedPurchases(){
        return db.query(SQL_SEARCH_ALL_DELETED, deletedPurchaseRowMapper);
    }

    /**
     * Takes a long as input representing an id for a purchase in the database.
     * Returns the purchase corresponding with the id.
     *
     * @param id of the Purchase that is searched for in the database.
     * @return Purchase with the purchase_id that matches the input.
     * @throws IllegalArgumentException when id == 0.
     * @throws DataAccessException
     * @throws NullPointerException
     */
    public Purchase searchPurchaseById(long id) throws IllegalArgumentException, DataAccessException, NullPointerException{
        if(id == 0) {
            ContextListener.log.error("Id must be separate from 0, Id is : " + id, new IllegalArgumentException());
            throw new IllegalArgumentException("Id must be separate from 0, Id is : " + id);
        }
        try {
            return (Purchase) db.query(SQL_SEARCH_BY_ID,purchaseRowMapper,id).get(0);
        } catch (DataAccessException e){
            ContextListener.log.error("DataAccessException, probably database connection error.", e);
            throw e;
        } catch (NullPointerException e){
            ContextListener.log.error("searchPurchaseById returned empty list with id : " + id, e);
            throw e;
        }
    }

    /**
     * Takes a String as an input representing the product_name for the purchase in the database.
     * Returns a list of purchases with the matching product_name and other purchases with similar product_names.
     *
     * @param product_name The product_name search for
     * @return A list of purchases with the product_name that matches the input.
     * @throws IllegalArgumentException if the String input is null.
     */
    public List<Purchase> searchPurchaseByName(String product_name) throws IllegalArgumentException{
        if(product_name == null) {
            ContextListener.log.error("Purchase name cannot be null.", new IllegalArgumentException(product_name));
            throw new IllegalArgumentException(product_name);
        }

        return db.query(SQL_SEARCH_BY_PRODUCT_NAME, purchaseRowMapper, product_name + "%");
    }

    /**
     * Takes a String as an input that represents the distributor_name of a purchase in the database.
     * Returns a list of all the purchases with the matching distributor_name and other purchases with similar distributor_name.
     *
     * @param name The Distributor_Name of the purchase searched for.
     * @return A list of purchases with the Distributor_name that matches the input.
     * and other purchases with similar Distributor_name.
     * @throws IllegalArgumentException if param name is null
     */
    public List<Purchase> searchPurchaseByDistributorName(String name) throws IllegalArgumentException{
        if(name == null) {
            ContextListener.log.error("Distributor name cannot be null.", new IllegalArgumentException(name));
            throw new IllegalArgumentException(name);
        }

        return db.query(SQL_SEARCH_BY_DISTRIBUTOR, purchaseRowMapper, name + "%");
    }

    /**
     * Takes a String as an input that represents the Manufacturer_name of a purchase in the database.
     * Returns a list of all the purchases with the matching Manufacturer_name and other purchases with similar Manufacturer_name.
     *
     * @param name The manufacturer_name of the purchase searched for.
     * @return A list of purchases with the Manufacturer_name that matches the input.
     * @throws IllegalArgumentException If the input is null.
     */
    public List<Purchase> searchPurchaseByManufacturerName(String name) throws IllegalArgumentException{
        if(name == null){
            ContextListener.log.error("Manufacturer name cannot be null.", new IllegalArgumentException(name));
            throw new IllegalArgumentException(name);
        }

        return db.query(SQL_SEARCH_BY_MANUFACTURER, purchaseRowMapper, name + "%");
    }

    /**
     * Takes a String as an input that represents the Type of a purchase in the database.
     * Returns a list of all the purchases with matching Type of a purchase and other purchases with a similar Type.
     *
     * @param type The type of the purchase searched for.
     * @return List of purchases with the Type that matches the input.
     * @throws IllegalArgumentException If input is null.
     */
    public List<Purchase> searchPurchaseByType(String type) throws IllegalArgumentException{
        if(type == null){
            ContextListener.log.error("Purchase type cannot be null.", new IllegalArgumentException(type));
            throw new IllegalArgumentException(type);
        }

        return db.query(SQL_SEARCH_BY_TYPE, purchaseRowMapper, type+"%");
    }

    /**
     * Returns a list of all the purchases in the database.
     *
     * @return List of all purchases in the database.
     */
    public List<Purchase> searchAllPurchases(){
        return db.query(SQL_SEARCH_ALL_PRODUCT, purchaseRowMapper);
    }

    /**
     * Takes a purchase object that is edited, a String that represents the name of the user editing,
     * the Id of the manufacturer in the purchase object and id of the distributor.
     * Searches for the id of the purchase object inputed and compares the purchase from the database to the edited purchase.
     *
     * @param pur Purchase Object that is edited
     * @param userName Name of the user that has edited the purchase
     * @param manufacturerId Id of manufacturer.
     * @param distributorId Id of distributor.
     * @throws IllegalArgumentException If any input is null.
     * @throws DataAccessException
     * @throws NullPointerException
     */
    @Override
    @Transactional
    public void editPurchase(final Purchase pur, final String userName, final long manufacturerId, final long distributorId) throws IllegalArgumentException, DataAccessException, NullPointerException{
        if (pur == null){
            ContextListener.log.error("Purchase cannot be null.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        } else if (userName == null){
            ContextListener.log.error("userName cannot be null.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        } else if (manufacturerId == 0){
            ContextListener.log.error("Manufacturer id must be higher than 0.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        } else if (distributorId == 0){
            ContextListener.log.error("Distributor id must be higher than 0.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }

        final Purchase oldPur = searchPurchaseById(pur.getPurchaseId());

        StringBuilder sb = new StringBuilder();

        if (!oldPur.getDistributorName().equals(pur.getDistributorName())){
            sb.append("Distributor Changed: \"" + oldPur.getDistributorName() + "\" to \"" + pur.getDistributorName() + "\" | ");
        }
        if (!oldPur.getManufacturerName().equals(pur.getManufacturerName())){
            sb.append("Manufacturer Changed: \"" + oldPur.getManufacturerName() + "\" to \"" + pur.getManufacturerName() + "\" | ");
        }
        if (!oldPur.getProductName().equals(pur.getProductName())){
            sb.append("Product Name Changed: \"" + oldPur.getProductName() + "\" to \"" + pur.getProductName() + "\" | ");
        }
        if (!oldPur.getType().equals(pur.getType())){
            sb.append("Type Changed: \"" + oldPur.getType() + "\" to \"" + pur.getType() + "\" |");
        }
        if (!oldPur.getFreeText().equals(pur.getFreeText())){
            sb.append("Free Text Changed: \"" + oldPur.getFreeText() + "\" to \"" + pur.getFreeText() + "\" | ");
        }
        final String s = sb.toString();
        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_MODIFY_PURCHASE_TABLE);
                ps.setString(1, userName);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setLong(3, pur.getPurchaseId());
                ps.setString(4, s);
                return ps;
            }
        });


        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("UPDATE PURCHASE SET PRODUCT_NAME = ?" +
                        ", LICENSE_TYPE = ? ,MANUFACTURER_ID = ?,DISTRIBUTOR_ID = ?, FREE_TEXT = ? " +
                        " WHERE PURCHASE_ID = ?");
                ps.setString(1, pur.getProductName());
                ps.setString(2, pur.getType());
                ps.setLong(3, manufacturerId);
                ps.setLong(4, distributorId);
                ps.setString(5, pur.getFreeText());
                ps.setLong(6, oldPur.getPurchaseId());
                return ps;
            }
        });
    }

    /**
     * Takes an Long as an input that represents the ID of a Deleted_purchase in the database.
     * Returns the Deleted_purchase that matches the ID.
     *
     * @param id The id of a deleted_purchase searched for.
     * @return The Deleted_purchase with the matching Id.
     * @throws IllegalArgumentException If the Id is 0.
     * @throws DataAccessException If there is a database connection error.
     * @throws NullPointerException If there are no deleted_purchase with the input id.
     */
    public DeletedPurchase searchDeletedPurchaseById(long id) throws IllegalArgumentException, DataAccessException, NullPointerException{
        if(id == 0) {
            ContextListener.log.error("Id must be separate from 0, Id is : " + id, new IllegalArgumentException());
            throw new IllegalArgumentException("Id must be separate from 0, Id is : " + id);
        }
        try {
            return (DeletedPurchase) db.query(SQL_SEARCH_DELETED_BY_ID,deletedPurchaseRowMapper,id).get(0);
        } catch (DataAccessException e){
            ContextListener.log.error("DataAccessException, probably database connection error.", e);
            throw e;
        } catch (NullPointerException e){
            ContextListener.log.error("searchPurchaseById returned empty list with id : " + id, e);
            throw e;
        }
    }

    /**
     * Takes a String as an input that represents the product_name of a Deleted_purchase.
     * Returns a List of Deleted_purchases with matching product_name.
     *
     * @param name The product_name of a Deleted_purchase searched for.
     * @return A list of deleted_purchases with matching product_name
     * @throws IllegalArgumentException if the input is null
     */
    public List<DeletedPurchase> searchDeletedPurchaseByName(String name) throws IllegalArgumentException{
        if(name == null) {
            ContextListener.log.error("Name must not be null: ", new IllegalArgumentException());
            throw new IllegalArgumentException("Name must not be null! ");
        }
        return db.query(SQL_SEARCH_DELETED_BY_NAME,deletedPurchaseRowMapper, name+"%");
    }

    /**
     * Takes a long as an input that represents the Purchase_id of an upgraded_purchase.
     * Returns the id of purchase that was upgraded.
     * @param newPurchaseId
     * @return Id of purchase that was upgraded.
     */
    public long getUpgradedFrom(long newPurchaseId){
        long id;
        try {
            id = db.queryForLong("SELECT ORIGINAL_PURCHASE_ID FROM UPGRADED_PURCHASE WHERE NEW_PURCHASE_ID = " + newPurchaseId + ";");
        } catch (IncorrectResultSizeDataAccessException e) {
            return 0;
        }
        return id;

    }

    /**
     * Takes a DataSource object as an input. Creates a JdbcTemplate Object with connection to a database.
     * Used by Spring Autowire.
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    public static final String SQL_SEARCH_BY_PRODUCT_NAME = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "LEFT OUTER JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "WHERE P.PRODUCT_NAME LIKE ? " +
            "AND DP.D_PURCHASE_ID IS NULL ";

    public static final String SQL_SEARCH_ALL_PRODUCT = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D,CREATOR CR " +
            "WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "AND CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "AND P.PURCHASE_ID NOT IN (SELECT DP.D_PURCHASE_ID from DELETED_PURCHASE DP)";

    public static final String SQL_SEARCH_BY_ID = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "LEFT OUTER JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "WHERE P.PURCHASE_ID = ? " +
            "AND DP.D_PURCHASE_ID IS NULL ";

    public static final String SQL_SEARCH_BY_DISTRIBUTOR = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "LEFT OUTER JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "WHERE D.DISTRIBUTOR_NAME LIKE ? " +
            "AND DP.D_PURCHASE_ID IS NULL " ;

    public static final String SQL_SEARCH_BY_MANUFACTURER = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "LEFT OUTER JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "WHERE M.MANUFACTURER_NAME LIKE ? " +
            "AND DP.D_PURCHASE_ID IS NULL " ;

    public static final String SQL_SEARCH_BY_TYPE = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "LEFT OUTER JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "WHERE P.LICENSE_TYPE LIKE ? " +
            "AND DP.D_PURCHASE_ID IS NULL " ;

    public static final String SQL_SEARCH_ALL_DELETED = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE, UP.ORIGINAL_PURCHASE_ID" +
            ", DP.DELETED_PURCHASE_ID, DP.DELETED_BY, DP.DELETED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "LEFT OUTER JOIN UPGRADED_PURCHASE UP ON UP.NEW_PURCHASE_ID = P.PURCHASE_ID";


    public static final String SQL_SEARCH_DELETED_BY_ID = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE, UP.ORIGINAL_PURCHASE_ID" +
            ", DP.DELETED_PURCHASE_ID, DP.DELETED_BY, DP.DELETED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "LEFT OUTER JOIN UPGRADED_PURCHASE UP ON UP.NEW_PURCHASE_ID = P.PURCHASE_ID "+
            "WHERE P.PURCHASE_ID = ?";

    public static final String SQL_SEARCH_DELETED_BY_NAME = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE, UP.ORIGINAL_PURCHASE_ID" +
            ", DP.DELETED_PURCHASE_ID, DP.DELETED_BY, DP.DELETED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "LEFT OUTER JOIN UPGRADED_PURCHASE UP ON UP.NEW_PURCHASE_ID = P.PURCHASE_ID "+
            "WHERE P.PRODUCT_NAME LIKE ?";

    public static final String SQL_INSERT_INTO_MODIFY_PURCHASE_TABLE = "INSERT INTO MODIFY_PURCHASE(MODIFIED_BY, MODIFY_DATE, M_PURCHASE_ID, FREE_TEXT) VALUES(?, ?, ?, ?)";

}
