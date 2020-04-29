// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package ru.imult.mult.mobile.m3u8;


public class PlaylistType
{

    private static final PlaylistType $VALUES[];
    public static final PlaylistType M3U;
    public static final PlaylistType M3U8;
    final String contentType;
    final String encoding;
    final String extension;

    private PlaylistType(String s, int i, String s1, String s2, String s3)
    {
        super();
        encoding = s1;
        contentType = s2;
        extension = s3;
    }

   

    public static PlaylistType[] values()
    {
        return (PlaylistType[])$VALUES.clone();
    }

    public String getContentType()
    {
        return contentType;
    }

    public String getEncoding()
    {
        return encoding;
    }

    public String getExtension()
    {
        return extension;
    }

    static 
    {
        M3U8 = new PlaylistType("M3U8", 0, "UTF-8", "application/vnd.apple.mpegurl", "m3u8");
        M3U = new PlaylistType("M3U", 1, "US-ASCII", "audio/mpegurl", "m3u");
        PlaylistType aplaylisttype[] = new PlaylistType[2];
        aplaylisttype[0] = M3U8;
        aplaylisttype[1] = M3U;
        $VALUES = aplaylisttype;
    }
}
