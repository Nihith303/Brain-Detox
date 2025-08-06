# Brain Detox - Android App

A comprehensive digital wellness Android application designed to help users manage their screen time and reduce mindless app usage through intelligent app blocking and time limit enforcement.

## 🎯 Overview

Brain Detox is a sophisticated Android app that helps users take control of their digital habits by:
- Setting daily time limits for distracting apps
- Monitoring app usage in real-time
- Automatically blocking apps when time limits are reached
- Providing math challenges as a barrier to accessing blocked apps
- Tracking daily screen time and usage statistics

## ✨ Features

### Core Functionality
- **App Selection & Time Limits**: Choose which apps to monitor and set daily time limits
- **Real-time Monitoring**: Background service that tracks app usage continuously
- **Smart Blocking**: Automatically redirects users when time limits are reached
- **Usage Statistics**: Visual graphs and detailed breakdowns of daily app usage
- **Math Challenge System**: Solve math problems to gain temporary access to blocked apps

### User Experience
- **Onboarding Flow**: Guided setup with permission requests
- **Intuitive UI**: Clean, modern interface with Material Design components
- **Real-time Updates**: Live usage statistics with pull-to-refresh functionality
- **Visual Feedback**: Progress bars and usage graphs for better understanding

### Technical Features
- **Background Service**: Persistent monitoring using Android's UsageStatsManager
- **Permission Management**: Handles usage access, overlay, and notification permissions
- **Data Persistence**: Local storage of app selections and usage data
- **Daily Reset**: Automatic reset of usage counters at midnight

## 🏗️ Architecture

### Key Components

#### Activities
- **OnboardingActivity**: Initial setup and permission requests
- **MainActivity**: Dashboard with usage statistics and app management
- **AppSelectionActivity**: Interface for selecting apps to block
- **TimeLimitBlockingActivity**: Blocking screen with countdown
- **MathChallengeActivity**: Math problem solving interface
- **TimeIncreaseActivity**: Time limit increase confirmation
- **AddTimeActivity**: Additional time management
- **AppTimeIncreaseMathActivity**: Math challenges for app time increases

#### Services
- **BlockerService**: Core background service for app monitoring and blocking
- **OverlayBlockingService**: Overlay-based blocking implementation

#### Utilities
- **UsageUtils**: Centralized usage tracking and data management
- **OnboardingAdapter**: ViewPager adapter for onboarding flow

### Data Flow
1. User selects apps and sets time limits
2. BlockerService monitors foreground app usage
3. When limits are reached, blocking mechanisms activate
4. Users can solve math challenges for temporary access
5. Usage data is tracked and displayed in real-time

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+ (API level 24)
- Kotlin 1.8+
- Gradle 7.0+

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd brain-rot-app
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the project directory and select it

3. **Build and Run**
   - Connect an Android device or start an emulator
   - Click "Run" in Android Studio
   - The app will install and launch automatically

### Required Permissions

The app requires several permissions to function properly:

- **Usage Access**: Required to monitor app usage
- **Overlay Permission**: Needed to show blocking screens over other apps
- **Notification Permission**: For blocking alerts and service notifications
- **Foreground Service**: For continuous background monitoring

These permissions are requested during the onboarding flow.

## 📱 Usage Guide

### First Time Setup

1. **Launch the App**: The app starts with an onboarding flow
2. **Grant Permissions**: Follow the prompts to grant necessary permissions
3. **Select Apps**: Choose which apps you want to monitor and block
4. **Set Time Limits**: Configure daily time limits for each selected app
5. **Start Monitoring**: The app will begin monitoring your usage

### Daily Usage

- **Dashboard**: View your daily screen time and app usage statistics
- **Real-time Monitoring**: The app runs in the background monitoring usage
- **Blocking**: When you reach time limits, the app will block access
- **Math Challenges**: Solve math problems to gain temporary access
- **Statistics**: Pull down to refresh usage data

### Managing Apps

1. **Add/Remove Apps**: Use the "Select Apps to Block" button
2. **Adjust Time Limits**: Use "Set Time Limits" to modify daily limits
3. **View Usage**: See how much time you've spent on each app
4. **Reset Data**: Pull to refresh to update statistics

## 🔧 Technical Details

### Build Configuration

```kotlin
android {
    namespace = "com.example.testing"
    compileSdk = 36
    minSdk = 24
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"
}
```

### Dependencies

- **AndroidX Core KTX**: Kotlin extensions for Android
- **Material Design**: UI components and theming
- **SwipeRefreshLayout**: Pull-to-refresh functionality
- **ViewPager2**: Onboarding flow implementation

### Key Permissions

```xml
<uses-permission android:name="android.permission.PACKAGE_USAGE_STATS" />
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

### Data Storage

- **SharedPreferences**: Used for app selections, time limits, and usage data
- **Daily Reset**: Usage counters automatically reset at midnight
- **Persistent Storage**: App selections and settings persist across sessions

## 🎨 UI/UX Design

### Design Principles
- **Minimalist Interface**: Clean, distraction-free design
- **Visual Feedback**: Progress bars and usage graphs
- **Intuitive Navigation**: Simple, logical flow between screens
- **Accessibility**: Support for different screen sizes and orientations

### Color Scheme
- Primary: Custom blue theme
- Secondary: Complementary colors for graphs
- Text: High contrast for readability
- Background: Clean white/light theme

## 🔒 Security & Privacy

### Data Handling
- **Local Storage Only**: All data stored locally on device
- **No Cloud Sync**: No data transmitted to external servers
- **Permission Scoped**: Only requests necessary permissions
- **Transparent Usage**: Clear indication of what data is collected

### Privacy Features
- **Usage Stats Only**: Monitors app usage, not content
- **No Personal Data**: Doesn't collect personal information
- **User Control**: Users can revoke permissions at any time

## 🐛 Troubleshooting

### Common Issues

**App Not Blocking**
- Ensure usage access permission is granted
- Check that apps are selected and time limits are set
- Verify the BlockerService is running

**Permission Issues**
- Go to Settings > Apps > Brain Detox > Permissions
- Grant all required permissions manually

**Service Not Starting**
- Check battery optimization settings
- Ensure the app is not force-stopped
- Restart the app if necessary

### Debug Mode

The app includes comprehensive logging for debugging:
- Check Logcat for detailed service logs
- Monitor usage tracking in real-time
- Verify permission status through logs

## 🤝 Contributing

### Development Setup
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly on multiple devices
5. Submit a pull request

### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Add comments for complex logic
- Maintain consistent formatting

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Android UsageStatsManager API
- Material Design Components
- AndroidX libraries
- Kotlin language features

## 📞 Support

For issues, questions, or feature requests:
- Create an issue in the repository
- Check the troubleshooting section
- Review the code documentation

---

**Note**: This app is designed for personal use and digital wellness. It should not be used to circumvent legitimate app restrictions or violate terms of service for other applications. 