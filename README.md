NIT3213 Dashboard App

What it does

Three screens. Login takes a student ID and first name and gets a keypass back from the API. The dashboard uses that keypass to fetch a list of science topics. Tapping one opens a details screen with all six fields.

Architecture

MVVM. Activities handle the UI and clicks only. ViewModels hold the state and talk to the repository, which is the only layer that touches Retrofit. ViewModels expose state through StateFlow and Activities collect it in lifecycleScope, so the UI updates as soon as data arrives.

Dependency injection

Koin handles this, so no class builds its own dependencies. AppModule declares the ApiService, repository and both ViewModels, and Koin starts in MainApplication. It also makes testing easier, since a fake repository can be passed straight into a ViewModel.

Error handling

Wrong credentials show a message instead of crashing, and the error resets before each attempt so it reappears on repeat failures.

Libraries used

Retrofit 2.11.0, Moshi 1.14.0, OkHttp logging-interceptor 4.10.0, Koin 3.5.6, RecyclerView 1.3.2, JUnit 4, MockK 1.13.12, kotlinx-coroutines-test

How to run

Clone the repo, open in Android Studio, let Gradle sync, then run on an emulator with API 24 or above. Log in with your student ID and first name. Needs an internet connection.

Testing

9 unit tests across both ViewModels. MockK replaces the repository so nothing hits the network. They cover successful and failed responses, empty states, and that typed credentials reach the repository unchanged.

Run with: gradlew testDebugUnitTest


MainActivity — login
DashboardActivity — displays the list
DetailsActivity — displays item details
LoginViewModel — handles login and stores the keypass
DashboardViewModel — fetches and holds the list
EntityAdapter — converts the list into rows
ApiService — defines API endpoints
RetrofitClient — configures the server and Retrofit
LoginRepository — handles all server calls
Entity, LoginResponse, DashboardResponse — data models
AppModule — configures Koin dependencies
MainApplication — starts Koin
Tests — 9 ViewModel unit tests in app/src/test/

