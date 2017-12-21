package org.superbiz.moviefun.moviefunapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.moviefunapi.Album;
import org.superbiz.moviefun.moviefunapi.MovieInfo;
import org.superbiz.moviefun.moviefunapi.MovieFixtures;
import org.superbiz.moviefun.moviefunapi.MoviesClient;


import java.util.Map;

@Controller
public class HomeController {

    private final MoviesClient moviesClient;
    private final AlbumsClient albumsClient;
    private final MovieFixtures movieFixtures;
    private final AlbumFixtures albumFixtures;

    public HomeController(MoviesClient moviesBean, AlbumsClient albumsClient, MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
        this.moviesClient = moviesBean;
        this.albumsClient = albumsClient;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (MovieInfo movie : movieFixtures.load()) {
            moviesClient.addMovie(movie);
        }

        for (Album album : albumFixtures.load()) {
            albumsClient.addAlbum(album);
        }

        model.put("movies", moviesClient.getMovies());
        model.put("albums", albumsClient.getAlbums());

        return "setup";
    }

    @GetMapping("/albums")
    public String getAlbums(Map<String, Object> model)
    {
        model.put("albums",albumsClient.getAlbums());
        return "albums";
    }

    @GetMapping("moviefun")
    public String getMovies(Map<String, Object> model)
    {
        System.out.println("Get Movies==============");
        model.put("movies", moviesClient.getMovies());
        return "moviefun";
    }

}
