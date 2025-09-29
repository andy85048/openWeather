object Versions {
    const val kotlin = "1.9.20"
    const val androidGradlePlugin = "8.1.2"
    const val compileSdk = 35
    const val minSdk = 24
    const val targetSdk = 35
    const val versionCode = 1
    const val versionName = "1.0"

    const val coreKtx = "1.12.0"
    const val lifecycleRuntime = "2.7.0"
    const val activityCompose = "1.8.2"
    const val composeBom = "2023.10.01"
    const val navigationCompose = "2.7.5"
    const val hilt = "2.48"
    const val hiltNavigationCompose = "1.1.0"
    const val coroutines = "1.7.3"
    const val retrofit = "2.9.0"
    const val okhttp = "4.12.0"
    const val room = "2.6.0"
    const val junit = "4.13.2"
    const val androidxJunit = "1.1.5"
    const val espresso = "3.5.1"
}

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleRuntime}"

    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
    const val composeMaterial3 = "androidx.compose.material3:material3"

    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val junit = "junit:junit:${Versions.junit}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}