package mc233.fun.colorfulcolors;

public class Trigonometry {
    private static final int Size_SC_Ac = 5000;
    private static final int Size_SC_Ar = 5001;
    private static final float[] Sin = new float[5001];
    private static final float[] Cos = new float[5001];
    private static final float Pi = 3.1415927F;
    private static final float Pi_D = 6.2831855F;
    private static final float Pi_SC_D = 0.001256637F;

    public Trigonometry() {
    }

    public static final float sin(float r) {
        float rp = r % 6.2831855F;
        if (rp < 0.0F) {
            rp += 6.2831855F;
        }

        return Sin[(int)(rp / 0.001256637F)];
    }

    public static final float cos(float r) {
        float rp = r % 6.2831855F;
        if (rp < 0.0F) {
            rp += 6.2831855F;
        }

        return Cos[(int)(rp / 0.001256637F)];
    }

    static {
        for(int i = 0; i < 5001; ++i) {
            double d = (double)((float)i * 0.001256637F);
            Sin[i] = (float)Math.sin(d);
            Cos[i] = (float)Math.cos(d);
        }

    }
}
