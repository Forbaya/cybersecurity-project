package sec.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.controller.DatabaseManager;
import sec.project.domain.User;
import sec.project.repository.UserRepository;
import sec.project.repository.UserRoleRepository;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    private DatabaseManager databaseManager;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = databaseManager.getUsers();
        User user = databaseManager.findUserByUsername(username, users);
        if (user == null) {
            throw new UsernameNotFoundException("Couldn't find username " + username);
        }

        List<String> userRoles = databaseManager.findUserRolesByUser(user.getId());
        return new CustomUserDetails(user, userRoles);
    }
}
