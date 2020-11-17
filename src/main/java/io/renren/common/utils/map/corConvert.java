package io.renren.common.utils.map;

// 坐标转换
public class corConvert {
    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    /**
     * 中国正常坐标系 GCJ02 协议的坐标，转到百度地图对应的 BD09 协议坐标
     * @param lng 经度
     * @param lat 维度
     */
    public static float[] Convert_GCJ02_To_BD09(double lng,double lat)
    {
        double x = lng, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        lng = z * Math.cos(theta) + 0.0065;
        lat = z * Math.sin(theta) + 0.006;
        return new float[]{(float)lng , (float)lat };
    }

    /**
     * 百度地图对应的 BD09 协议坐标，转到 中国正常坐标系 GCJ02 协议的坐标
     * @param lng 经度
     * @param lat 维度
     * @return
     */
    public static float[] Convert_BD09_To_GCJ02(double lng,double lat)
    {
        double x = lng - 0.0065, y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        lng = z * Math.cos(theta);
        lat = z * Math.sin(theta);
        return new float[]{(float)lng , (float)lat };
    }


}
