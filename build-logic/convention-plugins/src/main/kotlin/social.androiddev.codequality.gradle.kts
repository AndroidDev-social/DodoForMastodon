/**
 * Convention plugin to apply various code-quality tools. Since this should be included in every
 * module, it's applied automatically by other convention plugins, but can be applied directly for
 * any module that doesn't use any.
 */
plugins {
    id("com.diffplug.spotless")
}

spotless {
    kotlin {
        target("src/*/kotlin/**/*.kt")
        ktlint("0.43.2")
        licenseHeaderFile(File(rootDir, "copyright.txt"))
    }
}