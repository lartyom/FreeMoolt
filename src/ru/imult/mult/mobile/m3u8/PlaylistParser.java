// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package ru.imult.mult.mobile.m3u8;

import java.io.File;
import java.net.URI;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package ru.imult.mult.mobile.m3u8:
//            ParseException, PlaylistType, ElementBuilder, Playlist, 
//            EncryptionInfo

public class PlaylistParser
{

    private Logger log;
    private PlaylistType type;

    public PlaylistParser(PlaylistType playlisttype)
    {
        log = Logger.getLogger(getClass().getName());
        if (playlisttype == null)
        {
            throw new NullPointerException("type");
        } else
        {
            type = playlisttype;
            return;
        }
    }

    private void checkFirstLine(int i, String s)
        throws ParseException
    {
        if (type == PlaylistType.M3U8 && !s.startsWith("#EXTM3U"))
            throw new ParseException(s, i, (new StringBuilder()).append("Playlist type '").append(PlaylistType.M3U8).append("' must downloadNext with ").append("#EXTM3U").toString());
        else
            return;
    }

    static PlaylistParser create(PlaylistType playlisttype)
    {
        return new PlaylistParser(playlisttype);
    }

    private EncryptionInfo parseEncryption(String s, int i)
        throws ParseException
    {
        Matcher matcher = M3uConstants.Patterns.EXT_X_KEY.matcher(s);
        if (matcher.find() && matcher.matches() && matcher.groupCount() >= 1)
        {
            String s1 = matcher.group(1);
            String s2 = matcher.group(3);
            if (s1.equalsIgnoreCase("none"))
                return null;
            URI uri = null;
            if (s2 != null)
                uri = toURI(s2);
            return new ElementImpl.EncryptionInfoImpl(uri, s1);
        } else
        {
            throw new ParseException(s, i, (new StringBuilder()).append("illegal input: ").append(s).toString());
        }
    }

    private void parseExtInf(String s, int i, ElementBuilder elementbuilder)
        throws ParseException
    {
        Matcher matcher = M3uConstants.Patterns.EXTINF.matcher(s);
        if (!matcher.find() && !matcher.matches() && matcher.groupCount() < 1)
            throw new ParseException(s, i, "EXTINF must specify at least the duration");
        String s1 = matcher.group(1);
        String s2;
        if (matcher.groupCount() > 1)
            s2 = matcher.group(2);
        else
            s2 = "";
        try
        {
            elementbuilder.duration(Integer.valueOf(s1).intValue()).title(s2);
            return;
        }
        catch (NumberFormatException numberformatexception)
        {
            throw new ParseException(s, i, numberformatexception);
        }
    }

    private int parseMediaSequence(String s, int i)
        throws ParseException
    {
        return (int)parseNumberTag(s, i, M3uConstants.Patterns.EXT_X_MEDIA_SEQUENCE, "#EXT-X-MEDIA-SEQUENCE");
    }

