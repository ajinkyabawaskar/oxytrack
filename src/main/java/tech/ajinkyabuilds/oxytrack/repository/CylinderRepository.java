package tech.ajinkyabuilds.oxytrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.ajinkyabuilds.oxytrack.entity.Cylinder;

@Repository
public interface CylinderRepository extends JpaRepository<Cylinder, Long> {

	Cylinder findByIdentifier(String identifier);

}
