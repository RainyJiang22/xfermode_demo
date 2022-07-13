package com.base.xfermode_demo

import android.graphics.PorterDuff
import android.os.Bundle
import com.base.xfermode_demo.base.BaseActivity
import com.base.xfermode_demo.databinding.ActivityDuffModeViewBinding

/**
 * @author jiangshiyu
 * @date 2022/7/13
 */
class DuffModeActivity : BaseActivity<ActivityDuffModeViewBinding>() {
    override fun init(savedInstanceState: Bundle?) {


        //Clear模式
        //看到蓝色(DST)与红色(SRC)相交的区域，蓝色部分也一并被清除掉，
        // 也就是交集区域透明度和色值均为0，
        // 因此此时的SRC就类似是一块“橡皮擦”的效果
        //   binding?.duffView?.setPorterMode(PorterDuff.Mode.CLEAR)

        //DARKEN模式，比较深色的会覆盖浅色的
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.DARKEN)

        //DST模式，顾名思义就是只剩下DST目标图,最终的效果只剩下DST图了，也就是交集区域只显示DST的透明度和色值
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.DST)

        //DST_ATOP模式,在交集的区域显示DST图的色值，不相交的区域只显示SRC的部分
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.DST_ATOP)

        //DST_IN模式，只在交集的区域有颜色和透明值的显示，且显示的是DST图的部分，这是因为在两者相交的区域，Sa不为0，所以Sa*Da显示DST的透明度，Sa*Dc显示DST的色值，在相交之外的区域，要么是Sa为0，要么是Da为0，
        // 所以这些区域计算出来的结果都是[0,0]，绘制区域收到原来SRC源图像的透明度影响，如果相交区域Sa是透明的则绘制出来的图像也是透明的
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.DST_IN)


        //DST_OUT模式，在两者不相交的区域绘制目标图，如果源图像的透明度为0的时候才绘制，不为0不绘制
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.DST_OUT)

        //DST_OVER模式，相当于目标图盖住了源图像
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.DST_OVER)

        //Lighten模式,浅色覆盖深色，会呈现出变亮的效果
        // binding?.duffView?.setPorterMode(PorterDuff.Mode.LIGHTEN)

        //MULTIPLY模式只在相交区域，其他区域均由于Sa=0或者Da=0导致透明度为0，色值为SRC和DST的色值相乘后的结果
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.MULTIPLY)

        //OVERLAY模式 相当于叠加，但又不完全覆盖
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.OVERLAY)

        //SCREEN模式相当于滤色，取了两者较白的部分混合而成的效果
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.SCREEN)

        //XOR模式 在源图像和目标图像相交的地方之外绘制它们，在相交的地方受到对应alpha和色值影响，如果完全不透明则相交处完全不绘制
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.XOR)

        //SRC模式，只绘制源图像
//        binding?.duffView?.setPorterMode(PorterDuff.Mode.SRC)

        //SRC_IN模式，只在源图像和目标图像相交的地方绘制【源图像】
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.SRC_IN)

        //SRC_OUT模式，只在源图像和目标图像不相交的地方绘制【源图像】，相交的地方根据目标图像的对应地方的alpha进行过滤，
        // 目标图像完全不透明则完全过滤，完全透明则不过滤
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.SRC_OUT)

        //SRC_ATOP模式，在源图像和目标图像相交的地方绘制【源图像】，在不相交的地方绘制则完全过滤
        //binding?.duffView?.setPorterMode(PorterDuff.Mode.SRC_ATOP)

        //SRC_OVER模式，将源图像放到目标图像上方
        binding?.duffView?.setPorterMode(PorterDuff.Mode.SRC_OVER)


    }
}