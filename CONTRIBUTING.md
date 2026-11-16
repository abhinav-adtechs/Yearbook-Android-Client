# Contributing to Yearbook VIT

Thanks for your interest. Yearbook VIT is a 2017 Android app that is archived and no longer actively developed. Focused fixes, modernisation work and docs improvements are still welcome.

Please read and follow the [Code of Conduct](https://github.com/abhinav-adtechs/.github/blob/main/CODE_OF_CONDUCT.md). To report a vulnerability, follow the [security policy](https://github.com/abhinav-adtechs/.github/blob/main/SECURITY.md) and do **not** open a public issue.

## Development setup

The project uses the 2017 Android toolchain: Android Gradle Plugin 2.3.3, `compileSdkVersion 25`, Build-Tools 25.0.3 and Support Library 25.3.1.

1. Install JDK 8, Android SDK Platform 25 and Build-Tools 25.0.3.
2. Clone the repo and add the missing module settings:

   ```bash
   git clone https://github.com/abhinav-adtechs/Yearbook-Android-Client.git
   cd Yearbook-Android-Client
   echo "include ':app'" > settings.gradle
   ```

3. Open the folder in a 2.3/3.0-era Android Studio, or use Gradle 3.3+. Let the IDE create a Gradle wrapper, then run the `app` configuration.

A current Android Studio won't sync AGP 2.3.3. If you're working on the modernisation roadmap items (AGP/AndroidX/Maven Central), keep each step in its own PR so it's easy to review.

### Firebase and Crashlytics

`app/google-services.json` and the Fabric configuration point at the original 2017 projects. When forking, use your own Firebase project and never commit new keys or secrets. Fabric has been shut down; migrating away from it is on the roadmap.

### Useful places in the code

| You want to…                               | Look at                                                                                       |
| ------------------------------------------ | --------------------------------------------------------------------------------------------- |
| Change where books are downloaded from     | `Model/Utils/Constants.java` (`BASE_URL`, `URL_BOOK`)                                         |
| Add or edit an edition (year, cover, size) | `View/NewUI/Dashboard/DashYearAdapter.java`, `DashboardUpdatedFragment.java`, `res/drawable/` |
| Edit team lists                            | `res/values/strings.xml` (`yb20xx_team_*` arrays), `View/NewUI/Team/TeamFragment.java`        |
| Touch the download flow                    | `Presenter/Main/MainActivityPresenter.java`, `Model/UIModels/BookDownloadingListener.java`    |

[`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md) has the full map.

## Branches and commits

- Branch from `master` with a descriptive name, e.g. `fix/download-state`, `chore/agp-upgrade` or `docs/screenshots`.
- Use [Conventional Commits](https://www.conventionalcommits.org/):
  - `feat: load editions from a remote manifest`
  - `fix: compare download state with equals()`
  - `build: add settings.gradle and Gradle wrapper`
  - `docs: add dashboard screenshot`
- Keep subject lines short, in the imperative mood, and focused on _why_.

## Filing issues

- **Bugs:** include the device or emulator, Android version, steps to reproduce, and what you expected versus what happened. Screenshots help, especially for layout issues; add device-specific layout reports to [#2](https://github.com/abhinav-adtechs/Yearbook-Android-Client/issues/2) rather than opening a new issue.
- **Features:** say what problem it solves for a student reading the yearbook, and check the README roadmap first.

## Pull request checklist

- [ ] The change is scoped to one concern (one bug, one feature or one build step).
- [ ] The app builds and launches. Note in the PR which toolchain you used (era-accurate or modernised).
- [ ] Manually tested the touched flow: splash → dashboard → download → open book, and/or the Team and Credits tabs.
- [ ] No keys, secrets or personal data added. Team lists stay as published by the yearbook team.
- [ ] Docs updated if behaviour or setup changed (`README.md`, `docs/ARCHITECTURE.md`, `CHANGELOG.md` under _Unreleased_).
- [ ] Screenshots attached for UI changes.

By contributing, you agree that your contributions are licensed under the [MIT License](LICENSE).
