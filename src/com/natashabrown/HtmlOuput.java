package com.natashabrown;

import com.natashabrown.entries.Entry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Saves entries to HTML file
 */
public class HtmlOuput
{
    protected static String htmlTemplatePath = "htmlTemplate.html";

    public static void save(List<Entry> entries)
    {
        String template = GetHTMLTemplate();

        String newHtml = template.replace("$OUTPUT$", buildList(entries));

        saveHtmlFile(newHtml);
    }

    private static void saveHtmlFile(String newHtml)
    {
        try ( PrintWriter out = new PrintWriter("htmlOutput.html") )
        {
            out.println(newHtml);
        } catch ( FileNotFoundException e )
        {
            e.printStackTrace();
        }
    }

    private static String buildList(List<Entry> entries)
    {
        String output = "<ul>";

        for ( int i = 0; i < entries.size(); i++ )
        {
            output += "<li>" + entries.get(i).getEntry() + "</li>";
        }

        output += "</ul>";

        return output;
    }

    public static String GetHTMLTemplate()
    {
        byte[] encoded = new byte[0];

        try
        {
            encoded = Files.readAllBytes(Paths.get(htmlTemplatePath));
        } catch ( IOException e )
        {
            e.printStackTrace();
        }

        return new String(encoded, Charset.defaultCharset());
    }
}
