/*
Decompiled By LOSTED
https://github.com/LOSTEDs
LOSTED#8754
https://www.youtube.com/watch?v=xg2M21todDU&t=55s
"...Minecraft client created by professional developers exclusively for me..." - SuchSpeed
Here is a better way to say this, "...Minecraft client skidded by some random script kittens exclusively for me"
Please SuchSpeed, don't sue me... I just dumped the source...
For Educational Purposes Only...
*/

package org.json;

public class Cookie {
    public static String escape(String string) {
        String s = string.trim();
        int length = s.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c < ' ' || c == '+' || c == '%' || c == '=' || c == ';') {
                sb.append('%');
                sb.append(Character.forDigit((char)(c >>> 4 & 0xF), 16));
                sb.append(Character.forDigit((char)(c & 0xF), 16));
            } else {
                sb.append(c);
            } 
        } 
        return sb.toString();
    }
    
    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject jo = new JSONObject();
        JSONTokener x = new JSONTokener(string);
        jo.put("name", x.nextTo('='));
        x.next('=');
        jo.put("value", x.nextTo(';'));
        x.next();
        while (x.more()) {
            Object value;
            String name = unescape(x.nextTo("=;"));
            if (x.next() != '=') {
                if (name.equals("secure")) {
                    value = Boolean.TRUE;
                } else {
                    throw x.syntaxError("Missing '=' in cookie parameter.");
                } 
            } else {
                value = unescape(x.nextTo(';'));
                x.next();
            } 
            jo.put(name, value);
        } 
        return jo;
    }
    
    public static String toString(JSONObject jo) throws JSONException {
        StringBuilder sb = new StringBuilder();
        sb.append(escape(jo.getString("name")));
        sb.append("=");
        sb.append(escape(jo.getString("value")));
        if (jo.has("expires")) {
            sb.append(";expires=");
            sb.append(jo.getString("expires"));
        } 
        if (jo.has("domain")) {
            sb.append(";domain=");
            sb.append(escape(jo.getString("domain")));
        } 
        if (jo.has("path")) {
            sb.append(";path=");
            sb.append(escape(jo.getString("path")));
        } 
        if (jo.optBoolean("secure"))
            sb.append(";secure"); 
        return sb.toString();
    }
    
    public static String unescape(String string) {
        int length = string.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (c == '+') {
                c = ' ';
            } else if (c == '%' && i + 2 < length) {
                int d = JSONTokener.dehexchar(string.charAt(i + 1));
                int e = JSONTokener.dehexchar(string.charAt(i + 2));
                if (d >= 0 && e >= 0) {
                    c = (char)(d * 16 + e);
                    i += 2;
                } 
            } 
            sb.append(c);
        } 
        return sb.toString();
    }
}
