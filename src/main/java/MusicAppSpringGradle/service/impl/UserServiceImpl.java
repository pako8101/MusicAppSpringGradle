package MusicAppSpringGradle.service.impl;

import MusicAppSpringGradle.models.entities.UserEntity;
import MusicAppSpringGradle.models.entities.UserRoleEntity;
import MusicAppSpringGradle.models.enums.UserRole;
import MusicAppSpringGradle.models.service.UserRegistrationServiceModel;
import MusicAppSpringGradle.repositories.UserRepository;
import MusicAppSpringGradle.repositories.UserRoleRepository;
import MusicAppSpringGradle.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRoleRepository userRoleRepository,
                           UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedUsers() {
        if (userRepository.count()==0){
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);
            userRoleRepository.saveAll(List.of(adminRole,userRole));

            UserEntity admin = new UserEntity().setUsername("admin")
                    .setFullname("Admin Adminov")
                    .setPassword(passwordEncoder.encode("topsecret"));
            UserEntity user = new UserEntity().setUsername("user")
                    .setFullname("Bai Ivan")
                    .setPassword(passwordEncoder.encode("topsecret"));
            admin.setRoles(List.of(adminRole,userRole));
            user.setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin,user));
        }

    }

    @Override
    public void registerAndLogin(UserRegistrationServiceModel serviceModel) {
        throw new UnsupportedOperationException();
    }
}
