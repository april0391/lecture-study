import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}

android {
    namespace = "com.example.myshoppinglistapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.myshoppinglistapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // 1. Manifest 파일을 위한 설정
        manifestPlaceholders["MAPS_API_KEY"] = localProperties.getProperty("MAPS_API_KEY") ?: ""

        // 2. 코틀린 코드(ViewModel)를 위한 설정
        buildConfigField("String", "MAPS_API_KEY", localProperties.getProperty("MAPS_API_KEY"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        resValues = true
    }
}

dependencies {
    val nav_version = "2.9.4"

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.maps.android:maps-compose:2.15.0")

    // Jetpack Compose 내에서 ViewModel을 쉽게 사용하게 해주는 라이브러리입니다.
    // @Composable 함수 안에서 viewModel() 이라는 함수를 호출하여 ViewModel 인스턴스를
    // Composable의 생명주기에 맞춰 안전하게 가져올 수 있게 해줍니다.
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")

    // 네트워크 통신을 매우 쉽게 만들어주는 라이브러리입니다. (개발사: Square)
    // 웹 서버(API)에 HTTP 요청을 보내고 응답을 받는 과정을 간결한 코드로 처리할 수 있습니다.
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Retrofit 라이브러리의 확장 기능(Converter)입니다.
    // 서버로부터 받은 JSON 형식의 응답 데이터를 우리가 만든 코틀린 데이터 클래스로
    // 자동으로 변환(파싱)해주는 역할을 합니다.
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}