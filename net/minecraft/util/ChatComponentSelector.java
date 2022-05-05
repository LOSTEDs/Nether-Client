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

package net.minecraft.util;

public class ChatComponentSelector extends ChatComponentStyle {
    private final String selector;
    
    public ChatComponentSelector(String selectorIn) {
        this.selector = selectorIn;
    }
    
    public String getSelector() {
        return this.selector;
    }
    
    public String getUnformattedTextForChat() {
        return this.selector;
    }
    
    public ChatComponentSelector createCopy() {
        ChatComponentSelector chatcomponentselector = new ChatComponentSelector(this.selector);
        chatcomponentselector.setChatStyle(getChatStyle().createShallowCopy());
        for (IChatComponent ichatcomponent : getSiblings())
            chatcomponentselector.appendSibling(ichatcomponent.createCopy()); 
        return chatcomponentselector;
    }
    
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_)
            return true; 
        if (!(p_equals_1_ instanceof ChatComponentSelector))
            return false; 
        ChatComponentSelector chatcomponentselector = (ChatComponentSelector)p_equals_1_;
        return (this.selector.equals(chatcomponentselector.selector) && super.equals(p_equals_1_));
    }
    
    public String toString() {
        return "SelectorComponent{pattern='" + this.selector + '\'' + ", siblings=" + this.siblings + ", style=" + getChatStyle() + '}';
    }
}
