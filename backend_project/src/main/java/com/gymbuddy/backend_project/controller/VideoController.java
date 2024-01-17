package com.gymbuddy.backend_project.controller;

import com.gymbuddy.backend_project.entity.Video;
import com.gymbuddy.backend_project.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/workouts")
    public String getWorkoutsPage(Model model) {
        return "workouts";
    }

    @GetMapping("/{id}")
    public String getVideoById(@PathVariable Long id, Model model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("video", video);
        return "single_video";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        try {
            videoService.deleteVideo(id);
            return new ResponseEntity<>("Videoclip șters cu succes!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Eroare la ștergerea videoclipului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/duration/{duration}")
    public String getVideosByDuration(@PathVariable int duration, Model model) {
        List<Video> videos = videoService.getVideosByDuration(duration);
        model.addAttribute("videos", videos);
        return "videos";
    }

    @GetMapping("/intensity/{intensity}")
    public String getVideosByIntensity(@PathVariable String intensity, Model model) {
        List<Video> videos = videoService.getVideosByIntensity(intensity);
        model.addAttribute("videos", videos);
        return "videos";
    }

    @GetMapping("/display")
    public String displayVideosPage(Model model) {
        List<Video> videos = videoService.getAllVideos();
        model.addAttribute("videos", videos);
        return "videos";
    }

    @GetMapping("/add_video")
    public String showAddVideoPage() {
        return "add_video";
    }

    @PostMapping("/add_video")
    public ResponseEntity<String> createVideo(@RequestBody Video videoRequest) {
        try {
            Video video = new Video();
            video.setTitle(videoRequest.getTitle());
            video.setUrl(videoRequest.getUrl());
            video.setDurationMinutes(videoRequest.getDurationMinutes());
            video.setIntensity(videoRequest.getIntensity());

            videoService.createVideo(video);

            return new ResponseEntity<>("Videoclip salvat cu succes!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Eroare la salvarea videoclipului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
