# Eccentric KHQR

A Flutter plugin for implementing KHQR (Khmer QR Code) functionality in your applications. This plugin provides a seamless interface to the native KHQR SDKs for both iOS and Android platforms. Supports both USH & KHR currencies

## Supported Platforms
- [x] iOS
- [x] Android

## Native KHQR SDK Version
- iOS using **`BakongKHQR`** (v1.0.0.16)
- Android using **`kh.gov.nbc.bakong_khqr:sdk-java:1.0.0.14`**

## Features Supported

See the example app for detailed implementation information.

| Features            | Android | iOS     |
|---------------------|---------|---------|
| Generate Individual |    ✔    |    ✔    |
| Generate Merchant   |    ✔    |    ✔    |
| Generate Deeplink   |    ✔    |    ✔    |
| Verify              |    ✔    |    ✔    |
| Decode              |    ✔    |    ✔    |
| Decode Non-KHQR     |    -    |    ✔    |
| KHQR Card Widget    |    ✔    |    ✔    |



## Features

✅ Generate KHQR codes for:
✅ Individual accounts
✅ Merchant accounts
✅ Dynamic QR with expiration

✅ Verify KHQR codes
✅ Decode KHQR and non-KHQR codes
✅ Generate deeplinks
✅ Built-in KHQR card widget
✅ Cross-platform support (iOS and Android)

## Getting Started

This plugin package supports out of the box installation. For ios ,you may have to follow the steps mentioned below.

### Installation

Add this to your package's `pubspec.yaml` file:

```yaml
dependencies:
  eccentric_khqr: ^1.1.7
```

### Platform Setup

#### iOS

1. Add the following source to your `ios/Podfile`:

```ruby
source "https://sambo:ycfXmxxRbyzEmozY9z6n@gitlab.nbc.gov.kh/khqr/khqr-ios-pod.git"
```

2. Disable Swift Package Manager in your `pubspec.yaml`, if you don't see the Podfile:

```yaml
flutter:
  disable-swift-package-manager: true
```

3. Run pod install:
```bash
cd ios && pod install
```

#### Android

No additional setup required. Works out of the box.

## Usage

### Initialize the SDK

```dart
import 'package:eccentric_khqr/khqr_sdk.dart.dart';

final _khqrSdk = KhqrSdk();
```

### Generate Individual KHQR

```dart
final info = IndividualInfo(
  bakongAccountId: 'user@bank',
  merchantName: 'John Doe',
  accountInformation: '123456789',
);
final khqrData = await _khqrSdk.generateIndividual(info);
```

### Generate Merchant KHQR

```dart
final info = MerchantInfo(
  bakongAccountId: 'merchant@bank',
  acquiringBank: 'Bank Name',
  merchantId: '123456',
  merchantName: 'Store Name',
);
final khqrData = await _khqrSdk.generateMerchant(info);

// For dynamic QR with expiration
final expire = DateTime.now().millisecondsSinceEpoch + 3600000; // 1 hour
final dynamicInfo = MerchantInfo(
  bakongAccountId: 'merchant@bank',
  acquiringBank: 'Bank Name',
  merchantId: '123456',
  merchantName: 'Store Name',
  amount: 10.0,
  expirationTimestamp: expire,
);
```

### Verify KHQR

```dart
final qrCode = '00020101021129...'; // Your KHQR code string
final isValid = await _khqrSdk.verify(qrCode);
```

### Decode KHQR

```dart
final qrCode = '00020101021129...'; // Your KHQR code string
final decodedData = await _khqrSdk.decode(qrCode);
```

### Generate Deeplink

```dart
final sourceInfo = SourceInfo(
  appName: 'My App',
  appIconUrl: 'https://myapp.com/icon.png',
  appDeepLinkCallBack: 'myapp://callback',
);

final deeplinkInfo = DeeplinkInfo(
  qr: qrCode,
  url: 'https://api.myapp.com/generate_deeplink',
  sourceInfo: sourceInfo,
);

final deeplinkData = await _khqrSdk.generateDeepLink(deeplinkInfo);
```

### KHQR Card Widget

```dart
KhqrCardWidget(
  width: 300.0,
  receiverName: 'Store Name',
  amount: 10.00,
  currency: KhqrCurrency.khr,
  qr: qrCodeContent,
  keepIntegerDecimal: false,
  showCurrencySymbol: true,
),
```

## Additional Information

### Native SDK Versions

- iOS: BakongKHQR v1.0.0.16
- Android: kh.gov.nbc.bakong_khqr:sdk-java:1.0.0.15

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

###  Credits

Based on the package https://pub.dev/packages/khqr_sdk. 
