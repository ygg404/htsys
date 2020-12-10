package io.renren.common.utils.map;

public class AreaUtil {
    public static double calculationArea(String coordinate){
        double totalArea = 0.0;// 初始化总面积
        double LowX = 0.0;
        double LowY = 0.0;
        double MiddleX = 0.0;
        double MiddleY = 0.0;
        double HighX = 0.0;
        double HighY = 0.0;
        double AM = 0.0;
        double BM = 0.0;
        double CM = 0.0;
        double AL = 0.0;
        double BL = 0.0;
        double CL = 0.0;
        double AH = 0.0;
        double BH = 0.0;
        double CH = 0.0;
        double CoefficientL = 0.0;
        double CoefficientH = 0.0;
        double ALtangent = 0.0;
        double BLtangent = 0.0;
        double CLtangent = 0.0;
        double AHtangent = 0.0;
        double BHtangent = 0.0;
        double CHtangent = 0.0;
        double ANormalLine = 0.0;
        double BNormalLine = 0.0;
        double CNormalLine = 0.0;
        double OrientationValue = 0.0;
        double AngleCos = 0.0;
        double Sum1 = 0.0;
        double Sum2 = 0.0;
        double Count2 = 0.0;
        double Count1 = 0.0;
        double Sum = 0.0;
        double Radius = 6378137.0;// ,WGS84椭球半径
        int Count = 0;

        String[] coordinateList = coordinate.split(";");

        if(coordinateList.length < 3){
            System.out.println("小于3点无法构成面");
            return 0;
        }

        String[][] coordinateDataList = new String[coordinateList.length][];
        for(int i = 0; i < coordinateDataList.length; i++){
            coordinateDataList[i] = new String[2] ;
        }
        for(int i = 0; i < coordinateList.length; i++){
            coordinateDataList[i][0] = coordinateList[i].split(",")[0];
            coordinateDataList[i][1] = coordinateList[i].split(",")[1];
        }
        Count = coordinateList.length;

        for (int i = 0; i < Count; i++) {
            if (i == 0) {
                LowX = Double.parseDouble(coordinateDataList[Count - 1][0]) * Math.PI / 180;
                LowY =  Double.parseDouble(coordinateDataList[Count - 1][1]) * Math.PI / 180;
                MiddleX = Double.parseDouble(coordinateDataList[0][0]) * Math.PI / 180;
                MiddleY =  Double.parseDouble(coordinateDataList[0][1]) * Math.PI / 180;
                HighX =  Double.parseDouble(coordinateDataList[1][0]) * Math.PI / 180;
                HighY =  Double.parseDouble(coordinateDataList[1][1]) * Math.PI / 180;
            } else if (i == Count - 1) {
                LowX =  Double.parseDouble(coordinateDataList[Count - 2][0]) * Math.PI / 180;
                LowY =  Double.parseDouble(coordinateDataList[Count - 2][1]) * Math.PI / 180;
                MiddleX =  Double.parseDouble(coordinateDataList[Count - 1][0]) * Math.PI / 180;
                MiddleY =  Double.parseDouble(coordinateDataList[Count - 1][1]) * Math.PI / 180;
                HighX =  Double.parseDouble(coordinateDataList[0][0]) * Math.PI / 180;
                HighY =  Double.parseDouble(coordinateDataList[0][1]) * Math.PI / 180;
            } else {
                LowX =  Double.parseDouble(coordinateDataList[i - 1][0]) * Math.PI / 180;
                LowY =  Double.parseDouble(coordinateDataList[i - 1][1]) * Math.PI / 180;
                MiddleX =  Double.parseDouble(coordinateDataList[i][0]) * Math.PI / 180;
                MiddleY =  Double.parseDouble(coordinateDataList[i][1]) * Math.PI / 180;
                HighX =  Double.parseDouble(coordinateDataList[i + 1][0]) * Math.PI / 180;
                HighY =  Double.parseDouble(coordinateDataList[i + 1][1]) * Math.PI / 180;
            }
            AM = Math.cos(MiddleY) * Math.cos(MiddleX);
            BM = Math.cos(MiddleY) * Math.sin(MiddleX);
            CM = Math.sin(MiddleY);
            AL = Math.cos(LowY) * Math.cos(LowX);
            BL = Math.cos(LowY) * Math.sin(LowX);
            CL = Math.sin(LowY);
            AH = Math.cos(HighY) * Math.cos(HighX);
            BH = Math.cos(HighY) * Math.sin(HighX);
            CH = Math.sin(HighY);
            CoefficientL = (AM * AM + BM * BM + CM * CM) / (AM * AL + BM * BL + CM * CL);
            CoefficientH = (AM * AM + BM * BM + CM * CM) / (AM * AH + BM * BH + CM * CH);
            ALtangent = CoefficientL * AL - AM;
            BLtangent = CoefficientL * BL - BM;
            CLtangent = CoefficientL * CL - CM;
            AHtangent = CoefficientH * AH - AM;
            BHtangent = CoefficientH * BH - BM;
            CHtangent = CoefficientH * CH - CM;
            AngleCos = (AHtangent * ALtangent + BHtangent * BLtangent + CHtangent * CLtangent) / (Math.sqrt(AHtangent * AHtangent + BHtangent * BHtangent + CHtangent * CHtangent) * Math.sqrt(ALtangent * ALtangent + BLtangent * BLtangent + CLtangent * CLtangent));
            AngleCos = Math.acos(AngleCos);
            ANormalLine = BHtangent * CLtangent - CHtangent * BLtangent;
            BNormalLine = 0 - (AHtangent * CLtangent - CHtangent * ALtangent);
            CNormalLine = AHtangent * BLtangent - BHtangent * ALtangent;
            if (AM != 0) {
                OrientationValue = ANormalLine / AM;
            } else if (BM != 0) {
                OrientationValue = BNormalLine / BM;
            } else {
                OrientationValue = CNormalLine / CM;
            }
            if (OrientationValue > 0) {
                Sum1 += AngleCos;
                Count1++;
            } else {
                Sum2 += AngleCos;
                Count2++;
            }
        }
        double tempSum1, tempSum2;
        tempSum1 = Sum1 + (2 * Math.PI * Count2 - Sum2);
        tempSum2 = (2 * Math.PI * Count1 - Sum1) + Sum2;
        if (Sum1 > Sum2) {
            if ((tempSum1 - (Count - 2) * Math.PI) < 1) {
                Sum = tempSum1;
            } else {
                Sum = tempSum2;
            }
        } else {
            if ((tempSum2 - (Count - 2) * Math.PI) < 1) {
                Sum = tempSum2;
            } else {
                Sum = tempSum1;
            }
        }
        totalArea = (Sum - (Count - 2) * Math.PI) * Radius * Radius;
        return Math.abs(totalArea); // 返回总面积
    }

    public static void main(String[] args) {
        double areaValue = calculationArea("116.724968,23.382216;116.725201,23.380441;116.730001,23.379077;116.729136,23.383012;");
        System.out.println("计算后的面积" + areaValue);
    }
}
