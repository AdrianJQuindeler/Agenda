package com.example.viagens;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourcesUtil {
    public static Drawable imagemDrawable(Context context, String drawableEmTexto) {
        Resources resources = context.getResources();
        int idDrawable = resources.getIdentifier(drawableEmTexto, "drawable", context.getPackageName());
        Drawable drawableImagem = resources.getDrawable(idDrawable);
        return drawableImagem;
    }
}
