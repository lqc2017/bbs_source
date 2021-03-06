/**
 * 全琛
 * 2017年5月4日
 */
package grp3022.bbs.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import grp3022.bbs.dao.AccountMapper;
import grp3022.bbs.po.Account;

/**
 * @author 全琛
 *
 */
public class AuthorityServiceImpl implements UserDetailsService {
	@Autowired
	private AccountMapper accountDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("service");
		Account account = accountDao.selectByPrimaryKey(username);

		String userName = account.getUsername();
		String passWord = account.getPassword();
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		if (account != null) {
			short role = account.getRole();

			switch (role) {
			case 10:
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				account.setLatestLogin(new Date());
				accountDao.updateByPrimaryKey(account);
			}
		}
		return new User(userName, passWord, authorities);
	}

}
