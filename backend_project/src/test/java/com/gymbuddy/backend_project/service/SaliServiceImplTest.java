package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.entity.SalaFitness;
import com.gymbuddy.backend_project.repository.SaliRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaliServiceImplTest {

    @Mock
    private SaliRepository saliRepository;
    private SaliServiceImpl testSaliService;

    @BeforeEach
    void setUp() {
        testSaliService = new SaliServiceImpl(saliRepository);
    }

    @Test
    void canGetAllSali() {
        //given
        SalaFitness salaFitness = SalaFitness.builder()
                .id(1L)
                .nume("GymBuddy")
                .adresa("adresa")
                .webAdresa("webAdresa")
                .build();

        //when
        //then
        testSaliService.getAllSali();
        verify(saliRepository).findAll();
        when(testSaliService.getAllSali()).thenReturn(List.of(salaFitness));
        assertThat(testSaliService.getAllSali())
                .filteredOn(salaFitness1 -> saliRepository.findAll().contains(salaFitness1))
                .hasSize(saliRepository.findAll().size());
    }

    @Test
    void canGetSalaById() {
        //given
        Long idSala = 1L;

        //when
        testSaliService.getSalaById(1L);

        //then
        ArgumentCaptor<Long> idSalaCaptor = ArgumentCaptor.forClass(Long.class);
        verify(saliRepository).findById(idSalaCaptor.capture());
        Long idForSalaToBeFound = idSalaCaptor.getValue();
        assertThat(idForSalaToBeFound).isEqualTo(idSala);
    }

    @Test
    void canCreateSala() {
        //given
        SalaFitness salaFitness = SalaFitness.builder()
                .id(1L)
                .nume("GymBuddy")
                .adresa("adresa")
                .webAdresa("webAdresa")
                .build();

        //when
        testSaliService.createSala(salaFitness);

        //then
        ArgumentCaptor<SalaFitness> salaCaptor = ArgumentCaptor
                .forClass(SalaFitness.class);
        verify(saliRepository).save(salaCaptor.capture());
        SalaFitness salaToBeSaved = salaCaptor.getValue();
        assertThat(salaToBeSaved).isEqualTo(salaFitness);

    }


    @Test
    void canDeleteSalaById() {
        //given
        Long idSalaToBeDeleted = 1L;

        //when
        testSaliService.deleteSala(idSalaToBeDeleted);

        //then
        ArgumentCaptor<Long> saliCaptor = ArgumentCaptor.forClass(Long.class);
        verify(saliRepository).deleteById(saliCaptor.capture());
        Long idCaptured = saliCaptor.getValue();
        assertThat(idSalaToBeDeleted).isEqualTo(idCaptured);
    }

    @Test
    void canGetSaliByNume() {
        //given
        String numeDesired = "GymBuddy";
        SalaFitness salaFitness = SalaFitness.builder()
                .id(1L)
                .nume(numeDesired)
                .adresa("adresa")
                .webAdresa("webAdresa")
                .build();

        //when
        testSaliService.getSaliByNume(numeDesired);

        //then
        ArgumentCaptor<String> numeCaptor = ArgumentCaptor.forClass(String.class);
        verify(saliRepository).findByNume(numeCaptor.capture());
        String numeObtained = numeCaptor.getValue();
        assertThat(numeObtained).isEqualTo(numeDesired);

        when(testSaliService.getSaliByNume(numeDesired))
                .thenReturn(List.of(salaFitness));
        assertThat(testSaliService.getSaliByNume(numeDesired))
                .filteredOn(sala -> saliRepository.findByNume(numeDesired)
                        .contains(sala))
                .hasSize(saliRepository.findByNume(numeDesired).size());
    }

    @Test
    void canUpdateExistingSala() {
        //given
        Long idSalaToBeUpdated = 1L;
        SalaFitness salaToBeUpdated = SalaFitness.builder()
                .id(1L)
                .nume("18Gym")
                .adresa("Sommmething")
                .webAdresa("Lol")
                .build();
        SalaFitness updateSalaFitness = SalaFitness.builder()
                .id(1L)
                .nume("18Gym")
                .adresa("adresa")
                .webAdresa("webAdresa")
                .build();
        given(saliRepository.findById(anyLong()))
                .willReturn(Optional.of(salaToBeUpdated));

        //when
        testSaliService.updateSala(idSalaToBeUpdated, updateSalaFitness);

        //then
        ArgumentCaptor<SalaFitness> newSalaCaptor = ArgumentCaptor
                .forClass(SalaFitness.class);
        verify(saliRepository).save(newSalaCaptor.capture());
        SalaFitness capturedSala = newSalaCaptor.getValue();
        assertThat(capturedSala).isEqualTo(salaToBeUpdated);
        assertThat(capturedSala.getWebAdresa()).isEqualTo(updateSalaFitness.getWebAdresa());
        assertThat(capturedSala.getAdresa()).isEqualTo(updateSalaFitness.getAdresa());
    }

    @Test
    void canNotUpdateNonExistingSala() {
        //given
        Long idSalaToBeUpdate = 1L;
        SalaFitness updateSalaFitness = SalaFitness.builder()
                .id(1L)
                .nume("18Gym")
                .adresa("adresa")
                .webAdresa("webAdresa")
                .build();
        given(saliRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        SalaFitness updatedSala = testSaliService.updateSala(idSalaToBeUpdate, updateSalaFitness);

        //then
        verify(saliRepository, never()).save(updateSalaFitness);
        assertThat(updatedSala).isNull();
    }
}