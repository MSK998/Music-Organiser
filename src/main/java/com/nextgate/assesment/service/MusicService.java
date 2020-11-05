package com.nextgate.assesment.service;

import com.nextgate.assesment.datatypes.Album;
import com.nextgate.assesment.datatypes.Singer;
import com.nextgate.assesment.datatypes.Song;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Music service that will handle the CRUD operations
 *
 * TODO: Add more methods & link to a SQL database
 *
 * @author nextgate.employee
 */
@Service
public class MusicService {

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
     * Retrieve a song by Id
     *
     * @param Id the song Id
     * @return Song
     */
    public Song getSongById(String Id) {
        return new Song("Test Song");
    }

    public List<Singer> getAllSingers(){
        try{
            List<Singer> singers = new ArrayList<>();
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement getAllSingers = connection.prepareStatement("SELECT * FROM singers");
            ResultSet resultSet = getAllSingers.executeQuery();

            if(resultSet.isBeforeFirst()){
                while(resultSet.next()){
                    Singer s = new Singer(
                            resultSet.getString("name"),
                            resultSet.getString("dob"),
                            resultSet.getString("sex"),
                            resultSet.getString("company"));
                    singers.add(s);
                }
                return singers;
            }

            resultSet.close();
            getAllSingers.close();
            connection.close();

            return null;
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Database connection failed");
            return null;
        }
    }

    public List<Singer> searchSingers(String searchTerm){
        return new ArrayList<Singer>();
    }

    public boolean addSinger(Singer singer){
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement insertSinger = connection.prepareStatement(
                    "INSERT INTO singers (name, dob, sex, company)" +
                            "SELECT ?, ?, ?, ?" +
                            "WHERE NOT EXISTS (" +
                            "SELECT 1 FROM singers WHERE name =?);");

            insertSinger.setString(1, singer.getName());
            insertSinger.setString(2, singer.getDateOfBirth());
            insertSinger.setString(3, singer.getSex());
            insertSinger.setString(4, singer.getCompany());
            insertSinger.setString(5, singer.getName());

            int resultSet = insertSinger.executeUpdate();

            insertSinger.close();
            connection.close();

            return true;

        }catch (Exception e){
            System.out.println(e);
            System.out.println("Database connection failed");
            return false;
        }
    }

}
