package com.gymbuddy.backend_project.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.gymbuddy.backend_project.entity.Video;
import com.gymbuddy.backend_project.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class AuthIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    private final Faker faker = new Faker();


//    @Test
//    //@RepeatedTest(3)
//    @Transactional
//    void canSaveVideo() throws Exception {
//        // given
//        Video video = new Video();
//        video.setUrl(faker.internet().url());
//        video.setTitle("Cardio");
//        video.setDurationMinutes(10);
//        video.setIntensity("InitialStage");
//        String desiredStage = video.getIntensity();
//
//        // when
//        ResultActions resultActions = mockMvc
//                .perform(post("/add_workout")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(video)));
//        // then
//        resultActions.andExpect(status().isOk());
//        List<Video> videosSearched = userService.findByIntensity(desiredStage);
//        Video videoAdded = videosSearched.stream().findFirst().get();
//        assertThat(videoAdded).isNotNull();
//        assertThat(videoAdded.getUrl()).isEqualTo(video.getUrl());
//        assertThat(videoAdded.getTitle()).isEqualTo(video.getTitle());
//    }
//
//    @Test
//    @Transactional
//    void canDeleteVideo() throws Exception {
//        // given
//        Video video = new Video();
//        video.setUrl(faker.internet().url());
//        video.setTitle("Cardio");
//        video.setDurationMinutes(10);
//        video.setIntensity("InitialStage");
//        String desiredStage = video.getIntensity();
//
//        mockMvc.perform(post("/add_workout")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(video)));
//
//
//        MvcResult getVideoResults = mockMvc.perform(get("/display")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        List<Video> videosSearched = (List<Video>) getVideoResults.getModelAndView()
//                .getModelMap().get("videos");
//
//        long idForVideoToBeDeleted = videosSearched.stream()
//                .filter(videoCautat -> videoCautat.getIntensity().equals(desiredStage))
//                .map(Video::getId)
//                .findFirst()
//                .orElseThrow(() ->
//                        new IllegalStateException(
//                                "Video cu url: " + video.getUrl() + " nu a fost gasit!"));
//
//        // when
//        ResultActions resultActions = mockMvc
//                .perform(delete("/workout/" + idForVideoToBeDeleted));
//
//        // then
//        resultActions.andExpect(status().isOk());
//        boolean existsVideoThatHasBeenDeleted = userService.existsById(idForVideoToBeDeleted);
//        assertThat(existsVideoThatHasBeenDeleted).isFalse();
//    }
//
//    @Test
//    void canGetAllVideos() throws Exception {
//        //given
//        Video firstVideoAdded = userService.findAll().get(0);
//
//        //when
//        MvcResult getVideoResults = mockMvc.perform(get("/display")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//
//        List<Video> videosSearched = (List<Video>) getVideoResults.getModelAndView()
//                .getModelMap().get("videos");
//
//        //then
//        assertThat(videosSearched).hasSizeGreaterThanOrEqualTo(1);
//        assertThat(videosSearched)
//                .filteredOn(video -> video.getId().equals(firstVideoAdded.getId()))
//                .hasSize(1);
//    }
}
