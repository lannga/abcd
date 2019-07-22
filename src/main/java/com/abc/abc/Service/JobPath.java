package com.abc.abc.Service;

/**
 * JobPath
 */
public class JobPath {

    public static String getPathByName(String jobName) {
        switch (jobName) {
            case "Kiến trúc":
                return "kien-truc";
            case "Biên dịch":
                return "bien-dich";
            case "Ngân hàng":
                return "ngan-hang";                
            default:
                return null;
        }
    }

    public static String getPathByLabel(int label) {
        switch (label) {
        case 1:
            return "kien-truc";
        case 3:
            return "bien-dich";
        case 2:
            return "ngan-hang";
        default:
            return null;
        }
    }

    public static String getNameByPath(String jobPath) {
        switch (jobPath) {
        case "kien-truc":
            return "Kiến trúc";
        case "bien-dich":
            return "Biên dịch";
        case "ngan-hang":
            return "Ngân hàng";
        default:
            return null;
        }
    }

    public static int getLabelByPath(String jobPath) {
        switch (jobPath) {
        case "kien-truc":
            return 1;
        case "bien-dich":
            return 3;
        case "ngan-hang":
            return 2;
        default:
            return 0;
        }
    }
}