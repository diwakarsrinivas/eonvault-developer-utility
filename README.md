# Eonvault Developer Utility

Standalone IntelliJ Platform plugin scaffold for a cross-IDE developer utility.

## Included

- Kotlin + Gradle IntelliJ Platform setup
- Plugin metadata (`plugin.xml`)
- Shared command contracts and error model
- Persistent settings storage service
- Command registration skeleton for structured data and log workflows
- GitHub Actions CI pipeline
- Static checks (ktlint)
- Test harness (JUnit + jacoco)

## Local commands

```powershell
./gradlew.bat test
./gradlew.bat check
./gradlew.bat runIde
```

## Current command skeleton

- Convert Structured Data
- Validate Structured Data
- Analyze Logs

These commands are registered in the IDE menu and currently return placeholder
notifications through a shared registry. The execution contracts, settings
service, and error model are in place so feature work can proceed without
reshaping the plugin foundation.
