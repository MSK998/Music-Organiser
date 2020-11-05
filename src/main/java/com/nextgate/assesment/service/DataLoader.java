package com.nextgate.assesment.service;

import com.nextgate.assesment.datatypes.Album;
import com.nextgate.assesment.datatypes.Singer;
import com.nextgate.assesment.datatypes.User;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private static UserService userService = new UserService();
    private static AlbumService albumService = new AlbumService();
    private static MusicService musicService = new MusicService();

    public static void main(String[] args) throws IOException{
        try{
            System.out.println(java.time.LocalDate.now() + ".txt");
            if(checkForFile() != null){
                addDataToDatabase(checkForFile());
            }

            System.out.println("Data loading complete");
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static Path checkForFile() throws IOException {
        Path path = Paths.get("data");
        try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)){

            ArrayList<Path> filePaths = new ArrayList<>();

            for(Path filePath: directoryStream){
                filePaths.add(filePath);
            }
            if(filePaths.isEmpty()){
                return null;
            }
            return filePaths.get(0);
        }
    }

    public static void addDataToDatabase(Path pathToFile) throws IOException{

        List<String> strings = Files.readAllLines(pathToFile);
        Files.move(pathToFile, Paths.get("archived/" + java.time.LocalDate.now() + ".txt"));
        List<Singer> singers = new ArrayList<>();
        List<Album> albums = new ArrayList<>();
        List<User> users = new ArrayList<>();

        for (String line : strings) {
            String[] split = line.split("\\|");

            switch (split[0]){
                case "A":
                    Album a = new Album(split[1].trim(), split[2].trim(), split [3].trim(), split[4].trim());
                    System.out.println(a.toString());
                    albums.add(a);
                    break;
                case "U":
                    User u = new User(split[1].trim(), split[2].trim());
                    System.out.println(u.toString());
                    users.add(u);
                    break;
                case "S":
                    Singer s = new Singer(split[1].trim(), split[2].trim(), split[3].trim(), split[4].trim());
                    System.out.println(s.toString());
                    singers.add(s);
                    break;
            }

        }
        for(User user : users){
            userService.signupUser(user);
        }

        for(Singer singer : singers){
            musicService.addSinger(singer);
        }

        for(Album album : albums){
            albumService.addAlbum(album);
        }

    }
}
