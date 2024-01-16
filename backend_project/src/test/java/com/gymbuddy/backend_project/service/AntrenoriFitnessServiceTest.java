package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.repository.AntrenoriFitnessRepository;
import com.gymbuddy.backend_project.service.AntrenoriFitnessService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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
class AntrenoriFitnessServiceTest {

    //depenndinta de care ne legam CAND testam service-ul, ADNOTATA cu Mock
    @Mock private AntrenoriFitnessRepository antrenoriRepo;
   // private AutoCloseable autoCloseable;
    private AntrenoriFitnessService testAntrenoriService;

//    @BeforeEach
//    void setUp(){
//        autoCloseable = MockitoAnnotations.openMocks(this);
//        testAntrenoriService = new AntrenoriFitnessServiceImpl(antrenoriRepo);
//    }

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


}