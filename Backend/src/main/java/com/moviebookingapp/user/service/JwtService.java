package com.moviebookingapp.user.service;


import com.moviebookingapp.user.entities.JwtRequest;
import com.moviebookingapp.user.entities.JwtResponse;
import com.moviebookingapp.user.entities.User;
import com.moviebookingapp.user.exception.ResourceNotFoundException;
import com.moviebookingapp.user.repositories.UserRepository;
import com.moviebookingapp.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName=jwtRequest.getUserName();
        String password=jwtRequest.getPassword();
        authenticate(userName,password);
        final UserDetails userDetails=loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        User user = userRepository.findByUserName(userName).get();

        //return new JwtResponse(user,newGeneratedToken);
        return new JwtResponse(newGeneratedToken);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).get();
        if(user!=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    getAuthorities(user)
            );
        }else{
            throw new UsernameNotFoundException("Username is not valid");
        }
    }

    private Set getAuthorities(User user){
        Set authorities=new HashSet();
        user.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authorities;
    }

   private void authenticate(String userName,String password) throws Exception {
        User user=userRepository.findByUserName(userName).orElseThrow(
                ()->new UsernameNotFoundException(userName)
        );

               try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
        }catch (DisabledException e){
            throw new Exception("User is disabled");
        }
        catch (BadCredentialsException e){
            throw new Exception("Bad credentials from User");
        }catch (Exception e){
                   throw new Exception("Bad credentials from User");
               }
    }
}
