package cg.creamgod45.cgrpgcore.Utils;

public class ByteUnitConverter {

    public static double convert(String sourceUnit, String targetUnit, double value) {
        // 定義各單位的轉換因子
        final long BYTE = 1L;
        final long KILOBYTE = 1024L;
        final long MEGABYTE = KILOBYTE * 1024L;
        final long GIGABYTE = MEGABYTE * 1024L;
        final long TERABYTE = GIGABYTE * 1024L;

        // 將所有單位轉換為字節
        long sourceBytes = convertToBytes(sourceUnit, value);
        // 將字節轉換為目標單位
        double result = convertFromBytes(targetUnit, sourceBytes);

        return result;
    }

    private static long convertToBytes(String unit, double value) {
        switch (unit.toLowerCase()) {
            case "b":
                return (long) value;
            case "kb":
                return (long) (value * 1024);
            case "mb":
                return (long) (value * 1024 * 1024);
            case "gb":
                return (long) (value * 1024 * 1024 * 1024);
            case "tb":
                return (long) (value * 1024 * 1024 * 1024 * 1024);
            default:
                throw new IllegalArgumentException("未知的單位: " + unit);
        }
    }

    private static double convertFromBytes(String unit, long bytes) {
        switch (unit.toLowerCase()) {
            case "b":
                return bytes;
            case "kb":
                return bytes / 1024.0;
            case "mb":
                return bytes / (1024.0 * 1024);
            case "gb":
                return bytes / (1024.0 * 1024 * 1024);
            case "tb":
                return bytes / (1024.0 * 1024 * 1024 * 1024);
            default:
                throw new IllegalArgumentException("未知的單位: " + unit);
        }
    }
}
