package com.midtrans.sdk.corekit.core.payment;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.midtrans.sdk.corekit.MidtransSdk;
import com.midtrans.sdk.corekit.SDKConfigTest;
import com.midtrans.sdk.corekit.base.callback.MidtransCallback;
import com.midtrans.sdk.corekit.base.enums.Environment;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.request.CustomerDetailPayRequest;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.request.creditcard.CreditCardPaymentParams;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.request.mandiriclick.MandiriClickpayParams;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.BankTransferVaBcaPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.BankTransferVaBniPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.BankTransferVaOtherPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.BankTransferVaPermataPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.CardlessCreditAkulakuPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.ConvenienceStoreIndomaretPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.CreditCardPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.DirectDebitKlikBcaResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.DirectDebitMandiriClickpayResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.EwalletGopayPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.EwalletMandiriEcashPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.EwalletTelkomselCashPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.OnlineDebitBcaKlikpayPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.OnlineDebitBriEpayPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.OnlineDebitCimbClicksPaymentResponse;
import com.midtrans.sdk.corekit.core.api.snap.model.pay.response.OnlineDebitDanamonOnlinePaymentResponse;
import com.midtrans.sdk.corekit.utilities.Helper;
import com.midtrans.sdk.corekit.utilities.Logger;
import com.midtrans.sdk.corekit.utilities.NetworkHelper;
import com.midtrans.sdk.corekit.utilities.ValidationHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@SuppressWarnings("ALL")
@RunWith(PowerMockRunner.class)
@PrepareForTest({NetworkHelper.class,
        Looper.class,
        Helper.class,
        Log.class,
        TextUtils.class,
        Logger.class,
        ValidationHelper.class})
public class PaymentChargeTest {

    @Mock
    private BankTransferCharge bankTransferCharge;
    @Mock
    private CardlessCreditCharge cardlessCreditCharge;
    @Mock
    private DirectDebitCharge directDebitCharge;
    @Mock
    private EWalletCharge eWalletCharge;
    @Mock
    private OnlineDebitCharge onlineDebitCharge;
    @Mock
    private ConvenienceStoreCharge convenienceStoreCharge;
    @Mock
    private CreditCardCharge creditCardCharge;

    @Mock
    private Context contextMock;
    @Mock
    private MidtransSdk midtransSdkMock;
    @Mock
    private Throwable throwable;
    @Mock
    private CustomerDetailPayRequest customerDetailPayRequest;
    @Mock
    private CreditCardPaymentParams creditCardPaymentParams;
    @Mock
    private String userId, customerNumber;
    @Mock
    private MandiriClickpayParams mandiriClickpayParams;

