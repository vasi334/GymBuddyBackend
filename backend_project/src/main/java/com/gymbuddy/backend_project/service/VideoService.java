package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.entity.Video;
import com.gymbuddy.backend_project.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    public Video createVideo(Video video) {
        return videoRepository.save(video);
    }

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }

    public List<Video> getVideosByDuration(int duration) {
        return videoRepository.findByDurationMinutes(duration);
    }

    public List<Video> getVideosByIntensity(String intensity) {
        return videoRepository.findByIntensity(intensity);
    }
}

