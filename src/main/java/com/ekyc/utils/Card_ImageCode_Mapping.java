package com.ekyc.utils;

import com.ekyc.enums.IdVerificationType;
import com.ekyc.enums.ImageCodes;

import java.util.HashMap;
import java.util.List;

public class Card_ImageCode_Mapping {
    public static HashMap<IdVerificationType, List<ImageCodes>> getCodesWithCardName() {
        HashMap<IdVerificationType, List<ImageCodes>> cardTypeToCodes = new HashMap<>();
        cardTypeToCodes.put(IdVerificationType.DriverLicenseCard, List.of(ImageCodes.DC1, ImageCodes.DC3, ImageCodes.DC2));
        cardTypeToCodes.put(IdVerificationType.MyNumberCard, List.of(ImageCodes.MN1, ImageCodes.MN3));
        cardTypeToCodes.put(IdVerificationType.NotificationCard, List.of(ImageCodes.MI1, ImageCodes.MI2));
        cardTypeToCodes.put(IdVerificationType.HealthInsuranceCard, List.of(ImageCodes.IC1, ImageCodes.IC3, ImageCodes.IC2));
        cardTypeToCodes.put(IdVerificationType.Passport, List.of(ImageCodes.PP1, ImageCodes.PP3));
        cardTypeToCodes.put(IdVerificationType.DrivingRecordCertificate, List.of(ImageCodes.CC1, ImageCodes.CC3, ImageCodes.CC2));
        cardTypeToCodes.put(IdVerificationType.ResidentRegistryCard, List.of(ImageCodes.BR1, ImageCodes.BR3, ImageCodes.BR2));
        cardTypeToCodes.put(IdVerificationType.ResidenceCard, List.of(ImageCodes.RC1, ImageCodes.RC3, ImageCodes.RC2));
        cardTypeToCodes.put(IdVerificationType.SpecialPermanentResidentCertificate, List.of(ImageCodes.SP1, ImageCodes.SP3, ImageCodes.SP2));
        cardTypeToCodes.put(IdVerificationType.ElectricBill, List.of(ImageCodes.EL1));
        cardTypeToCodes.put(IdVerificationType.GasBill, List.of(ImageCodes.GS1));
        cardTypeToCodes.put(IdVerificationType.ResidentCardWithoutMyNumber, List.of(ImageCodes.RR1));
        cardTypeToCodes.put(IdVerificationType.ResidentCardWithMyNumber, List.of(ImageCodes.RR2));
        cardTypeToCodes.put(IdVerificationType.CashCard, List.of(ImageCodes.AI1));
        cardTypeToCodes.put(IdVerificationType.Payslip, List.of(ImageCodes.PS1));
        cardTypeToCodes.put(IdVerificationType.WaterBill, List.of(ImageCodes.WC1));
        cardTypeToCodes.put(IdVerificationType.PensionBook, List.of(ImageCodes.PB1));
        cardTypeToCodes.put(IdVerificationType.OtherFaceOfPhotoId, List.of(ImageCodes.PI1));
        return cardTypeToCodes;
    }

    public static HashMap<IdVerificationType, String> getImageHashCode() {
        HashMap<IdVerificationType, String> imagesHashCode = new HashMap<>();
        imagesHashCode.put(IdVerificationType.DriverLicenseCard, "273bd63c93b3e5481edb1898a8f84ed0");
        imagesHashCode.put(IdVerificationType.MyNumberCard, "bdb8bb94f62e56618af8cd705c26458b");
        imagesHashCode.put(IdVerificationType.ResidenceCard, "4ef0fc525094eb1bbb31563f16611c4a");
        return imagesHashCode;
    }
}
