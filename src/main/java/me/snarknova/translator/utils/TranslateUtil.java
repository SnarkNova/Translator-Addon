package me.snarknova.translator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4;

public class TranslateUtil {
    public static String translate(String text, String to) throws MalformedURLException {
        StringBuilder response = new StringBuilder();

        URL url = new URL(String.format("https://translate.google.com/m?hl=en&sl=auto&tl=%s&ie=UTF-8&prev=_m&q=%s", to, URLEncoder.encode(text.trim(), StandardCharsets.UTF_8)));
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null)
                    response.append(line + "\n");
            }
        } catch (IOException ignored) {
        }

        Matcher matcher = Pattern.compile("class=\"result-container\">([^<]*)<\\/div>", Pattern.MULTILINE).matcher(response);
        matcher.find();
        String match = matcher.group(1);
        if (match == null || match.isEmpty())
            return "translation failed";
        return unescapeHtml4(match);
    }
}
