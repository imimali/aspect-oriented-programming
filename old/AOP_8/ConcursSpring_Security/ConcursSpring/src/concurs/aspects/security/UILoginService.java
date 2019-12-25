package concurs.aspects.security;



import javax.swing.JOptionPane;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import ajia.security.LoginService;

public class UILoginService implements LoginService {

	public Authentication getAuthenticationToken() {
		String userName=JOptionPane.showInputDialog("Username:");
		String password=JOptionPane.showInputDialog("Password:");
		
		return new UsernamePasswordAuthenticationToken(userName, password);
	}

}
