package org.superbiz.moviefun.moviefunapi;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.superbiz.moviefun.moviefunapi.MovieInfo;

import java.util.List;
import static org.springframework.http.HttpMethod.GET;


public class MoviesClient {

    private String movieUrl;
    private RestOperations restOperations;
    private RestTemplate restTemplate;

    private static ParameterizedTypeReference<List<MovieInfo>> movieListType = new ParameterizedTypeReference<List<MovieInfo>>() {
    };
    public MoviesClient(String movieUrl, RestOperations restOperations)
    {
        this.movieUrl = movieUrl;
        this.restOperations=restOperations;

    }

    /*public MoviesClient(String movieUrl, RestTemplate restTemplate)
    {
        this.movieUrl = movieUrl;
        this.restTemplate=restTemplate;

    }*/

    public void addMovie( MovieInfo movieInfo)
    {
        System.out.println("MovieUrl==============: " + movieUrl);
        System.out.println("MovieInfo==============: " + movieInfo.getTitle());
        restOperations.postForEntity(movieUrl, movieInfo, MovieInfo.class);
        //restTemplate.postForEntity(movieUrl, movieInfo, MovieInfo.class);

    }



    public void deleteMovieId (Long movieId)
    {
        restOperations.delete(movieUrl + "/" + movieId);

    }

    public int countAll() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(movieUrl + "/count");

        System.out.println("============MOVIE URL========" + movieUrl+"/count");
        //return restOperations.getForObject(movieUrl + "/count", Integer.class);
        return restOperations.getForObject(builder.toUriString() , Integer.class);


    }

    public int count(String field, String key) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(movieUrl + "/count")
                .queryParam("field", field)
                .queryParam("key", key);

        return restOperations.getForObject(builder.toUriString(), Integer.class);
    }

    public List<MovieInfo> findAll(int start, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize);

        return restOperations.exchange(builder.toUriString(), GET, null, movieListType).getBody();
    }

    public List<MovieInfo> findRange(String field, String key, int start, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .queryParam("field", field)
                .queryParam("key", key)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize);

        return restOperations.exchange(builder.toUriString(), GET, null, movieListType).getBody();
    }

    public List<MovieInfo> getMovies() {

        System.out.println("Movie url Get Movies============= " + movieUrl);
        return restOperations.exchange(movieUrl, GET, null, movieListType).getBody();
    }



}