    @Mock
    private BankTransferVaBcaPaymentResponse responseBcaVaMock;
    @Mock
    private MidtransCallback<BankTransferVaBcaPaymentResponse> callbackBcaVaMock;
    @Mock
    private BankTransferVaPermataPaymentResponse responsePermataVaMock;
    @Mock
    private MidtransCallback<BankTransferVaPermataPaymentResponse> callbackPermataVaMock;
    @Mock
    private BankTransferVaBniPaymentResponse responseBniVaMock;
    @Mock
    private MidtransCallback<BankTransferVaBniPaymentResponse> callbackBniVaMock;
    @Mock
    private BankTransferVaOtherPaymentResponse responseOtherVaMock;
    @Mock
    private MidtransCallback<BankTransferVaOtherPaymentResponse> callbackOtherVaMock;
    @Mock
    private CardlessCreditAkulakuPaymentResponse responseAkulakuMock;
    @Mock
    private MidtransCallback<CardlessCreditAkulakuPaymentResponse> callbacAkulakuMock;
    @Mock
    private DirectDebitKlikBcaResponse responseKlikBca;
    @Mock
    private MidtransCallback<DirectDebitKlikBcaResponse> callbackKlikBca;
    @Mock
    private DirectDebitMandiriClickpayResponse responseMandiriClick;
    @Mock
    private MidtransCallback<DirectDebitMandiriClickpayResponse> callbackMandiriClick;
    @Mock
    private EwalletTelkomselCashPaymentResponse responseTelkomsel;
    @Mock
    private MidtransCallback<EwalletTelkomselCashPaymentResponse> callbackTelkomsel;
    @Mock
    private EwalletMandiriEcashPaymentResponse responseMandiriEcash;
    @Mock
    private MidtransCallback<EwalletMandiriEcashPaymentResponse> callbackMandiriEcash;
    @Mock
    private EwalletGopayPaymentResponse responseGopay;
    @Mock
    private MidtransCallback<EwalletGopayPaymentResponse> callbackGopay;
    @Mock
    private OnlineDebitCimbClicksPaymentResponse responseCimb;
    @Mock
    private MidtransCallback<OnlineDebitCimbClicksPaymentResponse> callbackCimb;
    @Mock
    private OnlineDebitBcaKlikpayPaymentResponse responseBcaKlikpay;
    @Mock
    private MidtransCallback<OnlineDebitBcaKlikpayPaymentResponse> callbackBcaKlikpay;
    @Mock
    private OnlineDebitBriEpayPaymentResponse responseBriEpay;
    @Mock
    private MidtransCallback<OnlineDebitBriEpayPaymentResponse> callbackBriEpay;
    @Mock
    private OnlineDebitDanamonOnlinePaymentResponse responseDanamonOnline;
    @Mock
    private MidtransCallback<OnlineDebitDanamonOnlinePaymentResponse> callbackDanamonOnline;
    @Mock
    private ConvenienceStoreIndomaretPaymentResponse responseIndomaret;
    @Mock
    private MidtransCallback<ConvenienceStoreIndomaretPaymentResponse> callbackIndomaret;
    @Mock
    private CreditCardPaymentResponse responseCreditCard;
    @Mock
    private MidtransCallback<CreditCardPaymentResponse> callbackCreditCard;

    @Before
    public void setup() {
        PowerMockito.mockStatic(MidtransCallback.class);
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.mockStatic(Log.class);
        PowerMockito.mockStatic(Logger.class);
        PowerMockito.mockStatic(Looper.class);
        PowerMockito.mockStatic(Helper.class);
        PowerMockito.mockStatic(NetworkHelper.class);
        PowerMockito.mockStatic(ValidationHelper.class);

        Mockito.when(TextUtils.isEmpty(Matchers.anyString())).thenReturn(false);
        Mockito.when(TextUtils.isEmpty(null)).thenReturn(true);
        Mockito.when(TextUtils.isEmpty("")).thenReturn(true);

        MidtransSdk midtransSdk = MidtransSdk.builder(contextMock,
                SDKConfigTest.CLIENT_KEY,
                SDKConfigTest.MERCHANT_BASE_URL)
                .setLogEnabled(true)
                .setEnvironment(Environment.SANDBOX)
                .build();

        midtransSdkMock = midtransSdk;
        callbackBcaVaMock.onSuccess(responseBcaVaMock);
        callbackBcaVaMock.onFailed(throwable);
        callbackPermataVaMock.onSuccess(responsePermataVaMock);
        callbackPermataVaMock.onFailed(throwable);
        callbackBniVaMock.onSuccess(responseBniVaMock);
        callbackBniVaMock.onFailed(throwable);
        callbackOtherVaMock.onSuccess(responseOtherVaMock);
        callbackOtherVaMock.onFailed(throwable);
        callbacAkulakuMock.onSuccess(responseAkulakuMock);
        callbacAkulakuMock.onFailed(throwable);
        callbackKlikBca.onSuccess(responseKlikBca);
        callbackKlikBca.onFailed(throwable);
        callbackMandiriClick.onSuccess(responseMandiriClick);
        callbackMandiriClick.onFailed(throwable);
        callbackTelkomsel.onSuccess(responseTelkomsel);
        callbackTelkomsel.onFailed(throwable);
        callbackMandiriEcash.onSuccess(responseMandiriEcash);
        callbackMandiriEcash.onFailed(throwable);
        callbackGopay.onSuccess(responseGopay);
        callbackGopay.onFailed(throwable);
        callbackCimb.onSuccess(responseCimb);
        callbackCimb.onFailed(throwable);
        callbackBcaKlikpay.onSuccess(responseBcaKlikpay);
        callbackBcaKlikpay.onFailed(throwable);
        callbackBriEpay.onSuccess(responseBriEpay);
        callbackBriEpay.onFailed(throwable);
        callbackIndomaret.onSuccess(responseIndomaret);
        callbackIndomaret.onFailed(throwable);
        callbackCreditCard.onSuccess(responseCreditCard);
        callbackCreditCard.onFailed(throwable);
        callbackDanamonOnline.onSuccess(responseDanamonOnline);
        callbackDanamonOnline.onFailed(throwable);
    }

