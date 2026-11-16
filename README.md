# Tenant Management System

An Android app (Kotlin) for landlords to record tenants, see the total rent, and call tenants.

## Download
Latest APK: RELEASE_LINK (also under **Releases** on the right of this page).

To install: open the link on an Android phone, tap `app-release.apk` under **Assets**, allow installs from this source, then tap **Install**. If Play Protect warns, tap **More details → Install anyway**.

## Features
- Register and log in
- Dashboard with the number of tenants and the total rent
- Add tenants and view all tenants (saved with Room, so they are still there after closing the app)
- Call a tenant, options menu and navigation drawer

## Architecture
The code is organised into a UI layer and a data layer. The `ui` layer may use `data`, but `data` never uses `ui`.

- `ui/auth` - LoginActivity, RegisterActivity
- `ui/main` - MainActivity
- `ui/dashboard` - DashboardFragment
- `ui/tenants` - AddTenantFragment, ViewTenantsFragment
- `ui/viewmodel` - TenantViewModel (UI layer)
- `data/model` - Tenant (Room entity)
- `data/local` - TenantDao, TenantDatabase (Room)
- `data/repository` - TenantRepository (the single door into the data layer)

Flow of data: Screens -> TenantViewModel (LiveData) -> TenantRepository -> Room database

## Technologies
- Kotlin, Fragments, Navigation with Safe Args, Data Binding
- ViewModel and LiveData (Lifecycle 2.8.7), Fragment KTX 1.8.5
- Room 2.7.2 with KSP
- Coroutines (`viewModelScope`)

## Team
| Stage              | Member          | Admission No. | What they did                                                            | Pull request                                                                                                      |
|--------------------|-----------------|---------------|--------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| 1 Lead             | Kamau Mary      | 191854        | Repository, .gitignore, ui and data packages (pushed directly to master) | [Commit d88a96e](https://github.com/Kamauma/TenantMagementSystem/commit/d88a96eb0b6a0e2446bcff3986540e6744374dd6) |
| 2 ViewModel        | Kostakis Saruni | 150574        | TenantViewModel with LiveData                                            | [PR #1](https://github.com/Kamauma/TenantMagementSystem/pull/1)                                                   |
| 3 Room             | Tonny Oirere    | 150917        | Room entity, DAO and database                                            | [PR #2](https://github.com/Kamauma/TenantMagementSystem/pull/2)                                                   |
| 4 Screens          | Ndirangu Sasha  | 168827        | Screens use the ViewModel                                                | [PR #3](https://github.com/Kamauma/TenantMagementSystem/pull/3)                                                   |
| 5 Data integration | Lysa Munjogu    | 190373        | Tenants saved in Room through the repository                             | [PR #4](https://github.com/Kamauma/TenantMagementSystem/pull/4)                                                   |
| 6 Release          | Tyron Odhiambo  | 151115        | Version 1.0, README, signed APK, GitHub Release v1.0                     | LINK_6                                                                                                            |

## How we worked with Git
Each member did one stage on their own branch, opened a pull request, and had it reviewed and merged by a teammate. Nobody pushed straight to `master` except the Lead in Stage 1.

Branches: `feature/viewmodel`, `feature/room`, `feature/screens`, `feature/data-integration`, `release/v1.0`.

## Build and run
1. Clone the repository and open it in Android Studio.
2. Wait for Gradle to finish syncing (choose **File → Sync Project with Gradle Files** if asked).
3. Click **Run** on an emulator or a phone.