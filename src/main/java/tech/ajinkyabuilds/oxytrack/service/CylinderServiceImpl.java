package tech.ajinkyabuilds.oxytrack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tech.ajinkyabuilds.oxytrack.entity.Cylinder;
import tech.ajinkyabuilds.oxytrack.repository.CylinderRepository;

@Service
public class CylinderServiceImpl implements CylinderService {

	@Autowired
	private CylinderRepository cylinderRepository;

	@Override
	public List<Cylinder> getCylinder(Integer pageNo) {
		Pageable page = PageRequest.of(pageNo - 1, 50);
		Page<Cylinder> cylinderPage = cylinderRepository.findAll(page);
		List<Cylinder> cylinders = new ArrayList<Cylinder>();
		if (cylinderPage != null && cylinderPage.hasContent()) {
			cylinders = cylinderPage.getContent();
		}
		return cylinders;
	}

	@Override
	public Cylinder createCylinder(Cylinder cylinder) {
		return cylinderRepository.save(cylinder);
	}

	@Override
	public Cylinder readCylinder(String identifier) {
		return cylinderRepository.findByIdentifier(identifier);
	}

	@Override
	public Cylinder updateCylinder(Long id, Cylinder cylinder) {
		Cylinder existing = cylinderRepository.findById(id).orElse(null);
		Cylinder updated = null;
		if (existing != null) {
			existing = cylinder;
			existing.setId(id);
			updated = cylinderRepository.save(existing);
		}
		return updated;
	}

	@Override
	public Boolean deleteCylinder(Long id) {
		Boolean deleted = Boolean.FALSE;
		Cylinder cylinder = cylinderRepository.findById(id).orElse(null);
		if (cylinder != null) {
			cylinderRepository.delete(cylinder);
			deleted = Boolean.TRUE;
		}
		return deleted;
	}

	@Override
	public Cylinder getCylinderById(Long id) {
		return cylinderRepository.findById(id).orElse(null);
	}

}
