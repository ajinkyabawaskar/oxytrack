package tech.ajinkyabuilds.oxytrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.ajinkyabuilds.oxytrack.entity.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

	Volunteer findByUsername(String username);

}
