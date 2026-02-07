# 📱 PortfolioApp

A **personal portfolio Android application** built entirely with **Kotlin** and **Jetpack Compose**. This app serves as a digital resume — showcasing my profile, skills, projects, and contact information in a polished, installable mobile format.

> Designed and developed by **Bhragu Tripathi**, a Computer Science & Engineering student passionate about Android development and modern UI design.

---

## ✨ Features

| Screen | Description |
|--------|-------------|
| 🏠 **Home** | Animated landing page with a profile photo, greeting with a waving-hand animation, and quick navigation cards |
| 👤 **About** | A scrollable "My Story" section with profile image and a letter-style introduction about my journey |
| 🛠 **Skills** | Categorized skill showcase — Programming Languages, Frameworks & UI, Tools & Platforms, and Soft Skills — displayed with icons |
| 💼 **Projects** | A grid layout of project cards with staggered entry animations and press effects |
| 📞 **Contact** | Contact form (Name, Email, Message) that opens an email intent, plus social links to LinkedIn, Instagram, and X (Twitter) |

### 🎨 UI Highlights

- **Jetpack Compose** declarative UI — no XML layouts
- **Material 3** design system with a custom light-blue color palette
- **Smooth screen transitions** — fade + slide animations between all screens
- **Animated bottom navigation bar** with selected/unselected icon states
- **Edge-to-edge** rendering with proper status bar and navigation bar padding
- **Gradient backgrounds** and elevated content cards for a modern look
- **Waving hand animation** on the Home screen using infinite transitions

---

## 🧑‍💻 Tech Stack

| Category | Technology |
|----------|-----------|
| **Language** | Kotlin |
| **UI Framework** | Jetpack Compose + Material 3 |
| **Navigation** | Navigation Compose (`2.7.0`) |
| **Serialization** | Kotlinx Serialization JSON (`1.6.3`) |
| **Layout** | ConstraintLayout Compose (`1.0.1`) |
| **Animations** | Compose Animation + Animated Navigation Bar |
| **Build System** | Gradle (Kotlin DSL) with Version Catalog |
| **Min SDK** | 25 (Android 7.1) |
| **Target SDK** | 36 |
| **IDE** | Android Studio |

---

## 📂 Project Structure

```
PortfolioApp/
├── app/
│   ├── src/main/java/com/example/portfolio/
│   │   ├── MainActivity.kt                  # Entry point — sets up Compose theme & NavGraph
│   │   ├── navigation/
│   │   │   ├── Screen.kt                    # Sealed class defining all route destinations
│   │   │   ├── NavGraph.kt                  # Navigation host with animated screen transitions
│   │   │   └── BottomNavItem.kt             # Bottom nav items with icons & routes
│   │   └── ui/
│   │       ├── components/
│   │       │   └── Component.kt             # Reusable composables (TopBar, BottomNav, GradientBackground, ContentCard)
│   │       ├── screens/
│   │       │   ├── home/HomeScreen.kt       # Home screen with profile & wave animation
│   │       │   ├── about/AboutScreen.kt     # About/My Story screen
│   │       │   ├── skills/SkillsScreen.kt   # Skills categorized with icons
│   │       │   ├── projects/ProjectScreen.kt # Projects grid with animated cards
│   │       │   └── contact/ContactScreen.kt  # Contact form + social links
│   │       └── theme/
│   │           ├── Color.kt                 # App color palette (LightBlue, PrimaryBlue, etc.)
│   │           ├── Theme.kt                 # Material 3 theme configuration
│   │           └── Type.kt                  # Custom typography styles
│   ├── src/main/res/
│   │   ├── drawable/                        # Vector icons (LinkedIn, Instagram, X) & profile image
│   │   ├── mipmap-*/                        # App launcher icons
│   │   └── values/                          # Colors, strings, themes (XML)
│   ├── build.gradle.kts                     # App-level build config & dependencies
│   └── proguard-rules.pro                   # ProGuard rules for release builds
├── build.gradle.kts                         # Project-level Gradle config
├── settings.gradle.kts                      # Project settings & plugin management
├── gradle/                                  # Gradle wrapper & version catalog
├── gradlew / gradlew.bat                    # Gradle wrapper scripts
└── .gitignore                               # Git ignore rules
```

