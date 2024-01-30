// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
}
val sourceCompatibility by extra(VERSION_17)
val defaultApplicationId by extra("org.huuloi.events")
