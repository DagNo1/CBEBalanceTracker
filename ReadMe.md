
# CBE Balance Tracker App

Welcome to the CBE Balance Tracker App! This app helps you track your balance and transactions related to your CBE account. It provides insights into your financial activities using SMS data in your inbox.

## Features

- **Balance Tracking**: Monitor your account balance over time.
- **Transaction History**: View detailed transaction history.
- **Graphical Representation**: Visualize your financial data with intuitive graphs.

## Installation

### Requirements

- Android device running Android 5.0 (Lollipop) or higher.
- Permissions to read SMS messages (required for fetching bank SMS data).

### Steps to Install

1. Clone the repository:

   ```bash
   git clone https://github.com/DagNo1/CBEBalanceTracker.git
   ```

2. Open the project in Android Studio.
3. Connect your Android device or use an emulator.
4. Build and run the project.

# Architecture

The CBE Balance Tracker app follows the **Model-View-ViewModel (MVVM)** architecture pattern. This architecture separates the app into three main components:

- **Model**: Represents the data and business logic of the application. It includes data models (`SmsData`, `OnboardingItem`) and the repository (`SmsRepository`) for managing data operations.

- **View**: Represents the UI components of the application. Screens such as `HomeScreen`, `ProfileSetupScreen`, and `OnboardingScreen` are responsible for displaying data and interacting with the user.

- **ViewModel**: Acts as a bridge between the View and the Model. ViewModels (`HomeViewModel`, `ProfileSetupViewModel`, `LoginViewModel`, etc.) manage the presentation of data to the UI components and handle user interactions. They abstract the UI from the underlying business logic and data handling.


## Folder Structure

### Overview

The project is structured to maintain a clear separation of concerns and follow a clean architecture approach. Here's an overview of the folder structure:

```
tech/
└── dagimtesfaye/
    └── cbe_balance_tracker/
        ├── data/
        ├── navigation/
        ├── ui/
        ├── utils/
        └── view/
```

### Folder Details

#### 1. **data/**

- **model/**: Contains data models used throughout the application.
    - `OnboardingItem.kt`: Data class representing onboarding item information.
    - `SmsData.kt`: Data class representing SMS data related to transactions.

- **repository/**: Manages data operations and abstracts the data sources.
    - `SharedPreferenceManager.kt`: Manages data stored in SharedPreferences.
    - `SmsRepository.kt`: Handles SMS data retrieval and management.

#### 2. **navigation/**

- **NavGraph.kt**: Defines the navigation graph for the application using Jetpack Navigation.
- **Screen.kt**: Contains destinations and routes for navigating between screens.

#### 3. **ui/**

- **theme/**: Defines the theme-related components of the application.
    - `Color.kt`: Defines color constants used in the app's theme.
    - `Shape.kt`: Provides shape definitions for UI components.
    - `Theme.kt`: Defines the overall theme configuration.
    - `Type.kt`: Provides typography configurations.

#### 4. **utils/**

- **Extensions.kt**: Contains extension functions used across the application.

#### 5. **view/**

- **components/**: Reusable UI components used throughout the app.
    - `LineGraph.kt`: Custom component for displaying line charts.
    - `OTPTextField.kt`: Custom component for OTP input field.
    - `SmsItem.kt`: Component to display individual SMS transaction items.

- **home/**: Contains the home screen related components.
    - `HomeScreen.kt`: Displays the main screen of the app with transaction data.
    - `HomeViewModel.kt`: ViewModel responsible for managing data for the HomeScreen.

- **login/**: Manages the login screen and related logic.
    - `LoginScreen.kt`: Implements the login screen UI.
    - `LoginViewModel.kt`: ViewModel for handling login-related data and logic.

- **onboarding/**: Handles the onboarding process for new users.
    - `OnboardingScreen.kt`: Displays onboarding screens for new users.

- **profileSetup/**: Manages the profile setup flow.
    - `ProfilePinSetupScreen.kt`: Screen for setting up a PIN during profile setup.
    - `ProfileSetupScreen.kt`: Main screen for profile setup.
    - `ProfileSetupViewModel.kt`: ViewModel for managing profile setup data and logic.

- **termsAndAgreements/**: Manages terms and agreements acceptance.
    - `TermsAndAgreementsScreen.kt`: Displays that the app requires read sms permissions.

### Key Features:

- **Separation of Concerns**: MVVM ensures a clear separation between UI logic (View) and business logic (ViewModel and Model), making the codebase more modular and easier to maintain.

- **Reactive Data Flow**: Utilizes LiveData or StateFlow for reactive UI updates, ensuring that the UI reflects the latest data state.

- **Jetpack Components**: Integrates with Jetpack components like ViewModel, LiveData (or StateFlow), and Navigation for robust and consistent app architecture.

This architecture promotes scalability, testability, and maintainability, allowing for easier expansion and modification of features in the future.

# Commit Messages

This project follows the **Conventional Commits** specification for commit messages. Conventional Commits is a simple yet powerful convention for writing commit messages that are easy to read and automate. It provides a structured way to communicate the nature of changes made in a commit.

### Key Benefits:

- **Clarity**: Each commit message is prefixed with a type (e.g., `feat`, `fix`, `docs`, `style`) indicating the nature of the change, followed by a concise description.

- **Automation**: Tools and scripts can parse commit messages based on the convention to automatically generate changelogs, version releases, or perform other tasks.

- **Consistency**: Ensures consistency across commits, making it easier for team members to understand the history of changes and review pull requests.

Where:
- **type**: Describes the kind of change (e.g., `feat` for new features, `fix` for bug fixes, `docs` for documentation changes).
- **scope**: (Optional) Indicates the context of the change (e.g., module, component).
- **description**: A short description of the change.
- **body**: (Optional) Additional details explaining the change if necessary.
- **footer**: (Optional) Metadata about the commit, such as issue tracker IDs or breaking changes.

By adhering to Conventional Commits, i aim to maintain clear and meaningful commit messages that enhance collaboration and project transparency.

