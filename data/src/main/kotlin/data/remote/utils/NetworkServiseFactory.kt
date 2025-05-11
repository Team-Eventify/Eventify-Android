package data.remote.utils
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object NetworkServiceFactory {

    fun <Service>getApi(
        baseUrl: String,
        service: Class<Service>,
        authenticator: Authenticator? = null
    ): Service = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient(authenticator))
        .build()
        .create(service)

    fun getOkHttpClient(
        authenticator: Authenticator?
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
        authenticator?.let {
            builder.authenticator(it)
        }
        return builder
            .addInterceptor(interceptor)
            .build()
    }
}