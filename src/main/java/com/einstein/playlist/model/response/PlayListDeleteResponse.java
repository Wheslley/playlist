package com.einstein.playlist.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public final class PlayListDeleteResponse implements Serializable {
    private Long deletedPlaylistId;
}
