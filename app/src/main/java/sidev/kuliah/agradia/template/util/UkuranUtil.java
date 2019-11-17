package sidev.kuliah.agradia.template.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;


public class UkuranUtil {
    public static DisplayMetrics getDm(Context k){
        return k.getResources().getDisplayMetrics();
    }

    public static int DpKePx(int dp, Context k){
        return DpKePx(dp, getDm(k));
    }
    public static int DpKePx(int dp, DisplayMetrics dm){
        int px= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm);
        return px;
    }

    public static int PxKeDp(int px, Context k){
        return PxKeDp(px, getDm(k));
    }
    public static int PxKeDp(int px, DisplayMetrics dm){
        int dp= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, dm);
        return dp;
    }

    public static int SpKePx(int sp, Context k){
        return SpKePx(sp, getDm(k));
    }
    public static int SpKePx(int sp, DisplayMetrics dm){
        int px= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, dm);
        return px;
    }

    public static int PxKeSp(int px, Context k){
        return PxKeSp(px, getDm(k));
    }
    public static int PxKeSp(int px, DisplayMetrics dm){
        int sp= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, dm);
        return sp;
    }

    public static int[] ukuranView(View v){
        v.measure(View.MeasureSpec.makeMeasureSpec(v.getWidth(), View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(v.getHeight(), View.MeasureSpec.UNSPECIFIED));
        int ukuran[]= new int[2];
        ukuran[0]= v.getMeasuredWidth();
        ukuran[1]= v.getMeasuredHeight();
        return ukuran;
    }
}
