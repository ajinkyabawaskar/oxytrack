package tech.ajinkyabuilds.oxytrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import tech.ajinkyabuilds.oxytrack.entity.Volunteer;
import tech.ajinkyabuilds.oxytrack.repository.VolunteerRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Value("${spring.security.user.name}")
	private String defaultUsername;

	@Value("${spring.security.user.password}")
	private String defaultPassword;

	private PasswordEncoder passwordEncoder;

	@Autowired
	private VolunteerRepository volunteerRepository;

	@Autowired
	public DataLoader(@Lazy final PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(ApplicationArguments args) {
		Volunteer fetchedVolunteer = volunteerRepository.findByUsername(defaultUsername);
		if (fetchedVolunteer == null) {
			String password = passwordEncoder.encode(defaultPassword);
			Volunteer volunteer = new Volunteer();
			volunteer.setUsername(defaultUsername);
			volunteer.setPassword(password);
			volunteerRepository.save(volunteer);
		}
	}
}