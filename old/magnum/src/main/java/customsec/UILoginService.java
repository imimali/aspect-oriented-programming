package customsec;



import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import security.LoginService;

import javax.swing.*;

public class UILoginService implements LoginService {

	public Authentication getAuthenticationToken() {

		String userName=JOptionPane.showInputDialog("Username:");
		String password=JOptionPane.showInputDialog("Password:");
		
		return new UsernamePasswordAuthenticationToken(userName, password);
	}

}
