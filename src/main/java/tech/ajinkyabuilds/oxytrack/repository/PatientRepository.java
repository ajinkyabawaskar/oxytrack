package tech.ajinkyabuilds.oxytrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.ajinkyabuilds.oxytrack.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByNickname(String nickname);

}
