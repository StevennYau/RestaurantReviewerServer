package com.project.RestaurantReviewer.service;
import com.project.RestaurantReviewer.controller.UserController;
import com.project.RestaurantReviewer.entity.MyUserDetails;
import com.project.RestaurantReviewer.entity.User;
import com.project.RestaurantReviewer.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private static Logger log = LoggerFactory.getLogger(UserController.class);



    public String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // log.debug("authentication="+authentication);
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            String currentUserName = authentication.getName();
            //log.debug("currentUserName="+currentUserName);
            return currentUserName;
        }
        return null;
    }

    public String getLoggedInId(String userName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // log.debug("authentication="+authentication);
        Optional<User> test = userRepository.findIdByUser(userName);
        log.info("ID ID ID");
        log.info("ID ID ID");
        log.info("ID ID ID");
        log.info("ID ID ID");
        log.info(String.valueOf(test));
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            String currentUserName = authentication.getName();
            //log.debug("currentUserName="+currentUserName);
            return currentUserName;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        System.out.println("USERDETAILS: " + user.map(MyUserDetails::new).get());

        return user.map(MyUserDetails::new).get();
    }
}
