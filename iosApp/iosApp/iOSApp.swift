import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
}

class AppDelegate: UIResponder, UIApplicationDelegate, UNUserNotificationCenterDelegate {

    // Run initializers on app launch
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        Main_iosKt.doInitApp()
        return true
    }
}
