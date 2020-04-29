// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package ru.imult.mult.mobile.m3u8;

import java.util.HashMap;

import net.chilicat.m3u8.Playlist;

// Referenced classes of package ru.imult.mult.mobile.m3u8:
//            Playlist

public class QualityPlaylist
{

    HashMap children;
    Playlist master;

    public QualityPlaylist()
    {
        children = new HashMap();
    }

    public void addChild(Playlist playlist, String s)
    {
        Integer integer = Integer.valueOf(s.replaceAll("[^0-9]", ""));
        if (integer.equals(Integer.valueOf(576)))
        {
            children.put(ru.imult.mult.mobile.Settings.Quality.Q720P, playlist);
            children.put(ru.imult.mult.mobile.Settings.Quality.Q1080P, playlist);
            return;
        } else
        {
            children.put(ru.imult.mult.mobile.Settings.Quality.byValue(integer.intValue()), playlist);
            return;
        }
    }

    public Playlist getByQuality(ru.imult.mult.mobile.Settings.Quality quality)
    {
        if (children.containsKey(quality) && !quality.equals(ru.imult.mult.mobile.Settings.Quality.AUTO))
            return (Playlist)children.get(quality);
        else
            return master;
    }

    public Playlist getMaster()
    {
        return master;
    }

    public void setMaster(Playlist playlist)
    {
        master = playlist;
    }
}

