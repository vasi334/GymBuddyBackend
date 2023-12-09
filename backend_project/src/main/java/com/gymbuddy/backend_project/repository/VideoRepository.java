package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByDurationMinutes(int duration);

    List<Video> findByIntensity(String intensity);
}

