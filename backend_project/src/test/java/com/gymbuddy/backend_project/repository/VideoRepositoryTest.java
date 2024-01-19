package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.Video;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VideoRepositoryTest {
    @Autowired
    private VideoRepository videoTestRepository;

    @AfterEach
    void clear(){
        videoTestRepository.deleteAll();
    }

    @Test
    void checkExistsVideoWithDesiredDuration() {
        //given
        int durationMinutes = 10;
        String random = "random";
        Video video = new Video();
        video.setDurationMinutes(durationMinutes);
        video.setUrl(random);
        video.setTitle(random);
        video.setIntensity(random);

        videoTestRepository.save(video);

        //then
        List<Video> searchedVideo = videoTestRepository.findByDurationMinutes(durationMinutes);

        //expected
        assertThat(searchedVideo).hasSize(1);
        assertThat(videoTestRepository.findAll())
                .filteredOn(videoclip -> videoclip.getDurationMinutes() == durationMinutes)
                .hasSize(1);
    }

    @Test
    void checkDoesNotExistVideoWithDesiredDuration() {
        //given
        int durationMinutes = 10;

        //then
        List<Video> searchedVideo = videoTestRepository.findByDurationMinutes(durationMinutes);

        //expected
        assertThat(searchedVideo).isEmpty();
        assertThat(videoTestRepository.findAll())
                .filteredOn(videoclip -> videoclip.getDurationMinutes() == durationMinutes)
                .isEmpty();
    }

    @Test
    void checkExistMultipleVideosWithDesiredDuration(){
        //given
        int durationMinutes = 10;
        String random1 = "RandomFirstVideo";
        String random2 = "RandomSecondVideo";
        String random = "NotDesiredVideo";
        Video video1 = new Video();
        video1.setDurationMinutes(durationMinutes);
        video1.setUrl(random1);
        video1.setTitle(random1);
        video1.setIntensity(random1);

        Video video2 = new Video();
        video2.setDurationMinutes(durationMinutes);
        video2.setUrl(random2);
        video2.setTitle(random2);
        video2.setIntensity(random2);

        Video video3 = new Video();
        video3.setDurationMinutes(durationMinutes + 1000);
        video3.setUrl(random);
        video3.setTitle(random);
        video3.setIntensity(random);

        //then
        videoTestRepository.saveAll(List.of(video2, video1, video3));

        //expected
        assertThat(videoTestRepository.findByDurationMinutes(durationMinutes)).hasSizeGreaterThan(1);
        assertThat(videoTestRepository.findAll())
                .filteredOn(video -> video.getDurationMinutes() == durationMinutes)
                .hasSizeGreaterThan(1);

    }

    @Test
    void checkExistVideoWithDesiredIntensity() {
        //given
        String intensity = "Beginner";
        String random = "random";
        Video video = new Video();
        video.setDurationMinutes(10);
        video.setUrl(random);
        video.setTitle(random);
        video.setIntensity(intensity);

        videoTestRepository.save(video);

        //then
        List<Video> searchedVideo = videoTestRepository.findByIntensity(intensity);

        //expected
        assertThat(searchedVideo).hasSize(1);
        assertThat(videoTestRepository.findAll())
                .filteredOn(videoclip -> videoclip.getIntensity().equals(intensity))
                .hasSize(1);
    }

    @Test
    void checkDoesNotExistVideoWithDesiredIntensity() {
        //given
        String intensity = "Beginner";
        String notDesiredIntensity = "Intermediate";
        String random = "random";
        Video video = new Video();
        video.setDurationMinutes(10);
        video.setUrl(random);
        video.setTitle(random);
        video.setIntensity(notDesiredIntensity);

        videoTestRepository.save(video);

        //then
        List<Video> searchedVideo = videoTestRepository.findByIntensity(intensity);

        //expected
        assertThat(searchedVideo).isEmpty();
        assertThat(videoTestRepository.findAll())
                .filteredOn(videoclip -> videoclip.getIntensity().equals(intensity))
                .isEmpty();
    }

    @Test
    void checkExistMultipleVideoWithDesiredIntensity(){
        //given
        String desiredIntensity = "Beginner";
        String notDesiredIntensity = "Intermediate";
        String random = "random";
        Video video1 = new Video();
        video1.setDurationMinutes(10);
        video1.setUrl(random);
        video1.setTitle(random);
        video1.setIntensity(desiredIntensity);

        Video video2 = new Video();
        video2.setDurationMinutes(10);
        video2.setUrl(random);
        video2.setTitle(random);
        video2.setIntensity(desiredIntensity);

        Video video3 = new Video();
        video3.setDurationMinutes(10);
        video3.setUrl(random);
        video3.setTitle(random);
        video3.setIntensity(notDesiredIntensity);

        videoTestRepository.saveAll(List.of(video2, video1, video3));

        //then
        List<Video> desiredVideos = videoTestRepository.findByIntensity(desiredIntensity);
        assertThat(desiredVideos).hasSizeGreaterThan(1);
        assertThat(videoTestRepository.findAll())
                .filteredOn(video -> video.getIntensity().equals(desiredIntensity))
                .hasSizeGreaterThan(1);
    }

}