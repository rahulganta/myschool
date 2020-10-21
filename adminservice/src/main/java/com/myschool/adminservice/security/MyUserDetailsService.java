package com.myschool.adminservice.security;

import com.myschool.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private Map<String,User> users = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<com.myschool.adminservice.model.User> user = userRepository.findById(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Username:" + userName +  " not found" ));

        return user.map(MyUserDetails::new).get();

        /*return users.get(userName);*/
    }

    @PostConstruct
    public void init() {
        GrantedAuthority studentGrantedAuthority = new SimpleGrantedAuthority("ROLE_STUDENT");
        GrantedAuthority parentGrantedAuthority = new SimpleGrantedAuthority("ROLE_PARENT");
        GrantedAuthority teacherGrantedAuthority = new SimpleGrantedAuthority("ROLE_TEACHER");
        GrantedAuthority schoolAdminGrantedAuthority = new SimpleGrantedAuthority("ROLE_SCHOOLADMIN");
        GrantedAuthority franchiseAdminGrantedAuthority = new SimpleGrantedAuthority("ROLE_FRANCHISEADMIN");
        GrantedAuthority superAdminGrantedAuthority = new SimpleGrantedAuthority("ROLE_SUPERADMIN");

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(studentGrantedAuthority);
        roles.add(superAdminGrantedAuthority);

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        //you can create custom user class to track custom attributes.
        User superAdmin = new User("admin", "admin", roles);
        this.users.put("admin", superAdmin);

        roles = new ArrayList<>();
        roles.add(studentGrantedAuthority);
        User student = new User("student", "Password1!", roles);
        this.users.put("student", student);

        roles = new ArrayList<>();
        roles.add(parentGrantedAuthority);
        User parent = new User("parent", "Password1!", roles);
        this.users.put("parent", parent);

        roles = new ArrayList<>();
        roles.add(teacherGrantedAuthority);
        User teacher = new User("teacher", "Password1!", roles);
        this.users.put("teacher", teacher);

        roles = new ArrayList<>();
        roles.add(schoolAdminGrantedAuthority);
        User schoolAdmin = new User("schooladmin", "Password1!", roles);
        this.users.put("schooladmin", schoolAdmin);

        roles = new ArrayList<>();
        roles.add(franchiseAdminGrantedAuthority);
        User franchiseAdmin = new User("franchiseadmin", "Password1!", roles);
        this.users.put("franchiseadmin", franchiseAdmin);


    }
}
