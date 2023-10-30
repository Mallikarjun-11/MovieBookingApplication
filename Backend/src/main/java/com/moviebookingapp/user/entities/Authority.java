package com.moviebookingapp.user.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

        private String authority;

		@Override
		public String getAuthority() {
			// TODO Auto-generated method stub
			return null;
		}


    }

