package ph.devcon.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

/**
 * Created by lope on 10/6/14.
 */
public class Util {
    public static String toTime(int section) {
        switch (section) {
            case 0:
                return "08:00 AM";
            case 1:
                return "09:00 AM";
            case 2:
                return "10:00 AM";
            case 3:
                return "11:00 AM";
            case 4:
                return "12:00 NN";
            case 5:
                return "01:00 PM";
            case 6:
                return "02:00 PM";
            case 7:
                return "03:00 PM";
            case 8:
                return "04:00 PM";
            default:
                return "UNKNOWN";
        }
    }

    public static String toSponsorType(int section) {
        switch (section) {
            case 0:
                return "Co-Presentors";
            case 1:
                return "Gold Sponsors";
            case 2:
                return "Silver Sponsors";
            case 3:
                return "Community Partner";
            default:
                return "UNKNOWN";
        }
    }

    /**
     * https://plus.google.com/+MarioViviani/posts/fhuzYkji9zz
     *
     * @param context
     * @param bitmap
     * @return
     */
    public static Bitmap blurBitmap(Context context, Bitmap bitmap) {
        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context);
        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //Create the in/out Allocations with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
        //Set the radius of the blur
        blurScript.setRadius(25.f);
        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);
        //recycle the original bitmap
        bitmap.recycle();
        //After finishing everything, we destroy the Renderscript.
        rs.destroy();
        return outBitmap;

    }
}
