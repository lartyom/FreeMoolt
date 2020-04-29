// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package ru.imult.mult.mobile.m3u8;

import java.net.URI;

// Referenced classes of package ru.imult.mult.mobile.m3u8:
//            Element, EncryptionInfo, PlaylistInfo

final class ElementImpl
    implements Element
{
    static final class EncryptionInfoImpl
        implements EncryptionInfo
    {

        private final String method;
        private final URI uri;

        public String getMethod()
        {
            return method;
        }

        public URI getURI()
        {
            return uri;
        }

        public String toString()
        {
            return (new StringBuilder()).append("EncryptionInfoImpl{uri=").append(uri).append(", method='").append(method).append('\'').append('}').toString();
        }

        public EncryptionInfoImpl(URI uri1, String s)
        {
            uri = uri1;
            method = s;
        }
    }

    static final class PlaylistInfoImpl
        implements PlaylistInfo
    {

        private final int bandWidth;
        private final String codec;
        private final String name;
        private final int programId;

        public int getBandWitdh()
        {
            return bandWidth;
        }

        public String getCodecs()
        {
            return codec;
        }

        public String getName()
        {
            return name;
        }

        public int getProgramId()
        {
            return programId;
        }

        public String toString()
        {
            return (new StringBuilder()).append("PlaylistInfoImpl{programId=").append(programId).append(", bandWidth=").append(bandWidth).append(", codec='").append(codec).append('\'').append(", name='").append(name).append('\'').append('}').toString();
        }

        public PlaylistInfoImpl(int i, int j, String s, String s1)
        {
            programId = i;
            bandWidth = j;
            codec = s;
            name = s1;
        }
    }


    private final int duration;
    private final EncryptionInfo encryptionInfo;
    private final PlaylistInfo playlistInfo;
    private final long programDate;
    private final String title;
    private final URI uri;

    public ElementImpl(PlaylistInfo playlistinfo, EncryptionInfo encryptioninfo, int i, URI uri1, String s, long l)
    {
        if (uri1 == null)
            throw new NullPointerException("uri");
        if (i < -1)
            throw new IllegalArgumentException();
        if (playlistinfo != null && encryptioninfo != null)
        {
            throw new IllegalArgumentException("Element cannot be a encrypted playlist.");
        } else
        {
            playlistInfo = playlistinfo;
            encryptionInfo = encryptioninfo;
            duration = i;
            uri = uri1;
            title = s;
            programDate = l;
            return;
        }
    }

    public int getDuration()
    {
        return duration;
    }

    public EncryptionInfo getEncryptionInfo()
    {
        return encryptionInfo;
    }

    public PlaylistInfo getPlayListInfo()
    {
        return playlistInfo;
    }

    public long getProgramDate()
    {
        return programDate;
    }

    public String getTitle()
    {
        return title;
    }

    public URI getURI()
    {
        return uri;
    }

    public boolean isEncrypted()
    {
        return encryptionInfo != null;
    }

    public boolean isMedia()
    {
        return playlistInfo == null;
    }

    public boolean isPlayList()
    {
        return playlistInfo != null;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ElementImpl{playlistInfo=").append(playlistInfo).append(", encryptionInfo=").append(encryptionInfo).append(", duration=").append(duration).append(", uri=").append(uri).append(", title='").append(title).append('\'').append('}').toString();
    }
}
