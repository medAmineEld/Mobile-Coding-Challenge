package challenge.coding.mobile.mobile_coding_challenge.api;

import challenge.coding.mobile.mobile_coding_challenge.model.MostStarredRepo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("/search/repositories")
    Call<MostStarredRepo> getMostStarredRepo(@Query("q") String q,
                                             @Query("sort") String sort,
                                             @Query("order") String order,
                                             @Query("page") int page);
}
//https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc
//https://api.github.com/search/repositories?q=created:>2018-11-26
//https://api.github.com/search/repositories?q=created:>2017-10-22