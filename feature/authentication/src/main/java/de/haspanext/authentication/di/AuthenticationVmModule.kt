package de.haspanext.authentication.di

import de.haspanext.authentication.viewmodel.LoginViewModel
import de.haspanext.authentication.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {LoginViewModel() }
    viewModel { RegisterViewModel()}
}