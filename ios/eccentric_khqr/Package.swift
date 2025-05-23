
// swift-tools-version: 5.9
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "eccentric_khqr",
    platforms: [
        .iOS("11.0")
    ],
    products: [
        .library(name: "eccentric_khqr", targets: ["eccentric_khqr"])
    ],
    dependencies: [],
    targets: [
        .target(
            name: "eccentric_khqr",
            dependencies: [],
            resources: [
                .process("PrivacyInfo.xcprivacy")
            ]
        )
    ]
)
