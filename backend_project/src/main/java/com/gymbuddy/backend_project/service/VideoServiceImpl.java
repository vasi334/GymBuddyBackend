package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.entity.Video;
import com.gymbuddy.backend_project.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Video createVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }

    @Override
    public List<Video> getVideosByDuration(int duration) {
        return videoRepository.findByDurationMinutes(duration);
    }

    @Override
    public List<Video> getVideosByIntensity(String intensity) {
        return videoRepository.findByIntensity(intensity);
    }
}

