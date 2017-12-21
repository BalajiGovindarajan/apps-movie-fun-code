package org.superbiz.moviefun.moviefunapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfiguration {

    @Value("${movies.url}") String moviesUrl;
    @Value("${albums.url}") String albumsUrl;

    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MoviesClient moviesClient(RestTemplate restTemplate) {
        return new MoviesClient(moviesUrl, restTemplate);
    }


    @Bean
    public AlbumsClient albumsClient(RestTemplate restTemplate) {
        return new AlbumsClient(albumsUrl, restTemplate);
    }

}
