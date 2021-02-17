package com.project.RestaurantReviewer.service;
import com.project.RestaurantReviewer.MyUserDetails;
import com.project.RestaurantReviewer.entity.User;
import com.project.RestaurantReviewer.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


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

    /*public String getLoggedInId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // log.debug("authentication="+authentication);
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            String currentUserName = authentication.getID();
            //log.debug("currentUserName="+currentUserName);
            return currentUserName;
        }
        return null;
    }*/

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        System.out.println("USERDETAILS: " + user.map(MyUserDetails::new).get());

        return user.map(MyUserDetails::new).get();
    }
}
