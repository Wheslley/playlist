package com.einstein.playlist.model.response;

import com.einstein.playlist.model.dto.PlayListDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PlayListListResponse implements Serializable {
    private List<PlayListDto> playlists;
}
