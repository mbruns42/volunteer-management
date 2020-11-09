package csv;

import java.text.SimpleDateFormat;

public class CsvMappingConstants {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
    public static final String[] volunteerCsv = {
            "state",
            "salutation",
            "firstName",
            "lastName",
            "birthday",
            "address",
            "postCode",
            "city",
            "phoneNumber",
            "phoneNumberMobile",
            "email",
            "HYIC", /* Hygieneinformation Corona neu seit 20.05.20 */
            "HYB", /* Hygienebelehrung Gesundheitsamt */
            "HYW", /* Hygienewiederholung im Gast-Haus_Januar */
            "MoV",
            "MoN", "DiVo", "DiN", "MiV", "MiN", "DoV", "DoN", "FrV", "FrN", "SaV", "SaN", "SoV", "SoN",
            "workArea",
            "workArea2",
            "workArea3",
            "workType",
            "SPR",
            "notes",
            "qualification",
            "entryDate"
    };
}
