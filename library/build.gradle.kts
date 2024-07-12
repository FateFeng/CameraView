plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = property("compileSdkVersion") as Int
    defaultConfig {
        minSdk = property("minSdkVersion") as Int
        targetSdk = property("targetSdkVersion") as Int
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["filter"] = "" +
                "com.otaliastudios.cameraview.tools.SdkExcludeFilter," +
                "com.otaliastudios.cameraview.tools.SdkIncludeFilter"
    }
    buildTypes["debug"].isTestCoverageEnabled = true
    buildTypes["release"].isMinifyEnabled = false

    buildTypes["release"].consumerProguardFile("proguard-rules.pro")
    sourceSets["main"].java.srcDirs("src/commonMain/kotlin")
    sourceSets["main"].manifest.srcFile("src/androidJvmMain/AndroidManifest.xml")
}

dependencies {
    testImplementation("junit:junit:4.13.1")

    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation("androidx.annotation:annotation:1.2.0")
}
