package ws.personnel.tax.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ws.personnel.tax.configuration.ServiceBase;
import ws.personnel.tax.configuration.ApplicationConstant;
import ws.personnel.tax.entities.User;
import ws.personnel.tax.entities.object.SecuityUserObj;
import ws.personnel.tax.entities.object.UserObj;
import ws.personnel.tax.entities.objectC.GroupMenuObjC;
import ws.personnel.tax.repository.UserRepository;
import ws.personnel.tax.utils.JwtUtil;

@Service
public class LoginService extends ServiceBase {

	@Autowired
	private MenuService menuService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MenuManageService menuManageService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;
	
	private HttpServletRequest req;
	
	@Autowired
	public LoginService(HttpServletRequest req) {
		this.req = req;
	}

	public GroupMenuObjC listMenu(String userId) throws Exception {

		GroupMenuObjC groupMenuObjC = new GroupMenuObjC();
		groupMenuObjC = menuManageService.permissionMenu(menuService.menuFromUserPermission(userId));

		return groupMenuObjC;
	}

	public SecuityUserObj verifyLogin(UserObj userObj) throws Exception {
		SecuityUserObj secuityUserObj = new SecuityUserObj();
		User user = new User();
		try {
			user = userRepository.findUserName(userObj.getUserName());
			if (null != user.getId()) {
				secuityUserObj.setName(userObj.getUserName());
				secuityUserObj.setToken(setToken(userObj));
				secuityUserObj.setTypeUser(user.getTypeUser());
				secuityUserObj.setIsLogin(true);
			} else {
				secuityUserObj.setIsLogin(false);
			}

		} catch (Exception e) {
			logger.error("Error ", e);
			throw e;
		}
		return secuityUserObj;
	}

	public String setToken(UserObj userObj) {
		String jwt = null;
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userObj.getUserName(), userObj.getPassword()));
			 jwt = jwtUtil.generateToken(userObj.getUserName());
		} catch (BadCredentialsException e) {
			// TODO: handle exception
		}
		return jwt;
	}
}
