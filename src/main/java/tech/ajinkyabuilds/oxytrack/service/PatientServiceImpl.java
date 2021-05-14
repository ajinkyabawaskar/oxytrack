package tech.ajinkyabuilds.oxytrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.ajinkyabuilds.oxytrack.entity.Patient;
import tech.ajinkyabuilds.oxytrack.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient createPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public Patient readPatient(String nickname) {
		return patientRepository.findByNickname(nickname);
	}

	@Override
	public Patient updatePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public Boolean deletePatient(String nickname) {
		Boolean deleted = Boolean.FALSE;
		Patient patient = this.readPatient(nickname);
		if (patient != null) {
			patientRepository.delete(patient);
			deleted = Boolean.TRUE;
		}
		return deleted;
	}

}
