import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.taskapp.models.NewsResponse
import com.tech.taskapp.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()

    val newsResponse: MutableLiveData<NewsResponse?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun fetchTopHeadlines() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response: Response<NewsResponse> = repository.getTopHeadlines()
                if (response.isSuccessful && response.body() != null) {
                    newsResponse.value = response.body()
                } else {
                    errorMessage.value = "Failed to fetch data: ${response.message()}"
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }
}
