package sandwich.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sandwich.model.entities.User;
import sandwich.repository.UserJpaRepository;
import org.apache.logging.log4j.LogManager;

@Component
public class DatabaseInit implements InitializingBean
{
    private final UserJpaRepository userRepo;
    private final PasswordEncoder encoder;

    public DatabaseInit(UserJpaRepository userRepo,
                        PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LogManager.getLogger("info").info("Initializing db");

        List<User> users = Arrays.asList(
                userRepo.findByUsername("green-goblin"),
                userRepo.findByUsername("magika_madoka"),
                userRepo.findByUsername("SuperEdgeLord45"),
                userRepo.findByUsername("Ziepler"),
                userRepo.findByUsername("Artemis"),
                userRepo.findByUsername("aura"),
                userRepo.findByUsername("planet15"),
                userRepo.findByUsername("theCorruptor"),
                userRepo.findByUsername("plague_doctor")
        );

        users.get(0).setPassword(this.encoder.encode("HnrSt456"));
        users.get(1).setPassword(this.encoder.encode("ChrMo456"));
        users.get(2).setPassword(this.encoder.encode("AlxMi456"));
        users.get(3).setPassword(this.encoder.encode("LauZa456"));
        users.get(4).setPassword(this.encoder.encode("ChlWi456"));
        users.get(5).setPassword(this.encoder.encode("MarFi456"));
        users.get(6).setPassword(this.encoder.encode("SelKe456"));
        users.get(7).setPassword(this.encoder.encode("ArcWe456"));
        users.get(8).setPassword(this.encoder.encode("GaeEs456"));

        System.out.println(this.encoder.encode("GaeEs456").length());

        users.forEach(this.userRepo::save);
        LogManager.getLogger("info").info(">>> !! DATABASE INITIALIZED !! <<<");
    }
}
