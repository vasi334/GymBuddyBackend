package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.AntrenorFitness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AntrenoriFitnessRepository extends JpaRepository<AntrenorFitness, Long> {

    /**
     * Facem un Query de tipul JPQL si pentru toate instantele de tipul AntrenorFitness(ce are fiecare
     * asociata o unica inregistrare in baza de date), identificate fiecare prin aliasul a, si identificam acelea
     * ce au field-ul egal cu informatia de conntact dorita;
     * ?1-placeholder(tine locul) pentru primul(si singurul) parametru al metodei
     * @param contactInformation-String
     * @return Antrenorul ce are informatiile de contact dorit sau null in caz contrar
     */
    @Query("select a from AntrenorFitness a where a.contactInformation = ?1")
    Optional<AntrenorFitness> findByEmail(String contactInformation);
}
