package social.androiddev.desktop.di

import org.koin.dsl.module
import social.androiddev.common.web.WebAuth
import social.androiddev.desktop.web.ExternalBrowserWebAuth

val desktopModule = module {
    factory<WebAuth> { ExternalBrowserWebAuth() }
}