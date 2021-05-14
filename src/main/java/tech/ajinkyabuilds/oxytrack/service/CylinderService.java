package tech.ajinkyabuilds.oxytrack.service;

import java.util.List;

import tech.ajinkyabuilds.oxytrack.entity.Cylinder;

public interface CylinderService {

	public Cylinder createCylinder(Cylinder cylinder);

	public Cylinder readCylinder(String identifier);

	public Cylinder updateCylinder(Long id, Cylinder cylinder);

	public Boolean deleteCylinder(Long id);

	public List<Cylinder> getCylinder(Integer pageNo);

	public Cylinder getCylinderById(Long id);

}
