package ohrm.malgra.util;

import net.minecraft.util.text.TextComponentTranslation;

/**
 * Created by Toby on 28/07/2016.
 */
public class Utils {

    public static String translateToLocal(String text) {
        return new TextComponentTranslation(text).getFormattedText();
    }

    public static String translateToLocalFormatted(String text, Object... format) {
        return new TextComponentTranslation(text, format).getFormattedText();
    }

}
