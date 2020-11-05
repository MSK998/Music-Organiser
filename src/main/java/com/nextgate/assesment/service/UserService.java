package com.nextgate.assesment.service;

import com.nextgate.assesment.datatypes.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

@Service
public class UserService {

    String DATABASE_URL;
    URI DATABASE_URI;

    {
        try {
            DATABASE_URI = new URI(System.getenv("DATABASE_URL"));
            String username = DATABASE_URI.getUserInfo().split(":")[0];
            String password = DATABASE_URI.getUserInfo().split(":")[1];
            DATABASE_URL = "jdbc:postgresql://" +
                    DATABASE_URI.getHost() +
                    ":"+
                    DATABASE_URI.getPort() +
                    DATABASE_URI.getPath() +
                    "?user=" + username +
                    "&password=" + password;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve user from the database if it exists
     * @param loginDetails the details supplied by the user
     * @return String stating whether or not the user is logged in
     * */
    public boolean loginUser(User loginDetails){
        try{
            // Creating the connection and statement
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement findUser = connection.prepareStatement("SELECT * FROM users WHERE username=?");
            findUser.setString(1, loginDetails.getUsername());

            // Executing the statement and getting the result
            ResultSet resultSet = findUser.executeQuery();
            resultSet.next();

            // Checking if the user matches
            if (resultSet.getString("password").equals(loginDetails.getPassword())){
                System.out.println("Correct Details");
                resultSet.close();
                findUser.close();
                connection.close();
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Database connection failed");
            return false;
        }

        System.out.println("Incorrect Details");
        return false;
    }

    /**
     * Add a new user to the database
     * @param signupDetails the details the user supplies to login
     * @return String stating whether or not the new user details have been saved
     * */
    public boolean signupUser(User signupDetails){
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement insertUser = connection.prepareStatement("INSERT INTO users (username, password)" +
                    "SELECT ?, ?" +
                    "WHERE NOT EXISTS (" +
                    "SELECT 1 FROM users WHERE username=?);");

            // Populating the statement
            insertUser.setString(1, signupDetails.getUsername());
            insertUser.setString(2, signupDetails.getPassword());
            insertUser.setString(3, signupDetails.getUsername());

            int resultSet = insertUser.executeUpdate();

            insertUser.close();
            connection.close();

            return true;

        }catch (Exception e){
            System.out.println(e);
            System.out.println("Database connection failed");
            return false;
        }
    }
}
