package com.einstein.playlist.model.converter;

import com.einstein.playlist.model.dto.PlayListDto;
import com.einstein.playlist.model.entity.PlaylistEntity;
import com.einstein.playlist.model.request.PlayListRequest;
import org.springframework.stereotype.Component;

@Component
public final class PlayListConverter {

    public PlayListDto toDto(PlaylistEntity entity) {
        if(entity == null) {
            return null;
        } else {
            return PlayListDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
        }
    }

    public PlaylistEntity toEntity(PlayListRequest request) {
        if(request == null) {
            return null;
        } else {
            return PlaylistEntity.builder()
                    .name(request.getName())
                    .build();
        }
    }

}
