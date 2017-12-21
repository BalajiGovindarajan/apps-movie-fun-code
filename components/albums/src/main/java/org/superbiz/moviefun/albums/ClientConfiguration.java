package org.superbiz.moviefun.albums;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.superbiz.moviefun.blobstore.BlobStore;

@Configuration
public class ClientConfiguration {

    /*@Value("${movies.url}") String moviesUrl;

    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
*/

}

