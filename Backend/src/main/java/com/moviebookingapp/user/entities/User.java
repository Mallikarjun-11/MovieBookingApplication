package com.moviebookingapp.user.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//@Table(name="users",uniqueConstraints = @UniqueConstraint(columnNames = {"emailId", "userName","contactNumber"}))
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotEmpty(message = "Please enter a valid First Name")
    private String firstName;
    @NotEmpty(message = "Please enter a valid Last Name")
    private String lastName;
// @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?``{|}~^.-]+@[a-zA-Z0-9.-]+$")

    @NotEmpty(message = "Please enter a valid Email")
    @Column(unique = true)
    private String emailId;
    @NotEmpty(message = "Please enter a valid User Name")
    @Column(unique = true)
    private String userName;
    @NotEmpty(message = "Please enter a valid Password")
    private String password;
    @NotEmpty(message = "Please enter a valid Confirm Password")
    private String confirmPassword;
    //@NotEmpty(message = "Please enter a valid First Name")
    @Pattern(regexp = "^\\d{10}$",message = "Please enter a valid contact number")
    @Column(unique = true)
    private String contactNumber;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name="USER_ROLES",
            joinColumns = @JoinColumn(
                    name="USER_ID"),
            inverseJoinColumns = @JoinColumn(
                    name="ROLE_ID")
    )
    private Set<Role> roles;

}





//package com.moviebookingapp.user.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import javax.persistence.*;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Entity
//@Table(name="users",uniqueConstraints = @UniqueConstraint(columnNames = {"email","userName"}))
//public class User {
//    //implements UserDetails
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//    @Column(nullable = false)
//    private String firstName;
//    @Column(nullable = false)
//    private String lastName;
//    @Column(nullable = false)
//    private String email;
//    @Column(nullable = false)
//    private String userName;
//    @Column(nullable = false)
//    private String password;
//    @Column(nullable = false)
//    private String confirmPassword;
//    @Column(nullable = false,unique = true)
//    private String contactNumber;
//
//    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @JoinTable(
//            name="USER_ROLES",
//            joinColumns = @JoinColumn(
//                    name="USER_ID"),
//            inverseJoinColumns = @JoinColumn(
//                    name="ROLE_ID")
//    )
//    private Set<Role> roles;
//
//
//
//
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        Set<Authority> set=new HashSet<>();
////        this.roles.forEach(role -> {
////            set.add(new Authority(role.getRoleName()));
////        });
////        return set;
////    }
////
////    @Override
////    public String getUsername() {
////        return username;
////    }
////
////    @Override
////    public boolean isAccountNonExpired() {
////        return true;
////    }
////
////    @Override
////    public boolean isAccountNonLocked() {
////        return true;
////    }
////
////    @Override
////    public boolean isCredentialsNonExpired() {
////        return true;
////    }
////
////    @Override
////    public boolean isEnabled() {
////        return true;
////    }
//}
