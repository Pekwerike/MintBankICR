# MintBankICR
Mint Bank Intelligent Card Reader (MintBankICR) utilizes cutting-edge machine learning algorithm to perform optical character recognition to extract out metadata from a user credit/debit card.

## How it works?
MintBankICR employs Google’s machine learning expertise on mobile to perform text recognition on the scanned card. Then MintBankICR extracts the card number and performs a 
network request to https://binlist.net/ to get information about the card. [Watch video preview](https://www.youtube.com/watch?v=VQSc8kc2JLw)

## Architecture pattern
The app uses Google's recommended Model-View-ViewModel pattern with unidirectional data flow. 

### App Layers
* UI 
* Network 
* Test
* Dependency Injection 
* Optical Character Recognition + Camera Configurations 
* Repository
* ViewModel
* Model 
* Concurrency 

* ### UI layer 
MintBankICR's user interface was built using Android’s modern toolkit for building native UI, Jetpack Compose + Android's traditional established xml view system.

  <img src="https://user-images.githubusercontent.com/43956851/110859787-43546900-82bc-11eb-9ad2-a882a515f580.png" width="320" height="620"> <img src="https://user-images.githubusercontent.com/43956851/110861661-c080dd80-82be-11eb-8ef1-057a7a6484cf.png" width="320" height="620">


* ### Network Layer 
MintBankICR uses Retrofit to make a GET HTTP request to https://lookup.binlist.net/, then parses the response body into Kotlin object with Moshi. 
Kotlin sealed class feature was deployed to ensure a robust yet concise network error handling mechanism.

* ### Test Layer
MintBankICR test layer contains local unit tests, instrumented unit tests, integration tests, and UI tests for the various components of the app.
More test cases still need to be covered.

* ### Dependency Injection
The dependency injection layer was completely managed by Hilt. This layer provides dependencies to the various layers of the app including the Test layer.

* ### Repository
The app main repository serves as an interface between the view models and the network layer.

* ### Optical Character Recognition + Camera Configuration
Google's ML Kit was exploited for it's on device machine learning capacity, while Androidx CameraX library was configured to capture input images for ML Kit.

* ### ViewModel


MiltBankICR view models holds all the UI states in the app to act as a single source of truth for state changes whilst serving as a data channel to the repository layer.

* ### Concurreny 
All long running operations(like running the machine learning text recognition algorithm, fetching results from the network) capable of blocking the main thread were handled on 
a separate thread by Kotlin coroutine framework.

* ### Model
The model layer consits of the card details representation and the network result states.

## Open Source Libraries used 
1. [Retrofit](https://github.com/square/retrofit) - A type-safe HTTP client for Android and Java.
2. [Moshi](https://github.com/square/moshi) - Moshi is a modern JSON library for Android and Java. It makes it easy to parse JSON into Kotlin objects.
3. [Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) - Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
4. [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android.
5. [ML Kit](https://developers.google.com/ml-kit) - ML Kit brings Google’s machine learning expertise to mobile developers in a powerful and easy-to-use package.
6. [CameraX](https://developer.android.com/jetpack/androidx/releases/camera) - CameraX is a Jetpack support library, built to help make camera app development easier.
7. [JUnit4](https://junit.org/junit4/) - JUnit is a simple framework for writing local unit tests.

## TODO
1. Improve code documentation
2. More tests cases to be written
