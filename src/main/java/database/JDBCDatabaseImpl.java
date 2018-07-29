package database;

import domain.Account;
import domain.Transaction;
import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCDatabaseImpl extends JDBCRepository implements Database {
    @Override
    public void changeBalance(double amt, String accountNumber) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "update ACCOUNTS set (balance) = ? where account_number = ?";
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
        Connection connection;
        Account account = null;
        try {
            connection = getConnection();
            String sql = "select * from ACCOUNTS where account_number = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, AccNumber);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account(resultSet.getLong("id"),
                        resultSet.getString("account_number"),
                        resultSet.getDouble("balance"),
                        resultSet.getString("currency"),
                        getTransactionsByAccount(resultSet.getString("account_number")));
            }
        } catch (Exception e) {
            System.out.println("Exception while execute ProductDAOImpl.save()");
            e.printStackTrace();
        }
        return Optional.ofNullable(account);
    }

    @Override
    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        Connection connection;
        List<Transaction> transactions = new ArrayList<>();
        try {
            connection = getConnection();
            String sql = "select * from TRANSACTIONS where from_account_id = ? or to_account_id = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, accountNumber);
            preparedStatement.setString(2, accountNumber);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setFromAcc(getAccountById(resultSet.getLong("from_account_id")).get());
                transaction.setToAccount(getAccountById(resultSet.getLong("to_account_id")).get());
                transaction.setDateTime(resultSet.getTimestamp("dateTime"));
            }
        } catch (Exception e) {
        }

        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public Optional<User> getUser(String username, String password) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from USERS where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                List<Account> accounts = getAccountsByUserId(resultSet.getLong("id"));
                user = new User(resultSet.getLong("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), accounts,
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
    }

    @Override
    public List<Account> getAccountsByUserId(Long id) {
        Connection connection = null;
        List<Account> accounts = new ArrayList<>();
        try {
            connection = getConnection();
            String sql = "select * from ACCOUNTS where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            accounts = new ArrayList<>();
            if (resultSet.next()) {
                Account account = new Account(resultSet.getLong("id"),
                        resultSet.getString("account_number"),
                        resultSet.getDouble("balance"),
                        resultSet.getString("currency"),
                        getTransactionsByAccount(resultSet.getString("account_number")));
                accounts.add(account);
            }
            return accounts;
        } catch (Exception e) {
            return accounts;
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Connection connection;
        try {
            connection = getConnection();
            String sql = "insert into Transactions(id, from_account_id, to_account_id, dateTime, amount) " +
                    "values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, getAccountId(transaction.getFromAccount()));
            preparedStatement.setLong(2, getAccountId(transaction.getToAccount()));
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

    @Override
    public Long getAccountId(Account account) {
        Long id = null;
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select id from ACCOUNTS where account_number = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getNumber());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong("id");
            }
        } catch (Exception e) {
            //TODO
        }
        return id;
    }

    public Optional <Account> getAccountById(Long accId){
        Connection connection;
        Account account = null;
        try {
            connection = getConnection();
            String sql = "select * from ACCOUNTS where id = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, accId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account(resultSet.getLong("id"),
                        resultSet.getString("account_number"),
                        resultSet.getDouble("balance"),
                        resultSet.getString("currency"),
                        getTransactionsByAccount(resultSet.getString("account_number")));
            }
        } catch (Exception e) {
            System.out.println("Exception while execute ProductDAOImpl.save()");
            e.printStackTrace();
        }
        return Optional.ofNullable(account);
    }
}
