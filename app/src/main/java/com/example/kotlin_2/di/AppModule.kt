package com.example.kotlin_2.di
/*
import android.app.Application
import androidx.room.Room
import com.example.kotlin_2.data.KeepFitDatabase
import com.example.kotlin_2.data.repository.HistoryRepository
import com.example.kotlin_2.data.repository.HistoryRepositoryImpl


object AppModule {
//    @Provides
//    @Singleton
    fun provideDatabase(app:Application): KeepFitDatabase{
        return Room.databaseBuilder(
            app,
            KeepFitDatabase::class.java,
            "kf_db"
        ).build()
    }
//    @Provides
//    @Singleton
    fun provideHistoryRepository(db:KeepFitDatabase):HistoryRepository{
        return HistoryRepositoryImpl(db.historyDao)
    }
}
*/