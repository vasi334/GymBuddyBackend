package com.gymbuddy.backend_project.controller;

import com.gymbuddy.backend_project.entity.Video;
import com.gymbuddy.backend_project.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public String getAllVideos(Model model) {
        List<Video> videos = videoService.getAllVideos();
        model.addAttribute("videos", videos);
        return "videos";
    }

    @GetMapping("/{id}")
    public String getVideoById(@PathVariable Long id, Model model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("video", video);
        return "video";
    }

    @PostMapping
    public String createVideo(@ModelAttribute Video video) {
        videoService.createVideo(video);
        return "redirect:/videos";
    }

    @DeleteMapping("/{id}")
    public String deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return "redirect:/videos";
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
}
