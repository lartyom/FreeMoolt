// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package ru.imult.mult.mobile;

public class Settings
{
    public static final class Quality 
    {
        private static final Quality $VALUES[];
        public static final Quality AUTO;
        public static final Quality Q1080P;
        public static final Quality Q240P;
        public static final Quality Q360P;
        public static final Quality Q480P;
        public static final Quality Q720P;
        private final Integer value;

        public static Quality byValue(int i)
        {
            Quality aquality[] = values();
            int j = aquality.length;
            for (int k = 0; k < j; k++)
            {
                Quality quality = aquality[k];
                if (i == quality.value.intValue())
                    return quality;
            }

            return AUTO;
        }


        public static Quality[] values()
        {
            return (Quality[])$VALUES.clone();
        }

        public Integer getValue()
        {
            return value;
        }

        static 
        {
            AUTO = new Quality("AUTO", 0, -1);
            Q240P = new Quality("Q240P", 1, 240);
            Q360P = new Quality("Q360P", 2, 360);
            Q480P = new Quality("Q480P", 3, 480);
            Q720P = new Quality("Q720P", 4, 720);
            Q1080P = new Quality("Q1080P", 5, 1080);
            Quality aquality[] = new Quality[6];
            aquality[0] = AUTO;
            aquality[1] = Q240P;
            aquality[2] = Q360P;
            aquality[3] = Q480P;
            aquality[4] = Q720P;
            aquality[5] = Q1080P;
            $VALUES = aquality;
        }

        private Quality(String s, int i, int j)
        {
            super();
            value = Integer.valueOf(j);
        }
    }



   
}
