package com.nextgate.assesment.rest;

import com.nextgate.assesment.datatypes.Album;
import com.nextgate.assesment.datatypes.Singer;
import com.nextgate.assesment.datatypes.Song;
import com.nextgate.assesment.service.AlbumService;
import com.nextgate.assesment.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A REST controller for managing the music catalogue via the Music service
 *
 * TODO: Add more methods
 *
 * @author nextgate.employee
 */
@RestController
public class MusicRestController {
    
    @Autowired
    private MusicService musicService;
    @Autowired
    private AlbumService albumService;
    
    /**
     * GET method to retrieve a song via an Id
     * @param songId the song Id
     * @return Song
     */
    @GetMapping("/song/{songId}")
    public Song song(@PathVariable("songId") String songId) {
        return musicService.getSongById(songId);
    }

    @GetMapping("/singers")
    public List<Singer> allSingers(){
        return musicService.getAllSingers();
    }

    @GetMapping("/singers/search")
    public List<Singer> singerSearch(@RequestParam("name") String name){
        return musicService.searchSingers(name);
    }

    @GetMapping("/albums")
    public List<Album> allAlbums(){
        return albumService.getAllAlbums();
    }

    @GetMapping("/albums/search")
    public List<Album> albumSearch(@RequestParam("album") String album){
        return albumService.searchAlbums(album);
    }

    @PostMapping("/singers")
    public ResponseEntity addSinger(@RequestBody Singer newSinger){
        if(musicService.addSinger(newSinger)){
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/albums")
    public ResponseEntity addAlbum(@RequestBody Album newAlbum){
        if(albumService.addAlbum(newAlbum)){
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }
    
}
