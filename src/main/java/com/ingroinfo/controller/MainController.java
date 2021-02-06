package com.ingroinfo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ingroinfo.bean.UserDetail;

@Controller
public class MainController {
	static final String LINKEDIN_CLIENT_ID = "S3FsudFA3TJdNK1002fzp9TaD";
	static final String LINKEDIN_CLIENT_SECRET = "e43vKU3eQfPMpbLcLCoFnj526K96KsXxPqIRusyZCwVIeGuq3x";
	static final String LINKEDIN_CALLBACK_URL = "http://localhost:8082/social/TwitterLogin";
	
	
	
	static final String GOOGLE_CLIENT_ID = "905465315511-kcppd18de6tkvhnr6nda9uiune6cen2u.apps.googleusercontent.com";
	static final String GOOGLE_CLIENT_SECRET = "oIGXG8LwJ_x4z5eMREaMzdaJ";
	static final String GOOGLE_CALLBACK_URL = "http://localhost:8082/social/GoogleLogin";

	TwitterConnectionFactory tconf=new TwitterConnectionFactory(LINKEDIN_CLIENT_ID, LINKEDIN_CLIENT_SECRET);
	OAuth1Operations oauthoperation=tconf.getOAuthOperations();
	
	
	GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory(GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET);
	OAuth2Operations googleOAuth2Operations = googleConnectionFactory.getOAuthOperations();

	@RequestMapping(value={"/TwitterLogin"}, method=RequestMethod.POST)
	public String twitterLoginPost(HttpServletRequest request, HttpServletResponse response){
		OAuth1Parameters oauthparms=new OAuth1Parameters();
		oauthparms.setCallbackUrl(LINKEDIN_CALLBACK_URL);
		OAuthToken oauthtoken=oauthoperation.fetchRequestToken(LINKEDIN_CALLBACK_URL, oauthparms);
		String authorizeurl=oauthoperation.buildAuthorizeUrl(oauthtoken.getValue(), oauthparms);
		return "redirect:"+authorizeurl;
		
		
	}
	
	@RequestMapping(value={"/TwitterLogin"}, method=RequestMethod.GET)
	public String twitterLoginGet(HttpServletRequest request, HttpServletResponse response,Model model){
		String token=request.getParameter("oauth_token");
		String verifier=request.getParameter("oauth_verifier");
		
		if(token==null || token.isEmpty() || verifier ==  null || verifier.isEmpty())
		{
			return "redirect:/login";
		}
		OAuthToken oauttoken=new OAuthToken(token, "");
		OAuthToken exchangeForAccessToken = oauthoperation.exchangeForAccessToken(new AuthorizedRequestToken(oauttoken, verifier), null);
		
		Connection<Twitter> createConnection = tconf.createConnection(exchangeForAccessToken);
		if(createConnection==null)
		{
			return "redirect:/login";
		}
		TwitterProfile userProfile = createConnection.getApi().userOperations().getUserProfile();
		
		model.addAttribute("profile",userProfile.getName());
		System.out.println("profile name"+userProfile.getName());
		UserDetail  user=new UserDetail();
		user.setUsername(String.valueOf(userProfile.getId()));
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword(), authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/home";
	}
	
	
	@RequestMapping(value={"/GoogleLogin"}, method=RequestMethod.POST)
	public String googleLoginPost(HttpServletRequest request, HttpServletResponse response){
		OAuth2Parameters params = new OAuth2Parameters();
		params.setScope("profile");
		params.setRedirectUri(GOOGLE_CALLBACK_URL);
		String authoriseUrl = googleOAuth2Operations.buildAuthorizeUrl(params);
		return "redirect:" + authoriseUrl;
	}
	
	@RequestMapping(value={"/GoogleLogin"}, method=RequestMethod.GET)
	public String googleLoginGet(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		if(code == null || code.isEmpty()){
			return "redirect:/Login";
		}
		AccessGrant accessGrant = googleOAuth2Operations.exchangeForAccess(code, GOOGLE_CALLBACK_URL, null);
		Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
		if(connection == null){
			return "redirect:/Login";
		}
		Person person = connection.getApi().plusOperations().getGoogleProfile();
		System.out.println(person.getDisplayName());
		
		UserDetail  user=new UserDetail();
		user.setUsername(person.getId());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword(), authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/home";
	}
	
	
	
	@GetMapping(value = {"/login"})
	public String login()
	{
		return"login";
	}
	@GetMapping(value = {"/home"})
	public String home()
	{
		return"home";
	}
}
