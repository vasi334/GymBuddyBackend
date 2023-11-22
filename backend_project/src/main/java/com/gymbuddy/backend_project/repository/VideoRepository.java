package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.dto.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
