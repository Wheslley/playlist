package com.einstein.playlist.service;

import com.einstein.playlist.model.dto.PlayListDto;
import com.einstein.playlist.model.request.PlayListRequest;
import com.einstein.playlist.model.response.PlayListDeleteResponse;
import com.einstein.playlist.model.response.PlayListListResponse;

public interface PlayListService {
    PlayListListResponse getPlayList(Long id);
    PlayListListResponse getAllPlayLists();
    PlayListDto createPlayList(PlayListRequest request);
    PlayListDto updatePlayList(Long id, PlayListRequest request);
    PlayListDeleteResponse deletePlayList(Long id);
}
