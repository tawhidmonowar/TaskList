package com.tawhid.tasklist.core.di

import androidx.room.Room
import com.tawhid.tasklist.data.local.database.TaskDatabase
import com.tawhid.tasklist.data.repository.TaskRepositoryImpl
import com.tawhid.tasklist.domain.repository.TaskRepository
import com.tawhid.tasklist.domain.usecase.AddTaskUseCase
import com.tawhid.tasklist.domain.usecase.AddToFavoriteUseCase
import com.tawhid.tasklist.domain.usecase.DeleteTaskUseCase
import com.tawhid.tasklist.domain.usecase.GetAllTaskUseCase
import com.tawhid.tasklist.domain.usecase.GetFavoriteTaskUseCase
import com.tawhid.tasklist.domain.usecase.GetTaskByIDUseCase
import com.tawhid.tasklist.domain.usecase.RemoveFromFavoriteUseCase
import com.tawhid.tasklist.domain.usecase.UpdateReminderUseCase
import com.tawhid.tasklist.presentation.screen.add_task.AddTaskViewModel
import com.tawhid.tasklist.presentation.screen.detail.DetailViewModel
import com.tawhid.tasklist.presentation.screen.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AndroidKoinModules = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            TaskDatabase::class.java,
            TaskDatabase.NAME
        ).build()
    }

    single {
        get<TaskDatabase>().taskDao()
    }
    single<TaskRepository> { TaskRepositoryImpl(get()) }

    factory { GetAllTaskUseCase(get()) }
    factory { GetFavoriteTaskUseCase(get()) }
    factory { AddTaskUseCase(get()) }
    factory { GetTaskByIDUseCase(get()) }
    factory { AddToFavoriteUseCase(get()) }
    factory { RemoveFromFavoriteUseCase(get()) }
    factory { DeleteTaskUseCase(get()) }
    factory { UpdateReminderUseCase(get()) }

    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { AddTaskViewModel(get()) }
    viewModel { DetailViewModel(get(), get(), get(), get()) }
}