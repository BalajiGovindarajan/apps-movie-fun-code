package org.superbiz.moviefun.moviefunapi;

import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {

    private String albumsUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<Album>> albumListType = new ParameterizedTypeReference<List<Album>>() {
    };

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }

    public List<Album> index() {
        return restOperations.exchange(albumsUrl, GET, null, albumListType).getBody();
    }

    public ResponseEntity<Album> details(long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(albumsUrl)
                .queryParam("id", id);

        return restOperations.exchange(builder.toUriString(), GET, null,Album.class);
    }


    public void addAlbum(Album album) {
        System.out.println("Album URL:=========" +albumsUrl + "Album Details: " + album.getArtist());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(albumsUrl);
        restOperations.postForEntity(builder.toUriString(), album, Album.class);

    }


    public void uploadCover(Long albumId, String file)
    {
        restOperations.postForEntity(albumsUrl +"/albumId",String.class, Album.class);

    }

  /*  public void getCover(Long albumId)
    {
        restOperations.getForEntity(albumsUrl + "/albumId", HttpEntity <byte[]>, Album.class)

    }
*/

    public List<Album> getAlbums() {
        //restOperations.getForEntity(albumsUrl, List<Album>, Album.class);
        return restOperations.exchange(albumsUrl, GET, null, albumListType).getBody();
    }



}