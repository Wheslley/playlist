package com.einstein.playlist.controller;

import com.einstein.playlist.model.dto.PlayListDto;
import com.einstein.playlist.model.request.PlayListRequest;
import com.einstein.playlist.model.response.PlayListDeleteResponse;
import com.einstein.playlist.model.response.PlayListListResponse;
import com.einstein.playlist.service.PlayListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public final class PlaylistController {

    private final PlayListService playListService;

    public PlaylistController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @GetMapping("/playlist/{id}")
    public ResponseEntity<PlayListListResponse> getPlaylist(@PathVariable("id") Long id) {
        PlayListListResponse response = this.playListService.getPlayList(id);
        if(CollectionUtils.isEmpty(response.getPlaylists())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/playlists")
    public ResponseEntity<PlayListListResponse> playlist() {
        PlayListListResponse response = this.playListService.getAllPlayLists();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/playlist")
    public ResponseEntity<PlayListDto> createPlaylist(@RequestBody PlayListRequest request) {
        return new ResponseEntity<>(this.playListService.createPlayList(request), HttpStatus.CREATED);
    }

    @PutMapping("/playlist/{id}")
    public ResponseEntity<PlayListDto> updatePlaylist(@PathVariable("id") Long id, @RequestBody PlayListRequest request) {
        PlayListDto playlistDto = this.playListService.updatePlayList(id, request);
        if(playlistDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(playlistDto, HttpStatus.OK);
        }
    }

    @DeleteMapping("/playlist/{id}")
    public ResponseEntity<PlayListDto> deletePlaylist(@PathVariable("id") Long id) {
        if(this.playListService.deletePlayList(id).getDeletedPlaylistId()==0L){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
