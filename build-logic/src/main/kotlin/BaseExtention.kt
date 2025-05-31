import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import java.util.Properties
import kotlin.io.inputStream


val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()


fun DependencyHandlerScope.implementation(dependency: Provider<MinimalExternalModuleDependency>) {
    add("implementation", dependency)
}

fun DependencyHandlerScope.debugImplementation( dependency: Provider<MinimalExternalModuleDependency> ) {
    add("debugImplementation", dependency)
}

fun AppExtension.setApkName(baseName: String) {
    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                val buildVariant = variant.buildType.name
                val versionName = variant.versionName
                output.outputFileName = "${baseName}_${buildVariant}_${versionName}.apk"
            }
    }
}

fun Project.properties(filename: String, block: Properties.() -> Unit) {
    val properties = Properties()
    file(filename).inputStream().use { properties.load(it) }
    block(properties)
}




