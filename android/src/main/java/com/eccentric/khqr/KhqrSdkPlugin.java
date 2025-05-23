package com.eccentric.khqr;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

import kh.gov.nbc.bakong_khqr.BakongKHQR;
import kh.gov.nbc.bakong_khqr.model.CRCValidation;
import kh.gov.nbc.bakong_khqr.model.IndividualInfo;
import kh.gov.nbc.bakong_khqr.model.KHQRCurrency;
import kh.gov.nbc.bakong_khqr.model.KHQRData;
import kh.gov.nbc.bakong_khqr.model.KHQRDecodeData;
import kh.gov.nbc.bakong_khqr.model.KHQRDeepLinkData;
import kh.gov.nbc.bakong_khqr.model.KHQRResponse;
import kh.gov.nbc.bakong_khqr.model.MerchantInfo;
import kh.gov.nbc.bakong_khqr.model.SourceInfo;

public class KhqrSdkPlugin implements FlutterPlugin, MethodCallHandler {
    private MethodChannel channel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "eccentric_khqr");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        switch (call.method) {
            case "generateIndividual":
                generateIndividual(call, result);
                break;
            case "generateMerchant":
                generateMerchant(call, result);
                break;
            case "verify":
                verify(call, result);
                break;
            case "decode":
                decode(call, result);
                break;
            case "decodeNonKhqr":
                decodeNonKhqr(call, result);
                break;
            case "generateDeepLink":
                generateDeepLink(call, result);
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    private void generateIndividual(MethodCall call, Result result) {
        if (call.arguments == null) {
            result.error("GENERATE_INDIVIDUAL_ERROR", "Missing parameter", null);
            return;
        }

        String bakongAccountId = call.argument("bakongAccountId");
        String merchantName = call.argument("merchantName");
        Double amount = call.argument("amount");
        String currency = call.argument("currency");
        String accountInformation = call.argument("accountInformation");
        String acquiringBank = call.argument("acquiringBank");
        String merchantCity = call.argument("merchantCity");
        String billNumber = call.argument("billNumber");
        String mobileNumber = call.argument("mobileNumber");
        String storeLabel = call.argument("storeLabel");
        String terminalLabel = call.argument("terminalLabel");
        String upiAccountInformation = call.argument("upiAccountInformation");
        String purposeOfTransaction = call.argument("purposeOfTransaction");
        String merchantAlternateLanguagePreference = call.argument("merchantAlternateLanguagePreference");
        String merchantNameAlternateLanguage = call.argument("merchantNameAlternateLanguage");
        String merchantCityAlternateLanguage = call.argument("merchantCityAlternateLanguage");
        Long expirationTimestamp = call.argument("expirationTimestamp");

        if (amount != null && amount == 0) {
            amount = null;
        }
        IndividualInfo individualInfo = new IndividualInfo();
        individualInfo.setBakongAccountId(bakongAccountId);
        individualInfo.setMerchantName(merchantName);
        individualInfo.setAmount(amount);
        individualInfo.setCurrency("khr".equals(currency) ? KHQRCurrency.KHR : KHQRCurrency.USD);
        individualInfo.setAccountInformation(accountInformation);
        individualInfo.setAcquiringBank(acquiringBank);
        individualInfo.setMerchantCity(merchantCity);
        individualInfo.setBillNumber(billNumber);
        individualInfo.setMobileNumber(mobileNumber);
        individualInfo.setStoreLabel(storeLabel);
        individualInfo.setTerminalLabel(terminalLabel);
        individualInfo.setUpiAccountInformation(upiAccountInformation);
        individualInfo.setPurposeOfTransaction(purposeOfTransaction);
        individualInfo.setMerchantAlternateLanguagePreference(merchantAlternateLanguagePreference);
        individualInfo.setMerchantNameAlternateLanguage(merchantNameAlternateLanguage);
        individualInfo.setMerchantCityAlternateLanguage(merchantCityAlternateLanguage);
        individualInfo.setExpirationTimestamp(expirationTimestamp);

        KHQRResponse<KHQRData> response = BakongKHQR.generateIndividual(individualInfo);
        if (response.getKHQRStatus().getCode() == 0) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(response.getData());
            result.success(jsonString);
        } else {
            result.error("GENERATE_INDIVIDUAL_ERROR", response.getKHQRStatus().getMessage(), null);
        }
    }

    private void generateMerchant(MethodCall call, Result result) {
        if (call.arguments == null) {
            result.error("GENERATE_MERCHANT_ERROR", "Missing parameter", null);
            return;
        }

        String bakongAccountId = call.argument("bakongAccountId");
        Double amount = call.argument("amount");
        String currency = call.argument("currency");
        String acquiringBank = call.argument("acquiringBank");
        String merchantId = call.argument("merchantId");
        String merchantName = call.argument("merchantName");
        String merchantCity = call.argument("merchantCity");
        String billNumber = call.argument("billNumber");
        String mobileNumber = call.argument("mobileNumber");
        String storeLabel = call.argument("storeLabel");
        String terminalLabel = call.argument("terminalLabel");
        String upiAccountInformation = call.argument("upiAccountInformation");
        String purposeOfTransaction = call.argument("purposeOfTransaction");
        String merchantAlternateLanguagePreference = call.argument("merchantAlternateLanguagePreference");
        String merchantNameAlternateLanguage = call.argument("merchantNameAlternateLanguage");
        String merchantCityAlternateLanguage = call.argument("merchantCityAlternateLanguage");
        Long expirationTimestamp = call.argument("expirationTimestamp");
        if (amount != null && amount == 0) {
            amount = null;
        }
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setBakongAccountId(bakongAccountId);
        merchantInfo.setAmount(amount);
        merchantInfo.setCurrency("khr".equals(currency) ? KHQRCurrency.KHR : KHQRCurrency.USD);
        merchantInfo.setAcquiringBank(acquiringBank);
        merchantInfo.setMerchantId(merchantId);
        merchantInfo.setMerchantName(merchantName);
        merchantInfo.setMerchantCity(merchantCity);
        merchantInfo.setBillNumber(billNumber);
        merchantInfo.setMobileNumber(mobileNumber);
        merchantInfo.setStoreLabel(storeLabel);
        merchantInfo.setTerminalLabel(terminalLabel);
        merchantInfo.setUpiAccountInformation(upiAccountInformation);
        merchantInfo.setPurposeOfTransaction(purposeOfTransaction);
        merchantInfo.setMerchantAlternateLanguagePreference(merchantAlternateLanguagePreference);
        merchantInfo.setMerchantNameAlternateLanguage(merchantNameAlternateLanguage);
        merchantInfo.setMerchantCityAlternateLanguage(merchantCityAlternateLanguage);
        merchantInfo.setExpirationTimestamp(expirationTimestamp);

        KHQRResponse<KHQRData> response = BakongKHQR.generateMerchant(merchantInfo);
        if (response.getKHQRStatus().getCode() == 0) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(response.getData());
            result.success(jsonString);
        } else {
            result.error("GENERATE_MERCHANT_ERROR", response.getKHQRStatus().getMessage(), null);
        }
    }

    private void verify(MethodCall call, Result result) {
        if (call.arguments == null) {
            result.error("VERIFY_ERROR", "Missing parameter", null);
            return;
        }

        String qrCode = call.argument("qrCode");
        KHQRResponse<CRCValidation> response = BakongKHQR.verify(qrCode);
        if (response.getKHQRStatus().getCode() == 0) {
            result.success(response.getData().isValid());
        } else {
            result.error("VERIFY_ERROR", response.getKHQRStatus().getMessage(), null);
        }
    }

    private void decode(MethodCall call, Result result) {
        if (call.arguments == null) {
            result.error("DECODE_ERROR", "Missing parameter", null);
            return;
        }

        String qrCode = call.argument("qrCode");
        KHQRResponse<KHQRDecodeData> response = BakongKHQR.decode(qrCode);
        Gson gson = new Gson();
        String jsonString = gson.toJson(response.getData());
        jsonString = jsonString.replace("\"bakongAccountID\":", "\"bakongAccountId\":");
        result.success(jsonString);
    }

    private void decodeNonKhqr(MethodCall call, Result result) {
        if (call.arguments == null) {
            result.error("DECODE_ERROR", "Missing parameter", null);
            return;
        }

        // Not implemented for Android
        result.error("DECODE_ERROR", "Decode Non-KHQR not yet available in Android platform", null);
    }

    private void generateDeepLink(MethodCall call, Result result) {
        if (call.arguments == null) {
            result.error("GENERATE_DEEPLINK_ERROR", "Missing parameter", null);
            return;
        }

        String url = call.argument("url");
        String qr = call.argument("qr");
        String appName = call.argument("appName");
        String appIconUrl = call.argument("appIconUrl");
        String appDeepLinkCallback = call.argument("appDeepLinkCallBack");

        SourceInfo sourceInfo = new SourceInfo();
        sourceInfo.setAppName(appName);
        sourceInfo.setAppIconUrl(appIconUrl);
        sourceInfo.setAppDeepLinkCallback(appDeepLinkCallback);

        KHQRResponse<KHQRDeepLinkData> response = BakongKHQR.generateDeepLink(url, qr, sourceInfo);
        if (response.getKHQRStatus().getCode() == 0) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(response.getData());
            result.success(jsonString);
        } else {
            result.error("GENERATE_DEEPLINK_ERROR", response.getKHQRStatus().getMessage(), null);
        }
    }
}
