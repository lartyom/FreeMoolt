// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package ru.imult.mult.mobile.m3u8;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package ru.imult.mult.mobile.m3u8:
//            ParseException, PlaylistType, PlaylistParser

public final class Playlist
    implements Iterable
{

    String content;
    private final List elements;
    private final boolean endSet;
    private int mediaSequenceNumber;
    private final int targetDuration;
    String uri;

    public Playlist(List list, boolean flag, int i, int j)
    {
        content = "";
        uri = "";
        if (list == null)
        {
            throw new NullPointerException("elements");
        } else
        {
            targetDuration = i;
            elements = list;
            endSet = flag;
            mediaSequenceNumber = j;
            return;
        }
    }

    Playlist(List list, boolean flag, int i, int j, String s)
    {
        content = "";
        uri = "";
        if (list == null)
        {
            throw new NullPointerException("elements");
        } else
        {
            targetDuration = i;
            elements = list;
            endSet = flag;
            mediaSequenceNumber = j;
            content = s;
            return;
        }
    }

    private static Readable makeReadable(ReadableByteChannel readablebytechannel)
    {
        if (readablebytechannel == null)
            throw new NullPointerException("source");
        else
            return Channels.newReader(readablebytechannel, Charset.defaultCharset().name());
    }

    public static Playlist parse(InputStream inputstream)
        throws ParseException
    {
        if (inputstream == null)
            throw new NullPointerException("playlist");
        else
            return parse(((Readable) (new InputStreamReader(inputstream))));
    }

    public static Playlist parse(Readable readable)
        throws ParseException
    {
        if (readable == null)
            throw new NullPointerException("playlist");
        else
            return PlaylistParser.create(PlaylistType.M3U8).parse(readable);
    }

    public static Playlist parse(String s)
        throws ParseException
    {
        if (s == null)
            throw new NullPointerException("playlist");
        else
            return parse(((Readable) (new StringReader(s))));
    }

    public static Playlist parse(ReadableByteChannel readablebytechannel)
        throws ParseException
    {
        if (readablebytechannel == null)
            throw new NullPointerException("playlist");
        else
            return parse(makeReadable(readablebytechannel));
    }

    public String getContent()
    {
        return content;
    }

    public List getElements()
    {
        return elements;
    }

    public int getMediaSequenceNumber()
    {
        return mediaSequenceNumber;
    }

    public int getTargetDuration()
    {
        return targetDuration;
    }

    public String getUri()
    {
        return uri;
    }

    public boolean isEndSet()
    {
        return endSet;
    }

    public Iterator iterator()
    {
        return elements.iterator();
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setUri(String s)
    {
        uri = s;
    }

    public String toString()
    {
        return content;
    }
}
