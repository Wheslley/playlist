package com.einstein.playlist.repository;

import com.einstein.playlist.model.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends JpaRepository<PlaylistEntity, Long> {

}
