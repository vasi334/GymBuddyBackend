package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.entity.Video;

import java.util.List;

public interface VideoService {
    List<Video> getAllVideos();
    Video getVideoById(Long id);
    Video createVideo(Video video);
    void deleteVideo(Long id);
    List<Video> getVideosByDuration(int duration);
    List<Video> getVideosByIntensity(String intensity);
}


