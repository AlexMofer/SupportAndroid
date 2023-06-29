package com.am.tool.support.other;

import android.content.Context;

import androidx.annotation.Nullable;

import com.am.tool.support.R;

/**
 * MCC辅助
 * Created by Alex on 2023/6/29.
 */
public class MCCHelper {

    public static final int MCC_UNKNOWN = 0;// Unknown network
    public static final int MCC_TEXT = 1;// Test network
    public static final int MCC_INTERNAL = 999;// Internal use
    public static final int MCC_AB = 289;// Abkhazia
    public static final int MCC_AF = 412;// Afghanistan
    public static final int MCC_AL = 276;// Albania
    public static final int MCC_DZ = 603;// Algeria
    public static final int MCC_AS = 544;// American Samoa (United States of America)
    public static final int MCC_AD = 213;// Andorra
    public static final int MCC_AO = 631;// Angola
    public static final int MCC_AI = 365;// Anguilla (United Kingdom)
    public static final int MCC_AG = 344;// Antigua and Barbuda
    public static final int MCC_AR = 722;// Argentina
    public static final int MCC_AM = 283;// Armenia
    public static final int MCC_AW = 363;// Aruba
    public static final int MCC_AU = 505;// Australia
    public static final int MCC_AT = 232;// Austria
    public static final int MCC_AZ = 400;// Azerbaijan
    public static final int MCC_BS = 364;// Bahamas
    public static final int MCC_BH = 426;// Bahrain
    public static final int MCC_BD = 470;// Bangladesh
    public static final int MCC_BB = 342;// Barbados
    public static final int MCC_BY = 257;// Belarus
    public static final int MCC_BE = 206;// Belgium
    public static final int MCC_BZ = 702;// Belize
    public static final int MCC_BJ = 616;// Benin
    public static final int MCC_BM = 350;// Bermuda
    public static final int MCC_BT = 402;// Bhutan
    public static final int MCC_BO = 736;// Bolivia
    public static final int MCC_BQ = 362;// Bonaire, Sint Eustatius, Saba and Curaçao
    public static final int MCC_BA = 218;// Bosnia and Herzegovina
    public static final int MCC_BW = 652;// Botswana
    public static final int MCC_BR = 724;// Brazil
    public static final int MCC_IO = 995;// British Indian Ocean Territory (United Kingdom)
    public static final int MCC_VG = 348;// British Virgin Islands (United Kingdom)
    public static final int MCC_BN = 528;// Brunei
    public static final int MCC_BG = 284;// Bulgaria
    public static final int MCC_BF = 613;// Burkina Faso
    public static final int MCC_BI = 642;// Burundi
    public static final int MCC_KH = 456;// Cambodia
    public static final int MCC_CM = 624;// Cameroon
    public static final int MCC_CA = 302;// Canada
    public static final int MCC_CV = 625;// Cape Verde
    public static final int MCC_KY = 346;// Cayman Islands (United Kingdom)
    public static final int MCC_CF = 623;// Central African Republic
    public static final int MCC_TD = 622;// Chad
    public static final int MCC_CL = 730;// Chile
    public static final int MCC_CN = 460;// China
    public static final int MCC_CN_2 = 461;// China
    public static final int MCC_CO = 732;// Colombia
    public static final int MCC_KM = 654;// Comoros
    public static final int MCC_CG = 629;// Congo
    public static final int MCC_CK = 548;// Cook Islands (Pacific Ocean)
    public static final int MCC_CR = 712;// Costa Rica
    public static final int MCC_HR = 219;// Croatia
    public static final int MCC_CU = 368;// Cuba
    public static final int MCC_CY = 280;// Cyprus
    public static final int MCC_CZ = 230;// Czech Republic
    public static final int MCC_CD = 630;// Democratic Republic of the Congo
    public static final int MCC_DK = 238;// Denmark (Kingdom of Denmark)
    public static final int MCC_DJ = 638;// Djibouti
    public static final int MCC_DM = 366;// Dominica
    public static final int MCC_DO = 370;// Dominican Republic
    public static final int MCC_TL = 514;// East Timor
    public static final int MCC_EC = 740;// Ecuador
    public static final int MCC_EG = 602;// Egypt
    public static final int MCC_SV = 706;// El Salvador
    public static final int MCC_GQ = 627;// Equatorial Guinea
    public static final int MCC_ER = 657;// Eritrea
    public static final int MCC_EE = 248;// Estonia
    public static final int MCC_ET = 636;// Ethiopia
    public static final int MCC_FK = 750;// Falkland Islands (United Kingdom)
    public static final int MCC_FO = 288;// Faroe Islands (Kingdom of Denmark)
    public static final int MCC_FJ = 542;// Fiji
    public static final int MCC_FI = 244;// Finland
    public static final int MCC_FR = 208;// France
    public static final int MCC_GF = 742;// French Guiana (France)
    public static final int MCC_RE = 647;// French Indian Ocean Territories (France)
    public static final int MCC_PF = 547;// French Polynesia (France)
    public static final int MCC_GA = 628;// Gabon

