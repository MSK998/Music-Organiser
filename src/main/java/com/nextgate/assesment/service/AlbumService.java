package com.nextgate.assesment.service;

import com.nextgate.assesment.datatypes.Album;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {
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

    public List<Album> getAllAlbums(){
        try{
            List<Album> albums = new ArrayList<>();
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement getAllAlbums = connection.prepareStatement("SELECT * FROM albums");
            ResultSet resultSet = getAllAlbums.executeQuery();

            if(resultSet.isBeforeFirst()){
                while(resultSet.next()){
                    Album a = new Album(
                            resultSet.getString("singer"),
                            resultSet.getString("album"),
                            resultSet.getString("year"),
                            resultSet.getString("company"));
                    albums.add(a);
                }

                resultSet.close();
                getAllAlbums.close();
                connection.close();
                return albums;
            }

            resultSet.close();
            getAllAlbums.close();
            connection.close();

            return null;
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Database connection failed");
            return null;
        }
    }

    public List<Album> searchAlbums(String searchTerm){
        try{
            System.out.println(searchTerm.toUpperCase());
            List<Album> albums = new ArrayList<>();
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement searchAlbum = connection.prepareStatement("SELECT * FROM albums WHERE album=?");
            searchAlbum.setString(1, searchTerm.toUpperCase());

            ResultSet resultSet = searchAlbum.executeQuery();

            if(resultSet.isBeforeFirst()){
                while(resultSet.next()){
                    Album a = new Album(
                            resultSet.getString("singer"),
                            resultSet.getString("album"),
                            resultSet.getString("year"),
                            resultSet.getString("company"));

                    albums.add(a);
                }

                resultSet.close();
                searchAlbum.close();
                connection.close();

                return albums;
            }

            resultSet.close();
            searchAlbum.close();
            connection.close();

            return null;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public boolean addAlbum(Album newAlbum){
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement insertAlbum = connection.prepareStatement(
                    "INSERT INTO albums (singer, album, year, company)" +
                            "SELECT ?, ?, ?, ?" +
                            "WHERE NOT EXISTS (" +
                            "SELECT 1 FROM albums WHERE album=? AND singer =?);");

            insertAlbum.setString(1, newAlbum.getSinger().toUpperCase());
            insertAlbum.setString(2, newAlbum.getAlbum().toUpperCase());
            insertAlbum.setString(3, newAlbum.getYear());
            insertAlbum.setString(4, newAlbum.getCompany().toUpperCase());
            insertAlbum.setString(5, newAlbum.getAlbum().toUpperCase());
            insertAlbum.setString(6, newAlbum.getSinger().toUpperCase());

            int resultSet = insertAlbum.executeUpdate();

            insertAlbum.close();
            connection.close();

            return true;

        }catch (Exception e){
            System.out.println(e);
            System.out.println("Database connection failed");
            return false;
        }
    }
}
