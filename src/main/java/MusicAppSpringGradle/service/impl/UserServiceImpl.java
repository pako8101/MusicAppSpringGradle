package MusicAppSpringGradle.service.impl;

import MusicAppSpringGradle.models.entities.UserEntity;
import MusicAppSpringGradle.models.entities.UserRoleEntity;
import MusicAppSpringGradle.models.enums.UserRole;
import MusicAppSpringGradle.models.service.UserRegistrationServiceModel;
import MusicAppSpringGradle.repositories.UserRepository;
import MusicAppSpringGradle.repositories.UserRoleRepository;
import MusicAppSpringGradle.service.MusicDBUserService;
import MusicAppSpringGradle.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final MusicDBUserService musicDBUserService;

    public UserServiceImpl(UserRoleRepository userRoleRepository,
                           UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder,
                           MusicDBUserService musicDBUserService) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.musicDBUserService = musicDBUserService;
    }

    @Override
    public void seedUsers() {
        if (userRepository.count()==0){
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);
            userRoleRepository.saveAll(List.of(adminRole,userRole));

            UserEntity admin = new UserEntity().setUsername("admin")
                    .setFullName("Admin Adminov")
                    .setPassword(passwordEncoder.encode("topsecret"));
            UserEntity user = new UserEntity().setUsername("user")
                    .setFullName("Bai Ivan")
                    .setPassword(passwordEncoder.encode("topsecret"));
            admin.setRoles(List.of(adminRole,userRole));
            user.setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin,user));
        }

    }

    @Override
    public void registerAndLogin(UserRegistrationServiceModel serviceModel) {

        UserEntity newUser = modelMapper.map(serviceModel,UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(serviceModel.getPassword()));
UserRoleEntity userRole =
        userRoleRepository.findByRole(UserRole.USER).orElseThrow(()
                -> new IllegalStateException("User role not found" +
                ".Please seed the roles"));


        newUser.addRole(userRole);

        newUser = userRepository.save(newUser);
        UserDetails principal =
        musicDBUserService.loadUserByUsername(newUser.getUsername());


        Authentication authentication = new UsernamePasswordAuthenticationToken(
               principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public boolean userNameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
