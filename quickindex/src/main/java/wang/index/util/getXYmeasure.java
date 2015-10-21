//package wang.index.util;
//
//import android.view.View;
//import android.view.ViewTreeObserver;
//
///**
// * 创建日期: 15/10/8 下午4:34
// * 作者:wanghao
// * 描述:
// */
//public class getXYmeasure {
//    private static float height;
//    private static float width;
//    public static float getWidth( final View view) {
//
//        view.getViewTreeObserver().addOnGlobalLayoutListener(
//                new ViewTreeObserver.OnGlobalLayoutListener() {
//
//                    @Override
//                    public void onGlobalLayout() {
//                        // 当布局填充结束之后, 此方法会被调用
//                        width=view.getWidth();
//
//                        if(android.os.Build.VERSION.SDK_INT>=16){
//                            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                        }else if(android.os.Build.VERSION.SDK_INT<=16){
//                            view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                        }
//                    }
//                });
//
//
//        return width;
//    }
//
//    public static float getHeight( final View view) {
//
//        view.getViewTreeObserver().addOnGlobalLayoutListener(
//                new ViewTreeObserver.OnGlobalLayoutListener() {
//
//                    @Override
//                    public void onGlobalLayout() {
//                        // 当布局填充结束之后, 此方法会被调用
//                        height=view.getHeight();
//                        if(android.os.Build.VERSION.SDK_INT>=16){
//                            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                        }else if(android.os.Build.VERSION.SDK_INT<=16){
//                            view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                        }
//                    }
//                });
//        return height;
//    }
//
//}
