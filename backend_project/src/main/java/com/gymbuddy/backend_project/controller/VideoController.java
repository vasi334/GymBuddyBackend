package com.gymbuddy.backend_project.controller;
import com.gymbuddy.backend_project.entity.Video;
import com.gymbuddy.backend_project.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/{id}")
    public Video getVideoById(@PathVariable Long id) {
        return videoService.getVideoById(id);
    }

    @PostMapping
    public Video createVideo(@RequestBody Video video) {
        return videoService.createVideo(video);
    }

    @DeleteMapping("/{id}")
    public void deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
    }

    @GetMapping("/duration/{duration}")
    public List<Video> getVideosByDuration(@PathVariable int duration) {
        return videoService.getVideosByDuration(duration);
    }

    @GetMapping("/intensity/{intensity}")
    public List<Video> getVideosByIntensity(@PathVariable String intensity) {
        return videoService.getVideosByIntensity(intensity);
    }
}
