// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package ru.imult.mult.mobile.m3u8;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package ru.imult.mult.mobile.m3u8:
//            ParseException

final class M3uConstants
{
    static class Patterns
    {

        static final Pattern EXTINF = Pattern.compile((new StringBuilder()).append(tagPattern("#EXTINF")).append("\\s*(-1|[0-9]*)\\s*(?:,(.*))?").toString());
        static final Pattern EXT_X_KEY = Pattern.compile((new StringBuilder()).append(tagPattern("#EXT-X-KEY")).append("METHOD=([0-9A-Za-z\\-]*)(,URI=\"([^\\\\\"]*.*)\")?").toString());
        static final Pattern EXT_X_MEDIA_SEQUENCE = Pattern.compile((new StringBuilder()).append(tagPattern("#EXT-X-MEDIA-SEQUENCE")).append("([0-9]*)").toString());
        static final Pattern EXT_X_PROGRAM_DATE_TIME = Pattern.compile((new StringBuilder()).append(tagPattern("#EXT-X-PROGRAM-DATE-TIME")).append("(.*)").toString());
        static final Pattern EXT_X_TARGET_DURATION = Pattern.compile((new StringBuilder()).append(tagPattern("#EXT-X-TARGETDURATION")).append("([0-9]*)").toString());

        private static String tagPattern(String s)
        {
            return (new StringBuilder()).append("\\s*").append(s).append("\\s*:\\s*").toString();
        }

        static long toDate(String s, int i)
            throws ru.imult.mult.mobile.m3u8.ParseException
        {
            Matcher matcher = EXT_X_PROGRAM_DATE_TIME.matcher(s);
            if (!matcher.find() || !matcher.matches() || matcher.groupCount() < 1)
                throw new ru.imult.mult.mobile.m3u8.ParseException(s, i, " must specify date-time");
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            System.out.println(simpledateformat.format(new Date()));
            String s1 = matcher.group(1);
            long l;
            try
            {
                l = simpledateformat.parse(s1).getTime();
            }
            catch (ParseException parseexception)
            {
                throw new ru.imult.mult.mobile.m3u8.ParseException(s, i, parseexception);
            }
            return l;
        }


        private Patterns()
        {
            throw new AssertionError();
        }
    }


    static final String BANDWIDTH = "BANDWIDTH";
    static final String CODECS = "CODECS";
    static final String COMMENT_PREFIX = "#";
    static final String EXTINF = "#EXTINF";
    static final String EXTM3U = "#EXTM3U";
    static final String EXT_X_ALLOW_CACHE = "#EXT-X-ALLOW-CACHE";
    private static final String EXT_X_DISCONTINUITY = "#EXT-X-DISCONTINUITY";
    static final String EXT_X_ENDLIST = "#EXT-X-ENDLIST";
    static final String EXT_X_KEY = "#EXT-X-KEY";
    static final String EXT_X_MEDIA_SEQUENCE = "#EXT-X-MEDIA-SEQUENCE";
    static final String EXT_X_PROGRAM_DATE_TIME = "#EXT-X-PROGRAM-DATE-TIME";
    static final String EXT_X_STREAM_INF = "#EXT-X-STREAM-INF";
    static final String EXT_X_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    static final String EX_PREFIX = "#EXT";
    static final String NAME = "NAME";
    static final String PROGRAM_ID = "PROGRAM-ID";
    final static String RESOLUTION = "RESOLUTION";

    private M3uConstants()
    {
        throw new AssertionError("Not allowed");
    }
}
