package com.einstein.playlist.service.impl;

import com.einstein.playlist.model.converter.PlayListConverter;
import com.einstein.playlist.model.dto.PlayListDto;
import com.einstein.playlist.model.entity.PlaylistEntity;
import com.einstein.playlist.model.request.PlayListRequest;
import com.einstein.playlist.model.response.PlayListDeleteResponse;
import com.einstein.playlist.model.response.PlayListListResponse;
import com.einstein.playlist.repository.PlayListRepository;
import com.einstein.playlist.service.PlayListService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayListServiceImpl implements PlayListService {

    private final PlayListRepository repository;
    private final PlayListConverter playListConverter;

    public PlayListServiceImpl(PlayListRepository repository, PlayListConverter playListConverter) {
        this.repository = repository;
        this.playListConverter = playListConverter;
    }

    @Override
    public PlayListListResponse getPlayList(Long id) {
        final PlayListListResponse response = new PlayListListResponse();
        return this.repository.findById(id)
                .map(entity -> PlayListListResponse.builder().playlists(Collections.singletonList(this.playListConverter.toDto(entity))).build())
                .orElse(response);
    }

    @Override
    public PlayListListResponse getAllPlayLists( ) {
        final List<PlaylistEntity> entities = this.repository.findAll();

        final List<PlayListDto> converted = entities
                .stream()
                .map(this.playListConverter::toDto)
                .collect(Collectors.toList());

        return PlayListListResponse.builder().playlists(converted).build();
    }

    @Override
    public PlayListDto createPlayList(PlayListRequest request) {
        final PlaylistEntity saved = this.repository.save(this.playListConverter.toEntity(request));
        return this.playListConverter.toDto(saved);
    }

    @Override
    public PlayListDto updatePlayList(Long id, PlayListRequest request) {
        final Optional<PlaylistEntity> optional = this.repository.findById(id);
        if ( optional.isEmpty() ) {
            return null;
        } else {
            final PlaylistEntity toBeUpdated = this.playListConverter.toEntity(request);
            toBeUpdated.setId(optional.get().getId());
            final PlaylistEntity saved = this.repository.save(toBeUpdated);
            return this.playListConverter.toDto(saved);
        }
    }

    @Override
    public PlayListDeleteResponse deletePlayList(Long id) {
        if(!this.repository.existsById(id)) {
            return PlayListDeleteResponse.builder().deletedPlaylistId(0L).build();
        } else {
            this.repository.deleteById(id);
            return PlayListDeleteResponse.builder().deletedPlaylistId(1L).build();
        }
    }

}
