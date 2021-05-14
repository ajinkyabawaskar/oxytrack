package tech.ajinkyabuilds.oxytrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tech.ajinkyabuilds.oxytrack.entity.Volunteer;
import tech.ajinkyabuilds.oxytrack.repository.VolunteerRepository;

@Service
public class VolunteerServiceImpl implements VolunteerService {

	@Autowired
	private VolunteerRepository volunteerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Volunteer createVolunteer(Volunteer volunteer) {
		String password = passwordEncoder.encode(volunteer.getPassword());
		volunteer.setPassword(password);
		return volunteerRepository.save(volunteer);
	}

	@Override
	public Volunteer readVolunteer(String username) {
		return volunteerRepository.findByUsername(username);
	}

	@Override
	public Volunteer updateVolunteer(Long id, Volunteer volunteer) {
		Volunteer existing = volunteerRepository.findById(id).orElse(null);
		Volunteer updated = null;
		if (existing != null) {
			existing = volunteer;
			existing.setId(id);
			updated = volunteerRepository.save(existing);
		}
		return updated;
	}

	@Override
	public Boolean deleteVolunteer(Long id) {
		Boolean deleted = Boolean.FALSE;
		Volunteer volunteer = volunteerRepository.findById(id).orElse(null);
		if (volunteer != null) {
			volunteerRepository.delete(volunteer);
			deleted = Boolean.TRUE;
		}
		return deleted;
	}

	@Override
	public Volunteer getVolunteerById(Long id) {
		return volunteerRepository.findById(id).orElse(null);
	}

}