---

## 🏗 Architecture Overview

```
┌─────────────────────────────────────────────────┐
│                  MainActivity                    │
│         (Sets theme, creates NavController)      │
└────────────────────┬────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────┐
│                   NavGraph                       │
│    (Defines routes with animated transitions)    │
├──────┬──────┬──────┬──────────┬─────────────────┤
│ Home │About │Skills│ Projects │    Contact       │
│Screen│Screen│Screen│  Screen  │    Screen        │
└──────┴──────┴──────┴──────────┴─────────────────┘
                     │
         ┌───────────┴───────────┐
         │  Shared Components    │
         │  • GradientBackground │
         │  • TopBar             │
         │  • BottomNavigationBar│
         │  • ContentCard        │
         └───────────────────────┘
```

- **Single Activity** architecture — `MainActivity` hosts all screens via Jetpack Compose
- **Navigation Compose** manages screen routing with a sealed `Screen` class
- **Shared components** are used consistently across all screens for a uniform look
- **Theme layer** (`Color.kt`, `Theme.kt`, `Type.kt`) centralizes all styling

---

## ▶️ How to Run Locally

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (Hedgehog or later recommended)
- JDK 11+
- Android device or emulator (API 25+)

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/BhraguTripathi/PortfolioApp.git
   cd PortfolioApp
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Click **Open** → select the cloned `PortfolioApp` folder
   - Wait for Gradle sync to complete

3. **Run the app**
   - Connect a physical Android device via USB **or** start an emulator
   - Click the **Run ▶** button (or press `Shift + F10`)

---

## 📥 Download APK

You can download and install the APK directly on your Android device:

👉 **[Download Latest APK](https://github.com/BhraguTripathi/PortfolioApp/releases/latest/download/PortfolioApp.apk)**

> ⚠️ If the APK is not available yet, it will be published under [**Releases**](https://github.com/BhraguTripathi/PortfolioApp/releases).

---

## 🗂 Key Dependencies

| Library | Purpose |
|---------|---------|
| `androidx.compose.material3` | Material 3 UI components |
| `androidx.navigation:navigation-compose` | In-app screen navigation |
| `androidx.constraintlayout:constraintlayout-compose` | Complex layouts in Compose |
| `androidx.compose.material:material-icons-extended` | Extended Material icon set |
| `kotlinx-serialization-json` | JSON serialization support |
| `compose-animated-navigationbar` | Custom animated bottom nav bar |
| `androidx.compose.animation` | Compose animation APIs |

---

## 🧠 How It Works (Simple Explanation)

1. **Kotlin** — the app is written entirely in Kotlin, Android's modern programming language
2. **Jetpack Compose** — UI is built declaratively using composable functions (no XML layouts)
3. **Navigation** — a `NavGraph` routes between 5 screens, each with enter/exit animations
4. **Reusable Components** — shared composables (`TopBar`, `BottomNav`, `ContentCard`) ensure a consistent look
5. **Animations** — spring physics, fade transitions, and infinite wave animations bring the UI to life
6. **Contact Integration** — the contact form triggers an email `Intent` to send messages directly

---

## 🚧 Future Improvements

- [ ] Add **dark theme** support
- [ ] Replace placeholder project data with real projects
- [ ] Add **app screenshots** to this README
- [ ] Set up **GitHub Actions** CI/CD for automated APK builds
- [ ] Add unit and UI tests
- [ ] Publish to the **Google Play Store**

---

## 📄 License

This project is currently for **learning and personal use**.
A license will be added if the project is open-sourced for broader use.

---

## 👤 Author

**Bhragu Tripathi**
Computer Science & Engineering Student • Android Developer

📧 [tripathibhragu@gmail.com](mailto:tripathibhragu@gmail.com)
🔗 [GitHub](https://github.com/BhraguTripathi)