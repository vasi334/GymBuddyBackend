package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.entity.AntrenorFitness;
import com.gymbuddy.backend_project.repository.AntrenoriFitnessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Framework ul Mockito este folosit cand testam clase ce invoca dependinte!!!
 * De exemplu, Layer ul Service se afla in stransa legatura cu layerul Repository,
 * IAR in majoritatea metodelor din clasele de tip Service(De exemple cele de baza, ABSOLUT TOATE: deleteById(), findAll(),
 * save(), etc..) Service ul apeleaza metodele layer-ul Repository, VREM sa verificam
 * acest aspect, IAR asta facem cu Mockito!
 */
//Folosirea acestui tag este ECHIVALENTUL liinilor de cod comentate!!!
@ExtendWith(MockitoExtension.class)
@Transactional
class AntrenoriFitnessServiceTest {

    //depenndinta de care ne legam CAND testam service-ul, ADNOTATA cu Mock
    @Mock private AntrenoriFitnessRepository antrenoriRepo;
   // private AutoCloseable autoCloseable;
    private AntrenoriFitnessService testAntrenoriService;

    @BeforeEach
    void setUp(){
//        autoCloseable = MockitoAnnotations.openMocks(this);
        testAntrenoriService = new AntrenoriFitnessServiceImpl(antrenoriRepo);
    }

//    @AfterEach
//    void tearDown() throws Exception{
//        autoCloseable.close();
//    }

    @Test
    void canGetAllAntrenori(){
        //when
        testAntrenoriService.findAllAntrenoriFitness();

        /*then(We verify THAT the findAll() method os teh associated repo IS CALLED!)
        Moreover, WE DO NOT need to concercn ourselves with the in-memory-database h2,
        methods called, and so on, CAUSE everything has been done WHEN testing teh repository,
        AND THUS the Unit Tests WILL BE much more efficient!
         */
        verify(antrenoriRepo).findAll();
    }


    @Test
    void canSaveAntrenor() {
        //given
        String contactInformation = "rauldolha@gmail.com";
        AntrenorFitness antrenorFitness = AntrenorFitness.builder()
                .contactInformation(contactInformation)
                .firstName("Raul")
                .lastName("Dolha")
                .build();

        //when
        testAntrenoriService.save(antrenorFitness);

        //then
        ArgumentCaptor<AntrenorFitness> antrenorFitnessArgumentCaptor =
                ArgumentCaptor.forClass(AntrenorFitness.class);

        /*
        Folosim ArgumentCaptor pentu a verifica ca metoda save apelata de catre
        Repository layer ADAUGA acelasi antrenor ca metoda save din Service layer
        (Verificam ca metoda save e apelata in Repository PRIMA SI PRIMA DATA, dupa captam
        valoarea pentru care este apelata; In fine, VERIFICAM ca parametrul pentru metodele
        coresp Repository si Service save sa fie unul si acelasi pentru cele 2 metoda)
         */
        verify(antrenoriRepo).save(antrenorFitnessArgumentCaptor.capture());

        AntrenorFitness capturedAntrenor = antrenorFitnessArgumentCaptor.getValue();

        assertThat(capturedAntrenor).isEqualTo(antrenorFitness);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        String contactInformation = "rauldolha@gmail.com";
        AntrenorFitness antrenorFitness = AntrenorFitness.builder()
                .contactInformation(contactInformation)
                .firstName("Raul")
                .lastName("Dolha")
                .build();

        /*
        Cu ajutorul acestei metode, PUTEM CONTROLA aceasta ramura in cadrul
        metodei save din AntrenoriFitnessServiceImpl, sa ne asiguram ca
        este indeplinita conditia in if pentru a arunca O EXCEPTIE!!!
         */
        given(antrenoriRepo.findByEmail(anyString()))
                .willReturn(Optional.of(antrenorFitness));

        //when
        //then
        assertThatThrownBy(() -> testAntrenoriService.save(antrenorFitness))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Exista deja un antrenor cu aceste informatii de contact!");
        /*
        In cazul in care arunca o exceptie, NE ASIGURAM CA NU SE apeleaza
        metoda save pentru NICI un fel de argument(de tip specific metodei)
         */
        verify(antrenoriRepo, never()).save(any());
    }

    @Test
    void canGetAntrenorByContactInformation() {
        //given
        String contactInformation = "rauldolha@gmail.com";
        AntrenorFitness antrenorFitness = AntrenorFitness.builder()
                .contactInformation(contactInformation)
                .firstName("Raul")
                .lastName("Dolha")
                .build();

        //when
        testAntrenoriService.findAntrenorByContactInformation(contactInformation);

        //then
        ArgumentCaptor<String> contactInformationCaptor = ArgumentCaptor.forClass(String.class);

        /*
        E apelata metoda aferenta din Repsitory, cu argumentul dorit!
         */
        verify(antrenoriRepo).findByEmail(contactInformationCaptor.capture());
        assertThat(contactInformationCaptor.getValue()).isEqualTo(contactInformation);
    }

    @Test
    void canDeleteAntrenor() {
        //given
        Long idAntrenorToBeDeleted = 10L;
        /*
        AnyLong() (sau orice alt AnyType()) este un Matecher specific framework ului Mockito,
        si este folosit pentru a spune ca pentru orice argument DE TIP LONG(in cazul concret),
        ne dorim ca metoda sa returneze True
         */
        given(antrenoriRepo.existsById(anyLong()))
                .willReturn(true);

        //when
        testAntrenoriService.deleteAntrenor(idAntrenorToBeDeleted);

        //then
        ArgumentCaptor<Long> antrenorIdCaptor = ArgumentCaptor.forClass(Long.class);
        verify(antrenoriRepo).deleteById(antrenorIdCaptor.capture());
        Long idCaptured = antrenorIdCaptor.getValue();
        assertThat(idAntrenorToBeDeleted).isEqualTo(idCaptured);
    }

    @Test
    void  willThrowWhenAntrenorWithIdDoesNotExist(){
        //given
        Long idAntrenorToBeDeleted = 10L;
        given(antrenoriRepo.existsById(anyLong())).willReturn(false);

        //when
        //then
        assertThatThrownBy(() -> testAntrenoriService.deleteAntrenor(idAntrenorToBeDeleted))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Antrenorul cu id " + idAntrenorToBeDeleted +  " nu exista!");
        verify(antrenoriRepo, never()).deleteById(any());
    }

    /**
     * Pentru metodele private NU SE FAC TESTE!!! Doar pentru cele ce vizeaza User ul!!!
     */
}