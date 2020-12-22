package io.renren.common.utils.map;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AreaUtil {
    public static double earthRadiusMeters = 6371000.0;
    public static double metersPerDegree = 2.0 * Math.PI * earthRadiusMeters / 360.0;
    public static double radiansPerDegree = Math.PI / 180.0;
    public static double degreesPerRadian = 180.0 / Math.PI;

    public static double caculateArea(String areaValue){
        double areaMters2 = 0.0;
        List<double[]> points = new ArrayList<double[]>();
        //String areaValue = "116.747423,23.382939;116.747279,23.384402;116.746854,23.385805;116.746162,23.387093;116.745230,23.388216;116.744095,23.389131;116.742800,23.389802;116.741395,23.390204;116.739933,23.390321;116.738471,23.390149;116.737064,23.389694;116.735768,23.388975;116.734632,23.388018;116.733699,23.386861;116.733005,23.385549;116.732578,23.384131;116.732433,23.382663;116.732577,23.381199;116.733003,23.379798;116.733695,23.378512;116.734627,23.377392;116.735763,23.376479;116.737059,23.375810;116.738464,23.375409;116.739926,23.375293;116.741388,23.375464;116.742794,23.375918;116.744090,23.376635;116.745226,23.377589;116.746158,23.378744;116.746851,23.380054;116.747278,23.381471;116.747423,23.382939;";
        String[] s1 = areaValue.split(";");
        for(String ss : s1){
            String[] temp = ss.split(",");
            double [] point = {Double.parseDouble(temp[0]),Double.parseDouble(temp[1])};
            points.add(point);
        }
        AreaUtil areaUtil = new AreaUtil();
        if(points.size() > 2){
            areaMters2 = PlanarPolygonAreaMeters2(points);
            areaMters2 = SphericalPolygonAreaMeters2(points);
            // BigDecimal bg = new BigDecimal(areaMters2+"");
            // System.out.println("面积为: " + bg  + "(平方米)");
        }
        return  Math.abs(areaMters2);
    }

    //计算球面多边形面积
    public static double SphericalPolygonAreaMeters2(List<double[]> points){
        double totalAngle = 0.0;
        for(int i = 0; i < points.size(); ++ i){
            int j = ( i + 1) % points.size();
            int k = (i + 2) % points.size();
            totalAngle += Angle(points.get(i),points.get(j),points.get(k));
        }
        double planarTotalAngle = (points.size() - 2) * 180.0;
        double sphericalExcess = totalAngle - planarTotalAngle;
        if(sphericalExcess > 420.0){
            totalAngle = points.size() * 360.0 - totalAngle;
            sphericalExcess = totalAngle - planarTotalAngle;
        }else if(sphericalExcess > 300.0 && sphericalExcess < 420.0){
            sphericalExcess = Math.abs(360.0 - sphericalExcess);
        }
        return sphericalExcess * radiansPerDegree * earthRadiusMeters * earthRadiusMeters;
    }

    //角度
    public static double Angle(double[] p1,double[] p2, double[] p3){
        double bearing21 = Bearing(p2,p1);
        double bearing23 = Bearing(p2,p3);
        double angle = bearing21 - bearing23;
        if(angle < 0.0){
            angle += 360.0;
        }
        return angle;
    }

    //方向
    public static double Bearing(double[] from ,double[] to){
        double lat1 = from[1] * radiansPerDegree;
        double lon1 = from[0] * radiansPerDegree;
        double lat2 = to[1] * radiansPerDegree;
        double lon2 = to[0] * radiansPerDegree;
        double angle = - Math.atan2(Math.sin(lon1 - lon2) * Math.cos(lat2),Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
        if(angle < 0.0)
            angle += Math.PI * 2.0;
        angle = angle * degreesPerRadian;
        return angle;
    }

    //平面多边形面积
    public static double PlanarPolygonAreaMeters2(List<double[]> points){
        double a = 0.0;
        for(int i = 0; i < points.size(); ++i){
            int j = (i + 1) % points.size();
            double xi = points.get(i)[0] * metersPerDegree * Math.cos(points.get(i)[1] * radiansPerDegree);
            double yi = points.get(i)[1] * metersPerDegree;
            double xj = points.get(j)[0] * metersPerDegree * Math.cos(points.get(j)[1] * radiansPerDegree);
            double yj = points.get(j)[1] * metersPerDegree;
            a += xi * yj - xj * yi;
        }
//        double yy = Math.abs(a / 2.0);
//        System.out.println("yy的值: " + yy);
        return Math.abs( a / 2.0);
    }

    public static double polarTriangleArea(double tan1, double lng1, double tan2, double lng2) {
        double deltaLng = lng1 - lng2;
        double t = tan1 * tan2;
        return 2 * Math.atan2(t * Math.sin(deltaLng), 1 + t * Math.cos(deltaLng));
    }

    public static void main(String[] args) {
        String areaValue = "116.747423,23.382939;116.747279,23.384402;116.746854,23.385805;116.746162,23.387093;116.745230,23.388216;116.744095,23.389131;116.742800,23.389802;116.741395,23.390204;116.739933,23.390321;116.738471,23.390149;116.737064,23.389694;116.735768,23.388975;116.734632,23.388018;116.733699,23.386861;116.733005,23.385549;116.732578,23.384131;116.732433,23.382663;116.732577,23.381199;116.733003,23.379798;116.733695,23.378512;116.734627,23.377392;116.735763,23.376479;116.737059,23.375810;116.738464,23.375409;116.739926,23.375293;116.741388,23.375464;116.742794,23.375918;116.744090,23.376635;116.745226,23.377589;116.746158,23.378744;116.746851,23.380054;116.747278,23.381471;116.747423,23.382939;";
        AreaUtil areaUtil = new AreaUtil();
        areaUtil.caculateArea(areaValue);

    }
}
