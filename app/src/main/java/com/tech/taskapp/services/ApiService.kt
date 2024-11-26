import com.tech.taskapp.models.NewsResponse
import com.tech.taskapp.utils.Utils.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//interface NewsApiService {
//    @GET("top-headlines?sources=bbc-news&apiKey=0a51f1bf79244558b69f2dff9c2f4084")
//    suspend fun getTopHeadlines(): Response<NewsResponse>
//}


interface Service {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources")
        sources: String = "bbc-news",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}

