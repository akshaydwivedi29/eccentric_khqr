# Copied and adapted from khqr_sdk.podspec
Pod::Spec.new do |s|
  s.name             = 'eccentric_khqr'
  s.version          = '1.0.0'
  s.summary          = 'Standardization of KHQR code specifications will help promote wider use of mobile retail payments in Cambodia.'
  s.description      = <<-DESC
The standardization of KHQR code specifications will help promote wider use of mobile retail payments in Cambodia and provide consistent user experience for merchants and consumers.
It can enable interoperability in the payment industry. A common QR code would facilitate payments among different schemes, e-wallets and banks and would encourage small merchants to adopt KHQR code as payment method.
KHQR is created for retail payment in Cambodia and Cross-Border payment within asean countries. It only requires a single QR for receiving payment from any mobile apps including Bakong app, making QR payment simple for both customers and merchants in Cambodia.
                       DESC
 
  s.license          = { :file => '../LICENSE', :type => 'MIT' }
  s.homepage         = 'https://github.com/akshaydwivedi29/eccentric_khqr'
  s.authors          = { 'Your Name' => 'your@email.com' }
  s.source           = { :git => 'https://github.com/akshaydwivedi29/eccentric_khqr.git', :tag => s.version.to_s }
  s.documentation_url = 'https://pub.dev/packages/eccentric_khqr'
  s.source_files = 'eccentric_khqr/Sources/eccentric_khqr/**/*.swift'
  s.dependency 'Flutter'
  s.dependency 'BakongKHQR', '= 1.0.0.16'
  s.platform = :ios, '11.0'
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.swift_version = '5.0'
  s.resource_bundles = {'eccentric_khqr_privacy' => ['eccentric_khqr/Sources/eccentric_khqr/PrivacyInfo.xcprivacy']}
end
