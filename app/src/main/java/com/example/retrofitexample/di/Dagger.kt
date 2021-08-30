package com.example.retrofitexample.di

import com.example.retrofitexample.MainActivity
import com.example.retrofitexample.MainViewModel
import com.example.retrofitexample.MainViewModelFactory
import com.example.retrofitexample.api.StudentApi
import com.example.retrofitexample.common.Constants.BASE_STUDENT_URL
import com.example.retrofitexample.repository.StudentRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
}

@Module(includes = [Network::class, RepositoryModule::class])
class ApplicationModule {

    @Provides
    fun provideMainViewModel(repository: StudentRepository): MainViewModel {
        return MainViewModelFactory(repository).create(MainViewModel::class.java)
    }
}


@Module
class RepositoryModule

@Module
class Network {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_STUDENT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideStudentApi(retrofit: Retrofit): StudentApi {
        return retrofit.create(StudentApi::class.java)
    }

}


