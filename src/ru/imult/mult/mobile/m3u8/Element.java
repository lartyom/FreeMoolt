// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package ru.imult.mult.mobile.m3u8;

import java.net.URI;

// Referenced classes of package ru.imult.mult.mobile.m3u8:
//            EncryptionInfo, PlaylistInfo

public interface Element
{

    public abstract int getDuration();

    public abstract EncryptionInfo getEncryptionInfo();

    public abstract PlaylistInfo getPlayListInfo();

    public abstract long getProgramDate();

    public abstract String getTitle();

    public abstract URI getURI();

    public abstract boolean isEncrypted();

    public abstract boolean isMedia();

    public abstract boolean isPlayList();
}
