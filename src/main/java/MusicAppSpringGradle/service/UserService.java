package MusicAppSpringGradle.service;

import MusicAppSpringGradle.models.service.UserRegistrationServiceModel;

public interface UserService {

    void seedUsers();

    void registerAndLogin(UserRegistrationServiceModel serviceModel);
}