    private MCCHelper() {
        //no instance
    }

    /**
     * 通过资源获取MCC
     *
     * @param context Context
     * @return MCC
     */
    public static int getMCC(Context context) {
        return context.getResources().getInteger(R.integer.am_support_mcc);
    }

    /**
     * 通过MCC获取国家码
     *
     * @param context Context
     * @return 国家码
     */
    @Nullable
    public static String getISO3166(Context context) {
        switch (getMCC(context)) {
            default:
            case MCC_UNKNOWN:
            case MCC_TEXT:
            case MCC_INTERNAL:
                return null;
            case MCC_AB:
                return "AB";
            case MCC_AF:
                return "AF";
            case MCC_AL:
                return "AL";
            case MCC_DZ:
                return "DZ";
            case MCC_AS:
                return "AS";
            case MCC_AD:
                return "AD";
            case MCC_AO:
                return "AO";
            case MCC_AI:
                return "AI";
            case MCC_AG:
                return "AG";
            case MCC_AR:
                return "AR";
            case MCC_AM:
                return "AM";
            case MCC_AW:
                return "AW";
            case MCC_AU:
                return "AU";
            case MCC_AT:
                return "AT";
            case MCC_AZ:
                return "AZ";
            case MCC_BS:
                return "BS";
            case MCC_BH:
                return "BH";
            case MCC_BD:
                return "BD";
            case MCC_BB:
                return "BB";
            case MCC_BY:
                return "BY";
            case MCC_BE:
                return "BE";
            case MCC_BZ:
                return "BZ";
            case MCC_BJ:
                return "BJ";
            case MCC_BM:
                return "BM";
            case MCC_BT:
                return "BT";
            case MCC_BO:
                return "BO";
            case MCC_BQ:
                return "BQ";
            case MCC_BA:
                return "BA";
            case MCC_BW:
                return "BW";
            case MCC_BR:
                return "BR";
            case MCC_IO:
                return "IO";
            case MCC_VG:
                return "VG";
            case MCC_BN:
                return "BN";
            case MCC_BG:
                return "BG";
            case MCC_BF:
                return "BF";
            case MCC_BI:
                return "BI";
            case MCC_KH:
                return "KH";
            case MCC_CM:
                return "CM";
            case MCC_CA:
                return "CA";
            case MCC_CV:
                return "CV";
            case MCC_KY:
                return "KY";
            case MCC_CF:
                return "CF";
            case MCC_TD:
                return "TD";
            case MCC_CL:
                return "CL";
            case MCC_CN:
            case MCC_CN_2:
                return "CN";
            case MCC_CO:
                return "CO";
            case MCC_KM:
                return "KM";
            case MCC_CG:
                return "CG";
            case MCC_CK:
                return "CK";
            case MCC_CR:
                return "CR";
            case MCC_HR:
                return "HR";
            case MCC_CU:
                return "CU";
            case MCC_CY:
                return "CY";
            case MCC_CZ:
                return "CZ";
            case MCC_CD:
                return "CD";
            case MCC_DK:
                return "DK";
            case MCC_DJ:
                return "DJ";
            case MCC_DM:
                return "DM";
            case MCC_DO:
                return "DO";
            case MCC_TL:
                return "TL";
            case MCC_EC:
                return "EC";
            case MCC_EG:
                return "EG";
            case MCC_SV:
                return "SV";
            case MCC_GQ:
                return "GQ";
            case MCC_ER:
                return "ER";
            case MCC_EE:
                return "EE";
            case MCC_ET:
                return "ET";
            case MCC_FK:
                return "FK";
            case MCC_FO:
                return "FO";
            case MCC_FJ:
                return "FJ";
            case MCC_FI:
                return "FI";
            case MCC_FR:
                return "FR";
            case MCC_GF:
                return "GF";
            case MCC_RE:
                return "RE";
            case MCC_PF:
                return "PF";
            case MCC_GA:
                return "GA";
        }
    }
}
