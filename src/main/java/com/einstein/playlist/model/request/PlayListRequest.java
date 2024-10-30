package com.einstein.playlist.model.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public final class PlayListRequest implements Serializable {

    private Long id;
    private String name;

}
