// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package ru.imult.mult.mobile.m3u8;


public class ParseException extends Exception
{

    private final String line;
    private final int lineNumber;

    public ParseException(String s, int i, String s1)
    {
        super(s1);
        line = s;
        lineNumber = i;
    }

    public ParseException(String s, int i, Throwable throwable)
    {
        super(throwable);
        line = s;
        lineNumber = i;
    }

    public String getLine()
    {
        return line;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public String getMessage()
    {
        return (new StringBuilder()).append("Error at line ").append(getLineNumber()).append(": ").append(getLine()).append("\n").append(super.getMessage()).toString();
    }
}