    private long parseNumberTag(String s, int i, Pattern pattern, String s1)
        throws ParseException
    {
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find() && !matcher.matches() && matcher.groupCount() < 1)
            throw new ParseException(s, i, (new StringBuilder()).append(s1).append(" must specify duration").toString());
        long l;
        try
        {
            l = Long.valueOf(matcher.group(1)).longValue();
        }
        catch (NumberFormatException numberformatexception)
        {
            throw new ParseException(s, i, numberformatexception);
        }
        return l;
    }

    private boolean parsePlayListInfo(ElementBuilder elementbuilder, String s)
    {
        int i;
        int j;
        String s1;
        String s2;
        String s3;
        i = -1;
        j = -1;
        s1 = "";
        s2 = "";
        s3 = s.substring(s.indexOf(":"));
_L6:
        String s5;
        String s6;
        if (s3.length() <= 0)
            break; /* Loop/switch isn't completed */
        String s4 = s3.substring(1);
        s5 = s4.substring(0, s4.indexOf('='));
        s6 = s4.substring(1 + s4.indexOf('='));
        if (s6.charAt(0) != '"') goto _L2; else goto _L1
_L1:
        String s7;
        String s8 = s6.substring(1);
        int l = s8.indexOf('"');
        s7 = s8.substring(0, l);
        s3 = s8.substring(l + 1);
_L4:
        if (s5.contentEquals("PROGRAM-ID"))
        {
            i = Integer.parseInt(s7);
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_187;
_L2:
        int k;
        try
        {
            k = s6.indexOf(',');
        }
        catch (NumberFormatException numberformatexception)
        {
            return false;
        }
        catch (IndexOutOfBoundsException indexoutofboundsexception)
        {
            return false;
        }
        if (k == -1)
            break MISSING_BLOCK_LABEL_177;
        s7 = s6.substring(0, k);
        s3 = s6.substring(k);
        if (true) goto _L4; else goto _L3
_L3:
        k = s6.length();
        break MISSING_BLOCK_LABEL_155;
        if (s5.contentEquals("CODECS"))
        {
            s2 = s7;
            continue; /* Loop/switch isn't completed */
        }
        if (s5.contentEquals("BANDWIDTH"))
        {
            j = Integer.parseInt(s7);
            continue; /* Loop/switch isn't completed */
        }
        if (s5.contentEquals("NAME"))
        {
            s1 = s7;
            continue; /* Loop/switch isn't completed */
        }
        log.fine((new StringBuilder()).append("Unhandled STREAM-INF attribute ").append(s5).append(" ").append(s7).toString());
        if (true) goto _L6; else goto _L5
_L5:
        elementbuilder.playList(i, j, s2, s1);
        return true;
    }

    private long parseProgramDateTime(String s, int i)
        throws ParseException
    {
        return M3uConstants.Patterns.toDate(s, i);
    }

    private int parseTargetDuration(String s, int i)
        throws ParseException
    {
        return (int)parseNumberTag(s, i, M3uConstants.Patterns.EXT_X_TARGET_DURATION, "#EXT-X-TARGETDURATION");
    }

    private URI toURI(String s)
    {
        URI uri;
        try
        {
            uri = URI.create(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return (new File(s)).toURI();
        }
        return uri;
    }

    public Playlist parse(Readable readable)
        throws ParseException
    {
        Scanner scanner = new Scanner(readable);
        boolean flag = true;
        int i = 0;
        ArrayList arraylist = new ArrayList(10);
        ElementBuilder elementbuilder = new ElementBuilder();
        boolean flag1 = false;
        int j = -1;
        int k = -1;
        EncryptionInfo encryptioninfo = null;
        for (; scanner.hasNextLine(); i++)
        {
            String s = scanner.nextLine().trim();
            if (s.length() <= 0)
                continue;
            if (s.startsWith("#EXT"))
            {
                if (flag)
                {
                    checkFirstLine(i, s);
                    flag = false;
                    continue;
                }
                if (s.startsWith("#EXTINF"))
                {
                    parseExtInf(s, i, elementbuilder);
                    continue;
                }
                if (s.startsWith("#EXT-X-ENDLIST"))
                {
                    flag1 = true;
                    continue;
                }
                if (s.startsWith("#EXT-X-TARGETDURATION"))
                {
                    if (j != -1)
                        throw new ParseException(s, i, "#EXT-X-TARGETDURATION duplicated");
                    j = parseTargetDuration(s, i);
                    continue;
                }
                if (s.startsWith("#EXT-X-MEDIA-SEQUENCE"))
                {
                    if (k != -1)
                        throw new ParseException(s, i, "#EXT-X-MEDIA-SEQUENCE duplicated");
                    k = parseMediaSequence(s, i);
                    continue;
                }
                if (s.startsWith("#EXT-X-PROGRAM-DATE-TIME"))
                {
                    elementbuilder.programDate(parseProgramDateTime(s, i));
                    continue;
                }
                if (s.startsWith("#EXT-X-STREAM-INF"))
                {
                    if (!parsePlayListInfo(elementbuilder, s))
                        throw new ParseException(s, i, "Failed to parse EXT-X-STREAM-INF element");
                    continue;
                }
                if (s.startsWith("#EXT-X-KEY"))
                    encryptioninfo = parseEncryption(s, i);
                else
                    log.log(Level.FINE, (new StringBuilder()).append("Unknown: '").append(s).append("'").toString());
                continue;
            }
            if (s.startsWith("#"))
            {
                if (log.isLoggable(Level.FINEST))
                    log.log(Level.FINEST, (new StringBuilder()).append("----- Comment: ").append(s).toString());
                continue;
            }
            if (flag)
                checkFirstLine(i, s);
            elementbuilder.encrypted(encryptioninfo);
            elementbuilder.uri(toURI(s));
            arraylist.add(elementbuilder.create());
            elementbuilder.reset();
        }

        return new Playlist(Collections.unmodifiableList(arraylist), flag1, j, k);
    }
}
