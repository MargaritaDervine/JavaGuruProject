package java2courseIbank.database.JDBC;

import java2courseIbank.database.Database;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.Transaction;
import java2courseIbank.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
public class JDBCDatabaseImpl extends JDBCRepository implements Database {
    @Override
    public void changeBalance(double amt, String accountNumber) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "update ACCOUNTS set balance = ? where account_number = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, amt);
            preparedStatement.setString(2, accountNumber);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception while execute ProductDAOImpl.save()");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<Account> getAccountByAccNumber(String AccNumber) {
       /* Connection connection;
        Account account = null;
        try {
            connection = getConnection();
            String sql = "select * from ACCOUNTS where account_number = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, AccNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account(resultSet.getString("account_number"),
                        resultSet.getDouble("balance"),
                        resultSet.getString("currency"));
            }
        } catch (Exception e) {
            System.out.println("Exception while execute ProductDAOImpl.save()");
            e.printStackTrace();
        }
        return Optional.ofNullable(account);
        */
        return Optional.empty();
    }

    @Override
    public List<Account> getAccountsByUserId(User user) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        Connection connection;
        List<Transaction> transactions = new ArrayList<>();
        try {
            connection = getConnection();
            String sql = "select * from TRANSACTIONS where from_account_number = ? or to_account_number = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, accountNumber);
            preparedStatement.setString(2, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setFromAcc(getAccountByAccNumber(resultSet.getString("from_account_number")).get());
                transaction.setToAccount(getAccountByAccNumber(resultSet.getString("to_account_number")).get());
                transaction.setDateTime(resultSet.getTimestamp("dateTime"));
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }


    @Override
    public Optional<User> getUser(String username, String password) {
       /* Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from USERS where username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User(resultSet.getLong("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("username"), resultSet.getString("password"));
            }
            return Optional.ofNullable(user);
        } catch (Throwable e) {
            System.out.println("Exception while execute ProductDAOImpl.getByTitle()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        */
       return Optional.empty();
    }

    @Override
    public Optional<User> getUser(String username) {
        /*Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from USERS where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User(resultSet.getLong("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("username"), resultSet.getString("password"));
            }
            return Optional.ofNullable(user);
        } catch (Throwable e) {
            System.out.println("Exception while execute ProductDAOImpl.getByTitle()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        */
        return Optional.empty();
    }

    @Override
    public List<Account> getAccountsByUserId(Long id) {
       /* Connection connection = null;
        List<Account> accounts = new ArrayList<>();
        try {
            connection = getConnection();
            String sql = "select * from ACCOUNTS where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account(
                        resultSet.getString("account_number"),
                        resultSet.getDouble("balance"),
                        resultSet.getString("currency")
                );
                accounts.add(account);
            }
            return accounts;
        } catch (Exception e) {
            return accounts;
        } finally {
            closeConnection(connection);
        }
        /*/
       return new ArrayList<>();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Connection connection;
        try {
            connection = getConnection();
            String sql = "insert into Transactions(transaction_id, from_account_number, to_account_number, dateTime, amount) " +
                    "values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, transaction.getFromAccount().getNumber());
            preparedStatement.setString(2, transaction.getToAccount().getNumber());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(transaction.getDateTime()));
            preparedStatement.setDouble(4, transaction.getAmount());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                transaction.setId(rs.getLong(1));
            }
        } catch (Exception e) {

        }
    }



}