    @Test
    public void test_paymentUsingBankTransferVaBca_positive() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(true);
        bankTransferCharge.paymentUsingBankTransferVaBca(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackBcaVaMock);
        Mockito.verify(callbackBcaVaMock).onSuccess(Matchers.any(BankTransferVaBcaPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaBca_negative_callback() {
        bankTransferCharge.paymentUsingBankTransferVaBca(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackBcaVaMock);
        Mockito.verify(callbackBcaVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaBca_negative_snapTokenNull() {
        bankTransferCharge.paymentUsingBankTransferVaBca(null, customerDetailPayRequest, callbackBcaVaMock);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingBankTransferVaBca_negative_withoutParams() {
        bankTransferCharge.paymentUsingBankTransferVaBca(SDKConfigTest.SNAP_TOKEN, null, callbackBcaVaMock);
        Mockito.verify(callbackBcaVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaBca_negative_withoutSnapTken() {
        bankTransferCharge.paymentUsingBankTransferVaBca(null, customerDetailPayRequest, null);
        Mockito.verify(callbackBcaVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaBca_negative_callbackNull() {
        bankTransferCharge.paymentUsingBankTransferVaBca(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingBankTransferVaBca_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        bankTransferCharge.paymentUsingBankTransferVaBca(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackBcaVaMock);
        Mockito.verify(callbackBcaVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaPermata_positive() {
        bankTransferCharge.paymentUsingBankTransferVaPermata(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackPermataVaMock);
        Mockito.verify(callbackPermataVaMock).onSuccess(Matchers.any(BankTransferVaPermataPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaPermata_negative_callback() {
        bankTransferCharge.paymentUsingBankTransferVaPermata(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackPermataVaMock);
        Mockito.verify(callbackPermataVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaPermata_negative_snapTokenNull() {
        bankTransferCharge.paymentUsingBankTransferVaPermata(null, customerDetailPayRequest, callbackPermataVaMock);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingBankTransferVaPermata_negative_callbackNull() {
        bankTransferCharge.paymentUsingBankTransferVaPermata(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingBankTransferVaPermata_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        bankTransferCharge.paymentUsingBankTransferVaPermata(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackPermataVaMock);
        Mockito.verify(callbackPermataVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaPermata_negative_withoutParams() {
        bankTransferCharge.paymentUsingBankTransferVaPermata(SDKConfigTest.SNAP_TOKEN, null, callbackPermataVaMock);
        Mockito.verify(callbackPermataVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaPermata_negative_withoutSnapTken() {
        bankTransferCharge.paymentUsingBankTransferVaPermata(null, customerDetailPayRequest, callbackPermataVaMock);
        Mockito.verify(callbackPermataVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaBni_positive() {
        bankTransferCharge.paymentUsingBankTransferVaBni(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackBniVaMock);
        Mockito.verify(callbackBniVaMock).onSuccess(Matchers.any(BankTransferVaBniPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaBni_negative_callback() {
        bankTransferCharge.paymentUsingBankTransferVaBni(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackBniVaMock);
        Mockito.verify(callbackBniVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaBni_negative_snapTokenNull() {
        bankTransferCharge.paymentUsingBankTransferVaBni(null, customerDetailPayRequest, callbackBniVaMock);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingBankTransferVaBni_negative_callbackNull() {
        bankTransferCharge.paymentUsingBankTransferVaBni(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingBankTransferVaBni_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        bankTransferCharge.paymentUsingBankTransferVaBni(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackBniVaMock);
        Mockito.verify(callbackBniVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaBni_negative_withoutParams() {
        bankTransferCharge.paymentUsingBankTransferVaBni(SDKConfigTest.SNAP_TOKEN, null, callbackBniVaMock);
        Mockito.verify(callbackBniVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingBankTransferVaBni_negative_withoutSnapTken() {
        bankTransferCharge.paymentUsingBankTransferVaBni(null, customerDetailPayRequest, null);
        Mockito.verify(callbackBniVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOtherVa_positive() {
        bankTransferCharge.paymentUsingBankTransferVaOther(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackOtherVaMock);
        Mockito.verify(callbackOtherVaMock).onSuccess(Matchers.any(BankTransferVaOtherPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingOtherVa_negative_callback() {
        bankTransferCharge.paymentUsingBankTransferVaOther(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackOtherVaMock);
        Mockito.verify(callbackOtherVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOtherVa_negative_snapTokenNull() {
        bankTransferCharge.paymentUsingBankTransferVaOther(null, customerDetailPayRequest, callbackOtherVaMock);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingOtherVa_negative_callbackNull() {
        bankTransferCharge.paymentUsingBankTransferVaOther(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingOtherVa_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        bankTransferCharge.paymentUsingBankTransferVaOther(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackOtherVaMock);
        Mockito.verify(callbackOtherVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOtherVa_negative_withoutParams() {
        bankTransferCharge.paymentUsingBankTransferVaOther(SDKConfigTest.SNAP_TOKEN, null, callbackOtherVaMock);
        Mockito.verify(callbackOtherVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOtherVa_negative_withoutSnapTken() {
        bankTransferCharge.paymentUsingBankTransferVaOther(null, customerDetailPayRequest, null);
        Mockito.verify(callbackOtherVaMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingCardlessCreditAkulaku_positive() {
        cardlessCreditCharge.paymentUsingAkulaku(SDKConfigTest.SNAP_TOKEN, callbacAkulakuMock);
        Mockito.verify(callbacAkulakuMock).onSuccess(Matchers.any(CardlessCreditAkulakuPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingCardlessCreditAkulaku_negative_callback() {
        cardlessCreditCharge.paymentUsingAkulaku(SDKConfigTest.SNAP_TOKEN, callbacAkulakuMock);
        Mockito.verify(callbacAkulakuMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingCardlessCreditAkulaku_negative_snapTokenNull() {
        cardlessCreditCharge.paymentUsingAkulaku(null, callbacAkulakuMock);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingCardlessCreditAkulaku_negative_callbackNull() {
        cardlessCreditCharge.paymentUsingAkulaku(SDKConfigTest.SNAP_TOKEN, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingCardlessCreditAkulaku_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        cardlessCreditCharge.paymentUsingAkulaku(SDKConfigTest.SNAP_TOKEN, callbacAkulakuMock);
        Mockito.verify(callbacAkulakuMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingCardlessCreditAkulaku_negative_withoutSnapToken() {
        cardlessCreditCharge.paymentUsingAkulaku(null, callbacAkulakuMock);
        Mockito.verify(callbacAkulakuMock).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingDirectDebitKlikBca_positive() {
        directDebitCharge.paymentUsingKlikBca(SDKConfigTest.SNAP_TOKEN, userId, callbackKlikBca);
        Mockito.verify(callbackKlikBca).onSuccess(Matchers.any(DirectDebitKlikBcaResponse.class));
    }

    @Test
    public void test_paymentUsingDirectDebitKlikBca_negative_callback() {
        directDebitCharge.paymentUsingKlikBca(SDKConfigTest.SNAP_TOKEN, userId, callbackKlikBca);
        Mockito.verify(callbackKlikBca).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingDirectDebitKlikBca_negative_snapTokenNull() {
        directDebitCharge.paymentUsingKlikBca(null, userId, callbackKlikBca);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingDirectDebitKlikBca_negative_callbackNull() {
        directDebitCharge.paymentUsingKlikBca(SDKConfigTest.SNAP_TOKEN, userId, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingDirectDebitKlikBca_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        directDebitCharge.paymentUsingKlikBca(SDKConfigTest.SNAP_TOKEN, userId, callbackKlikBca);
        Mockito.verify(callbackKlikBca).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingDirectDebitKlikBca_negative_withoutParams() {
        directDebitCharge.paymentUsingKlikBca(SDKConfigTest.SNAP_TOKEN, null, callbackKlikBca);
        Mockito.verify(callbackKlikBca).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingDirectDebitKlikBca_negative_withoutSnapTken() {
        directDebitCharge.paymentUsingKlikBca(null, userId, null);
        Mockito.verify(callbackKlikBca).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingDirectDebitMandiriClickPay_positive() {
        directDebitCharge.paymentUsingMandiriClickPay(SDKConfigTest.SNAP_TOKEN, mandiriClickpayParams, callbackMandiriClick);
        Mockito.verify(callbackMandiriClick).onSuccess(Matchers.any(DirectDebitMandiriClickpayResponse.class));
    }

    @Test
    public void test_paymentUsingDirectDebitMandiriClickPay_negative_callback() {
        directDebitCharge.paymentUsingMandiriClickPay(SDKConfigTest.SNAP_TOKEN, mandiriClickpayParams, callbackMandiriClick);
        Mockito.verify(callbackMandiriClick).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingDirectDebitMandiriClickPay_negative_snapTokenNull() {
        directDebitCharge.paymentUsingMandiriClickPay(null, mandiriClickpayParams, callbackMandiriClick);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingDirectDebitMandiriClickPay_negative_callbackNull() {
        directDebitCharge.paymentUsingMandiriClickPay(SDKConfigTest.SNAP_TOKEN, mandiriClickpayParams, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingDirectDebitMandiriClickPay_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        directDebitCharge.paymentUsingMandiriClickPay(SDKConfigTest.SNAP_TOKEN, mandiriClickpayParams, callbackMandiriClick);
        Mockito.verify(callbackMandiriClick).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingDirectDebitMandiriClickpay_negative_withoutParams() {
        directDebitCharge.paymentUsingMandiriClickPay(SDKConfigTest.SNAP_TOKEN, null, callbackMandiriClick);
        Mockito.verify(callbackMandiriClick).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingDirectDebitMandiriClickpay_negative_withoutSnapTken() {
        directDebitCharge.paymentUsingMandiriClickPay(null, mandiriClickpayParams, null);
        Mockito.verify(callbackMandiriClick).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletTelkkomselCash_positive() {
        eWalletCharge.paymentUsingTelkomselCash(SDKConfigTest.SNAP_TOKEN, customerNumber, callbackTelkomsel);
        Mockito.verify(callbackTelkomsel).onSuccess(Matchers.any(EwalletTelkomselCashPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingEwalletTelkkomselCash_negative_callback() {
        eWalletCharge.paymentUsingTelkomselCash(SDKConfigTest.SNAP_TOKEN, customerNumber, callbackTelkomsel);
        Mockito.verify(callbackTelkomsel).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletTelkkomselCash_negative_snapTokenNull() {
        eWalletCharge.paymentUsingTelkomselCash(null, customerNumber, callbackTelkomsel);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingEwalletTelkkomselCash_negative_callbackNull() {
        eWalletCharge.paymentUsingTelkomselCash(SDKConfigTest.SNAP_TOKEN, customerNumber, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingEwalletTelkkomselCash_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        eWalletCharge.paymentUsingTelkomselCash(SDKConfigTest.SNAP_TOKEN, customerNumber, callbackTelkomsel);
        Mockito.verify(callbackTelkomsel).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletTelkkomselCash_negative_withoutParams() {
        eWalletCharge.paymentUsingTelkomselCash(SDKConfigTest.SNAP_TOKEN, null, callbackTelkomsel);
        Mockito.verify(callbackTelkomsel).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletTelkkomselCash_negative_withoutSnapTken() {
        eWalletCharge.paymentUsingTelkomselCash(null, customerNumber, null);
        Mockito.verify(callbackTelkomsel).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletMandiriEcash_positive() {
        eWalletCharge.paymentUsingMandiriEcash(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackMandiriEcash);
        Mockito.verify(callbackMandiriEcash).onSuccess(Matchers.any(EwalletMandiriEcashPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingEwalletMandiriEcash_negative_callback() {
        eWalletCharge.paymentUsingMandiriEcash(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackMandiriEcash);
        Mockito.verify(callbackMandiriEcash).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletMandiriEcash_negative_snapTokenNull() {
        eWalletCharge.paymentUsingMandiriEcash(null, customerDetailPayRequest, callbackMandiriEcash);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingEwalletMandiriEcash_negative_callbackNull() {
        eWalletCharge.paymentUsingMandiriEcash(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingEwalletMandiriEcash_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        eWalletCharge.paymentUsingMandiriEcash(SDKConfigTest.SNAP_TOKEN, customerDetailPayRequest, callbackMandiriEcash);
        Mockito.verify(callbackMandiriEcash).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletMandiriEcash_negative_withoutParams() {
        eWalletCharge.paymentUsingMandiriEcash(SDKConfigTest.SNAP_TOKEN, null, callbackMandiriEcash);
        Mockito.verify(callbackMandiriEcash).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletMandiriEcash_negative_withoutSnapTken() {
        eWalletCharge.paymentUsingMandiriEcash(null, customerDetailPayRequest, null);
        Mockito.verify(callbackMandiriEcash).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletGopay_positive() {
        eWalletCharge.paymentUsingGopay(SDKConfigTest.SNAP_TOKEN, customerNumber, callbackGopay);
        Mockito.verify(callbackGopay).onSuccess(Matchers.any(EwalletGopayPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingEwalletGopay_negative_callback() {
        eWalletCharge.paymentUsingGopay(SDKConfigTest.SNAP_TOKEN, customerNumber, callbackGopay);
        Mockito.verify(callbackGopay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletGopay_negative_snapTokenNull() {
        eWalletCharge.paymentUsingGopay(null, customerNumber, callbackGopay);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingEwalletGopay_negative_callbackNull() {
        eWalletCharge.paymentUsingGopay(SDKConfigTest.SNAP_TOKEN, customerNumber, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingEwalletGopay_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        eWalletCharge.paymentUsingGopay(SDKConfigTest.SNAP_TOKEN, customerNumber, callbackGopay);
        Mockito.verify(callbackGopay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletGopay_negative_withoutParams() {
        eWalletCharge.paymentUsingGopay(SDKConfigTest.SNAP_TOKEN, null, callbackGopay);
        Mockito.verify(callbackGopay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingEwalletGopay_negative_withoutSnapTken() {
        eWalletCharge.paymentUsingGopay(null, customerNumber, null);
        Mockito.verify(callbackGopay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeCimbClicks_positive() {
        onlineDebitCharge.paymentUsingCimbClicks(SDKConfigTest.SNAP_TOKEN, callbackCimb);
        Mockito.verify(callbackCimb).onSuccess(Matchers.any(OnlineDebitCimbClicksPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeCimbClicks_negative_callback() {
        onlineDebitCharge.paymentUsingCimbClicks(SDKConfigTest.SNAP_TOKEN, callbackCimb);
        Mockito.verify(callbackCimb).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeCimbClicks_negative_snapTokenNull() {
        onlineDebitCharge.paymentUsingCimbClicks(null, callbackCimb);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeCimbClicks_negative_callbackNull() {
        onlineDebitCharge.paymentUsingCimbClicks(SDKConfigTest.SNAP_TOKEN, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeCimbClicks_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        onlineDebitCharge.paymentUsingCimbClicks(SDKConfigTest.SNAP_TOKEN, callbackCimb);
        Mockito.verify(callbackCimb).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeCimbClicks_negative_withoutSnapToken() {
        onlineDebitCharge.paymentUsingCimbClicks(null, callbackCimb);
        Mockito.verify(callbackCimb).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBcaClickPay_positive() {
        onlineDebitCharge.paymentUsingBcaKlikpay(SDKConfigTest.SNAP_TOKEN, callbackBcaKlikpay);
        Mockito.verify(callbackBcaKlikpay).onSuccess(Matchers.any(OnlineDebitBcaKlikpayPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBcaClickPay_negative() {
        onlineDebitCharge.paymentUsingBcaKlikpay(SDKConfigTest.SNAP_TOKEN, callbackBcaKlikpay);
        Mockito.verify(callbackBcaKlikpay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBcaClickPay_negative_callback() {
        onlineDebitCharge.paymentUsingBcaKlikpay(SDKConfigTest.SNAP_TOKEN, callbackBcaKlikpay);
        Mockito.verify(callbackBcaKlikpay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBcaClickPay_negative_snapTokenNull() {
        onlineDebitCharge.paymentUsingBcaKlikpay(null, callbackBcaKlikpay);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBcaClickPay_negative_callbackNull() {
        onlineDebitCharge.paymentUsingBcaKlikpay(SDKConfigTest.SNAP_TOKEN, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBcaClickPay_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        onlineDebitCharge.paymentUsingBcaKlikpay(SDKConfigTest.SNAP_TOKEN, callbackBcaKlikpay);
        Mockito.verify(callbackBcaKlikpay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBcaClickPay_negative_withoutSnapToken() {
        onlineDebitCharge.paymentUsingBcaKlikpay(null, callbackBcaKlikpay);
        Mockito.verify(callbackBcaKlikpay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeDanamonOnline_positive() {
        onlineDebitCharge.paymentUsingDanamonOnline(SDKConfigTest.SNAP_TOKEN, callbackDanamonOnline);
        Mockito.verify(callbackDanamonOnline).onSuccess(Matchers.any(OnlineDebitDanamonOnlinePaymentResponse.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeDanamonOnline_negative() {
        onlineDebitCharge.paymentUsingDanamonOnline(SDKConfigTest.SNAP_TOKEN, callbackDanamonOnline);
        Mockito.verify(callbackDanamonOnline).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeDanamonOnline_negative_callback() {
        onlineDebitCharge.paymentUsingDanamonOnline(SDKConfigTest.SNAP_TOKEN, callbackDanamonOnline);
        Mockito.verify(callbackDanamonOnline).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeDanamonOnline_negative_snapTokenNull() {
        onlineDebitCharge.paymentUsingDanamonOnline(null, callbackDanamonOnline);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeDanamonOnline_negative_callbackNull() {
        onlineDebitCharge.paymentUsingDanamonOnline(SDKConfigTest.SNAP_TOKEN, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeDanamonOnline_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        onlineDebitCharge.paymentUsingDanamonOnline(SDKConfigTest.SNAP_TOKEN, callbackDanamonOnline);
        Mockito.verify(callbackDanamonOnline).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeDanamonOnline_negative_withoutSnapToken() {
        onlineDebitCharge.paymentUsingDanamonOnline(null, callbackDanamonOnline);
        Mockito.verify(callbackDanamonOnline).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBriEpay_positive() {
        onlineDebitCharge.paymentUsingBriEpay(SDKConfigTest.SNAP_TOKEN, callbackBriEpay);
        Mockito.verify(callbackBriEpay).onSuccess(Matchers.any(OnlineDebitBriEpayPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBriEpay_negative_callback() {
        onlineDebitCharge.paymentUsingBriEpay(SDKConfigTest.SNAP_TOKEN, callbackBriEpay);
        Mockito.verify(callbackBriEpay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBriEpay_negative_snapTokenNull() {
        onlineDebitCharge.paymentUsingBriEpay(null, callbackBriEpay);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBriEpay_negative_callbackNull() {
        onlineDebitCharge.paymentUsingBriEpay(SDKConfigTest.SNAP_TOKEN, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBriEpay_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        onlineDebitCharge.paymentUsingBriEpay(SDKConfigTest.SNAP_TOKEN, callbackBriEpay);
        Mockito.verify(callbackBriEpay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingOnlineDebitChargeBriEpay_negative_withoutSnapTken() {
        onlineDebitCharge.paymentUsingBriEpay(null, callbackBriEpay);
        Mockito.verify(callbackBriEpay).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingStoreChange_positive() {
        convenienceStoreCharge.paymentUsingIndomaret(SDKConfigTest.SNAP_TOKEN, callbackIndomaret);
        Mockito.verify(callbackIndomaret).onSuccess(Matchers.any(ConvenienceStoreIndomaretPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingStoreChange_negative_callback() {
        convenienceStoreCharge.paymentUsingIndomaret(SDKConfigTest.SNAP_TOKEN, callbackIndomaret);
        Mockito.verify(callbackIndomaret).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingStoreChange_negative_snapTokenNull() {
        convenienceStoreCharge.paymentUsingIndomaret(null, callbackIndomaret);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingStoreChange_negative_callbackNull() {
        convenienceStoreCharge.paymentUsingIndomaret(SDKConfigTest.SNAP_TOKEN, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingStoreChange_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        convenienceStoreCharge.paymentUsingIndomaret(SDKConfigTest.SNAP_TOKEN, callbackIndomaret);
        Mockito.verify(callbackIndomaret).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingStoreChange_negative_withoutSnapTken() {
        convenienceStoreCharge.paymentUsingIndomaret(null, callbackIndomaret);
        Mockito.verify(callbackIndomaret).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingCreditCard_positive() {
        creditCardCharge.paymentUsingCard(SDKConfigTest.SNAP_TOKEN, creditCardPaymentParams, customerDetailPayRequest, callbackCreditCard);
        Mockito.verify(callbackCreditCard).onSuccess(Matchers.any(CreditCardPaymentResponse.class));
    }

    @Test
    public void test_paymentUsingCreditCard_negative_callback() {
        creditCardCharge.paymentUsingCard(SDKConfigTest.SNAP_TOKEN, creditCardPaymentParams, customerDetailPayRequest, callbackCreditCard);
        Mockito.verify(callbackCreditCard).onFailed(Matchers.any(Throwable.class));
    }

    @Test
    public void test_paymentUsingCreditCard_negative_snapTokenNull() {
        creditCardCharge.paymentUsingCard(null, creditCardPaymentParams, customerDetailPayRequest, callbackCreditCard);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingCreditCard_negative_callbackNull() {
        creditCardCharge.paymentUsingCard(SDKConfigTest.SNAP_TOKEN, creditCardPaymentParams, customerDetailPayRequest, null);
        verifyStatic(Mockito.times(0));
        Logger.error(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void test_paymentUsingCreditCard_negative_noNetwork() {
        when(NetworkHelper.isNetworkAvailable(midtransSdkMock.getContext())).thenReturn(false);
        creditCardCharge.paymentUsingCard(SDKConfigTest.SNAP_TOKEN, creditCardPaymentParams, customerDetailPayRequest, callbackCreditCard);
        Mockito.verify(callbackCreditCard).onFailed(Matchers.any(Throwable.class));
    }

}