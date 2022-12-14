plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
    id(Plugins.SECRETS_GRADLE_PLUGIN)
    id(Plugins.SAFEARGS)
    id(Plugins.PARCELIZE)
    id(Plugins.HILT_PLUGIN)
}

android {
    namespace = "com.chlqudco.kakaobooksearch"
    compileSdk = DefaultConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.chlqudco.kakaobooksearch"
        minSdk = DefaultConfig.MIN_SDK_VERSION
        targetSdk = DefaultConfig.TARGET_SDK_VERSION
        versionCode = DefaultConfig.VERSION_CODE
        versionName = DefaultConfig.VERSION_NAME


//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.chlqudco.kakaobooksearch.HiltTestRunner"
    }

    buildTypes {
        release {
            //minifyEnabled =  false
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_11
        targetCompatibility  = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding =  true
    }
    kapt {
        correctErrorTypes =  true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        animationsDisabled = true
    }
}

dependencies {

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)

    // Test
    testImplementation(Testing.junit4)
    testImplementation(Testing.textExtTruth)
    testImplementation(Testing.textRunner)
    testImplementation(Testing.roboletric)
    testImplementation(Testing.testExtJunit)
    testImplementation(Testing.testCore)
    testImplementation(Testing.coroutineTest)

    androidTestImplementation(Testing.testExtJunit)
    androidTestImplementation(Testing.espressoCore)
    androidTestImplementation(Testing.espressoContrib)
    androidTestImplementation(Testing.hamcrest)
    androidTestImplementation(Testing.testCore)
    androidTestImplementation(Testing.textExtTruth)
    androidTestImplementation(Testing.textRunner)
    androidTestImplementation(Testing.coroutineTest)
    androidTestImplementation(Testing.hiltAndroidTesting)
    kaptAndroidTest(Testing.hiltAndroidCompiler)

    // Retrofit
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_MOSHI)

    // Moshi
    implementation(Dependencies.MOSHI)
    kapt(Dependencies.MOSHI_KAPT)

    // Okhttp
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)

    // Lifecycle
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)

    // Coroutine
    implementation(Dependencies.COROUTINE_CORE)
    implementation(Dependencies.COROUTINE_ANDROID)

    // Coil
    implementation(Dependencies.COIL)

    // Recyclerview
    implementation(Dependencies.RECYCLERVIEW)

    // Navigation
    implementation(Dependencies.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.NAVIGATION_UI_KTX)

    // Room
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.ROOM_KTX)
    kapt(Dependencies.ROOM_KAPT)
    implementation(Dependencies.ROOM_PAGING)

    // Kotlin serialization
    implementation(Dependencies.KOTLIN_SERIALIZATION)

    // DataStore
    implementation(Dependencies.PREFERENCES_DATASTORE)

    // Paging
    implementation(Dependencies.PAGING)

    // WorkManager
    implementation(Dependencies.WORKMANGER)

    // Hilt
    implementation(Dependencies.DAGGER_HILT)
    kapt(Dependencies.DAGGER_HILT_KAPT)

    // ViewModel delegate
    implementation(Dependencies.ACTIVITY_KTX)
    implementation(Dependencies.FRAGMENT_KTX)

    // Hilt extension
    implementation(Dependencies.HILT_EXTENSION_WORK)
    kapt(Dependencies.HILT_EXTENSION_KAPT)
}