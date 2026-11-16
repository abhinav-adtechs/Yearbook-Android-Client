# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project uses [Semantic Versioning](https://semver.org/spec/v2.0.0.html). Each tag maps to the app's in-build `versionName` (`beta-0.x`). Entries come from the git history; short hashes are in parentheses.

## [0.3.0] - 2017-10-22

`versionName "beta-0.3"`

### Changed

- Rewrote the fragment layouts and bumped `versionName` to `beta-0.3` (c00181a).
- Rewrote the Team screen layout (4574a9e).

## [0.2.0] - 2017-10-19

`versionName "beta-0.2"`

### Added

- `.gitignore` for Android Studio, Gradle and keystore files (9586b29).

### Changed

- Removed per-density drawable duplicates and tidied `app/build.gradle` (f5bb057).

### Fixed

- Layouts tuned for hdpi (6b79bae), xxhdpi (0931765) and xhdpi (d5c06dc) screens.
- Final screen sorted for all densities (ae92917).

## [0.1.0] - 2017-10-18

`versionName "beta-0.1"`. The first beta: everything from the initial commit (2017-09-09) up to the first tagged build.

### Added

- Base architecture, navigation and the first dashboard of books (a9698dd, 3c99583, b29c68d, bae9602).
- Splash animation (db366ab), later replaced by the yearbook intro video (c8efad9).
- PDF reader for downloaded books (0b38970), fixed up in 4f25345.
- Team view (8ea78da), member listing adapter (8004d70) and year-wise team sorting (6fe8ae6).
- Year carousel with year-wise fragments on the dashboard (a48b793, 566c896).
- Book downloader with pause/resume handling (96c08f9), finished and tested (5044282, 6bc987c).
- Credits section (3829bf9).
- Dagger + presenter architecture and design clean-up (fdc78ee, 965ff3d).
- Firebase push notification support (9263c04), plus Firebase and Crashlytics integration (3e48161).

### Changed

- UI revamp: new base UI, frame setups, scaling effects, bottom-bar animations and a reworked top bar (5f5ef4a, 927d863, 943725b, 456df32, 2598a13, 0673ab8, c7603f9, 810e368).
- Cover images converted to WebP (4f6a7e7).
- Removed a bundled test PDF and other unused files (2a3be1c).

### Fixed

- Top bar layout (5c1b091) and assorted UI bugs (6082d75, a32adc6, 3ef2744, 783d1ec).
- Small memory fix in the download flow (392d129).
- Layouts for mdpi screens (7f265a3, cb0d6ac, 259d17c) and fixes across screen sizes (3e48161).

[0.3.0]: https://github.com/abhinav-adtechs/Yearbook-Android-Client/compare/v0.2.0...v0.3.0
[0.2.0]: https://github.com/abhinav-adtechs/Yearbook-Android-Client/compare/v0.1.0...v0.2.0
[0.1.0]: https://github.com/abhinav-adtechs/Yearbook-Android-Client/commits/v0.1.0
