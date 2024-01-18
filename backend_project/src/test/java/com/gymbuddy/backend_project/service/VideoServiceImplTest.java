package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.entity.Video;
import com.gymbuddy.backend_project.repository.VideoRepository;
import jakarta.annotation.Resource;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoServiceImplTest {

    @Mock private VideoRepository videoRepository;

    private VideoService testVideoService;

    @BeforeEach
    void setUp(){
        testVideoService = new VideoServiceImpl(videoRepository);
    }

    @Test
    void canGetAllVideos() {
        //given
        Video video = new Video();
        video.setId(1L);
        video.setDurationMinutes(10);
        video.setIntensity("Beginner");
        video.setUrl("URL");

        //when
        testVideoService.getAllVideos();

        //then
        verify(videoRepository).findAll();
        when(testVideoService.getAllVideos()).thenReturn(List.of(video));
        assertThat(testVideoService.getAllVideos())
                .filteredOn(videoclip -> videoRepository.findAll().contains(videoclip))
                .hasSameSizeAs(videoRepository.findAll());
    }

    @Test
    void canGetVideoById() {
        //given
        Long desiredId = 1L;
        Video video = new Video();
        video.setId(desiredId);
        video.setDurationMinutes(10);
        video.setIntensity("Beginner");
        video.setUrl("URL");

        //when
        testVideoService.getVideoById(desiredId);

        //then
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(videoRepository).findById(idCaptor.capture());
        Long idCaptured = idCaptor.getValue();
        assertThat(idCaptured).isEqualTo(desiredId);
        when(testVideoService.getAllVideos()).thenReturn(List.of(video));
        assertThat(testVideoService.getAllVideos())
                .hasSameSizeAs(List.of(videoRepository.findById(desiredId)));
    }

    @Test
    void canBeNonVideoOfDesiredId() {
        //given
        Long desiredId = 1L;
        given(videoRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        Video videoNotFound = testVideoService.getVideoById(desiredId);

        //then
        assertThat(videoNotFound).isNull();
        verify(videoRepository, atMostOnce()).findById(anyLong());
    }

    @Test
    void canCreateVideo(){
        //given
        Long desiredId = 1L;
        Video video = new Video();
        video.setId(desiredId);
        video.setDurationMinutes(10);
        video.setIntensity("Beginner");
        video.setUrl("URL");

        //when
        testVideoService.createVideo(video);

        //then
        ArgumentCaptor<Video> videoSavedCaptor = ArgumentCaptor.forClass(Video.class);
        verify(videoRepository).save(videoSavedCaptor.capture());
        Video savedVideo = videoSavedCaptor.getValue();
        assertThat(savedVideo).isEqualTo(video);
    }

    @Test
    void canDeleteVideo() {
        //given
        Long desiredId = 1L;
        Video video = new Video();
        video.setId(desiredId);
        video.setDurationMinutes(10);
        video.setIntensity("Beginner");
        video.setUrl("URL");

        //when
        testVideoService.deleteVideo(desiredId);

        //then
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(videoRepository).deleteById(idCaptor.capture());
        Long idForDeletedVideo = idCaptor.getValue();
        assertThat(desiredId).isEqualTo(idForDeletedVideo);

    }

    @Test
    void getVideosByDuration() {
        //given
        int desiredDuration = 10;
        Video video = new Video();
        video.setId(1L);
        video.setDurationMinutes(desiredDuration);
        video.setIntensity("Beginner");
        video.setUrl("URL");

        //when
        testVideoService.getVideosByDuration(desiredDuration);

        //then
        ArgumentCaptor<Integer> durationCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(videoRepository).findByDurationMinutes(durationCaptor.capture());
        Integer capturedDuration = durationCaptor.getValue();
        assertThat(desiredDuration).isEqualTo(capturedDuration);
        when(testVideoService.getAllVideos()).thenReturn(List.of(video));
        assertThat(testVideoService.getAllVideos())
                .hasSameSizeAs(List.of(videoRepository.findByDurationMinutes(desiredDuration)));
    }

    @Test
    void canGetVideosByIntensity() {
        //given
        String desiredIntensity = "Beginner";
        Video video = new Video();
        video.setId(1L);
        video.setDurationMinutes(10);
        video.setIntensity(desiredIntensity);
        video.setUrl("URL");

        //when
        testVideoService.getVideosByIntensity(desiredIntensity);

        //then
        ArgumentCaptor<String> intensityCaptor = ArgumentCaptor.forClass(String.class);
        verify(videoRepository).findByIntensity(intensityCaptor.capture());
        String capturedIntensity = intensityCaptor.getValue();
        assertThat(desiredIntensity).isEqualTo(capturedIntensity);
        when(testVideoService.getAllVideos()).thenReturn(List.of(video));
        assertThat(testVideoService.getAllVideos())
                .hasSameSizeAs(List.of(videoRepository.findByIntensity(desiredIntensity)));
    }
}