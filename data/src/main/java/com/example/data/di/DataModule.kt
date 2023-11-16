package com.example.data.di

import com.example.data.mapper.booking.BookingMapper
import com.example.data.mapper.hotel_info.HotelMapper
import com.example.data.mapper.room_hotel.RoomsMapper
import com.example.data.network.ApiService
import com.example.data.repository.HotelRepositoryImpl
import com.example.domain.repository.HotelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val BASE_URL = " https://run.mocky.io/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideHotelRepository(
        api: ApiService,
        hotelMapper: HotelMapper,
        roomsMapper: RoomsMapper,
        bookingMapper: BookingMapper,
    ): HotelRepository {
        return HotelRepositoryImpl(api, hotelMapper, roomsMapper, bookingMapper)
    }
}