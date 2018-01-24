package priv.yimeng.demo.configuration.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import priv.yimeng.demo.persistence.entity.Authorities;

import javax.sql.DataSource;

/**
 * Desc
 *
 * @author yimeng
 * @date 2017-12-09
 */
@Configuration
@AutoConfigureAfter(CustomAuthenticationEntryPoint.class)
public class OAuth2Configuration {

    @Configuration
    @EnableResourceServer
    public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
        private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

        @Autowired
        public ResourceServerConfiguration(CustomAuthenticationEntryPoint customAuthenticationEntryPoint, CustomLogoutSuccessHandler customLogoutSuccessHandler) {
            this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
            this.customLogoutSuccessHandler = customLogoutSuccessHandler;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
                    .and()
                    .logout()
                    .logoutUrl("/oauth/logout")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .permitAll()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/hello").permitAll()
                    .antMatchers("/secure/**").authenticated();
        }
    }

    @Configuration
    @EnableAuthorizationServer
    @PropertySource("classpath:oauth.properties")
    @ConfigurationProperties(prefix = "authentication.oauth")
    public static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        private String clientId;
        private String secret;
        private String tokenValidityInSeconds;

        @Autowired
        private DataSource dataSource;

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient(clientId)
                    .scopes("read", "write")
                    .authorities(Authorities.ROLE_ADMIN.name(), Authorities.ROLE_USER.name())
                    .authorizedGrantTypes("password", "refresh_token")
                    .secret(secret)
                    .accessTokenValiditySeconds(Integer.parseInt(tokenValidityInSeconds));
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public void setTokenValidityInSeconds(String tokenValidityInSeconds) {
            this.tokenValidityInSeconds = tokenValidityInSeconds;
        }
    }

}
