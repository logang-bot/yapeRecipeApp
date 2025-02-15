# AmazingRecipeApp

This app has been built using JetpackCompose

## Libraries installed for this app

- Navigation for Compose using type safe navigation
- Coil, to download images from internet
- Retrofit, to make http requests
- Maps for Compose, to display maps

## About the architecture

- I'm using MVVM since it is the architecture that I'm more familiar with
- I'm using manual DI to simplify the process of create instances for certain classes. I'm aware that this can be farly improved by using libraries such as Dagger/Hilt
- I'm aware that having private API KEYS is unsafe and dangerous (talking about the key for google maps). This can fixed by implementing the [Secrets Gradle Plugin](https://developers.google.com/maps/documentation/android-sdk/config#step_3_add_your_api_key_to_the_project)

I ran out of time, so I didn't implement Unit tests, but the steps I'd follow would be:

- Create a fake data source
- Create a fake api service in order to not make real network calls
- Create a fake repository
- Create a _test_ file for each viewModel and repository
