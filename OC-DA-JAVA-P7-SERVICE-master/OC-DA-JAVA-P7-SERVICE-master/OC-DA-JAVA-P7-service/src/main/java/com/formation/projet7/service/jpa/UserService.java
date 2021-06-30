package com.formation.projet7.service.jpa;

import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PassThroughInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.repository.UserRepo;
import com.formation.projet7.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public List<Utilisateur> listerUsers() {

		List<Utilisateur> users = userRepo.findAll();
		return users;
	}

	@Override
	public Utilisateur obtenirUser(Integer id) {

		Utilisateur user = userRepo.getOne(id);
		return user;
	}

	@Override
	public Utilisateur obtenirUser(String string) {


		// User user = userRepo.findByIdentity(string);

		return null;
	}

	@Override
	public Utilisateur obtenirUserParEmail(String email) {

		Utilisateur user = userRepo.findByUsername(email);
		return user;
	}
	
	

	@Override
	public void ajouterUser(Utilisateur user) {

		String password = encoder.encode(user.getPassword());
		user.setPassword(password);
		userRepo.save(user);

	}

	@Override
	public void modifierUser(Utilisateur user) {
		userRepo.save(user);

	}

	@Override
	public void supprimerUser(Utilisateur user) {
		userRepo.delete(user);

	}

	public Utilisateur obtenirUserParlogin(String username, String password) {
		
		Utilisateur utilisateur = userRepo.findByUsername(username);
		if (encoder.matches(password, utilisateur.getPassword())){
			
			return utilisateur;

		} else

			return null;

	}

}
