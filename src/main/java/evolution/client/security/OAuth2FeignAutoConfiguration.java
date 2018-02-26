package evolution.client.security;

import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

@Configuration
@ConditionalOnClass({ Feign.class })
@ConditionalOnProperty(value = "feign.oauth2.enabled", matchIfMissing = true)
public class OAuth2FeignAutoConfiguration {

    @Bean
    @ConditionalOnBean(OAuth2ClientContext.class)
    public RequestInterceptor oauth2FeignRequestInterceptor(@Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2FeignRequestInterceptor(oauth2ClientContext);
    }
}
