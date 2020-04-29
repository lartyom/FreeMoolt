// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package ru.imult.mult.mobile.m3u8;

import java.net.URI;

// Referenced classes of package ru.imult.mult.mobile.m3u8:
//            ElementImpl, EncryptionInfo, PlaylistInfo, Element

class ElementBuilder
{

    private int duration;
    private EncryptionInfo encryptionInfo;
    private PlaylistInfo playlistInfo;
    private long programDate;
    private String title;
    private URI uri;

    public ElementBuilder()
    {
        programDate = -1L;
    }

    public Element create()
    {
        return new ElementImpl(playlistInfo, encryptionInfo, duration, uri, title, programDate);
    }

    public ElementBuilder duration(int i)
    {
        duration = i;
        return this;
    }

    public ElementBuilder encrypted(URI uri1, String s)
    {
        encryptionInfo = new ElementImpl.EncryptionInfoImpl(uri1, s);
        return this;
    }

    public ElementBuilder encrypted(EncryptionInfo encryptioninfo)
    {
        encryptionInfo = encryptioninfo;
        return this;
    }

    public int getDuration()
    {
        return duration;
    }

    public String getTitle()
    {
        return title;
    }

    public URI getUri()
    {
        return uri;
    }

    public ElementBuilder playList(int i, int j, String s, String s1)
    {
        playlistInfo = new ElementImpl.PlaylistInfoImpl(i, j, s, s1);
        return this;
    }

    public long programDate()
    {
        return programDate;
    }

    public ElementBuilder programDate(long l)
    {
        programDate = l;
        return this;
    }

    public ElementBuilder reset()
    {
        duration = 0;
        uri = null;
        title = null;
        programDate = -1L;
        resetEncryptedInfo();
        resetPlatListInfo();
        return this;
    }

    public ElementBuilder resetEncryptedInfo()
    {
        encryptionInfo = null;
        return this;
    }

    public ElementBuilder resetPlatListInfo()
    {
        playlistInfo = null;
        return this;
    }

    public ElementBuilder title(String s)
    {
        title = s;
        return this;
    }

    public ElementBuilder uri(URI uri1)
    {
        uri = uri1;
        return this;
    }
}
