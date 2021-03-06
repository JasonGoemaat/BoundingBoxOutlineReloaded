package com.irtimaled.bbor.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;

public class SearchField extends TextFieldWidget implements IControl {
    private final ControlList controlList;

    SearchField(FontRenderer fontRenderer, int left, int top, int width, int height, ControlList controlList) {
        super(fontRenderer, left, top, width, height, "");

        this.controlList = controlList;
        this.func_212954_a(text -> this.controlList.filter(removeLeadingSpaces(text.toLowerCase())));
        this.setTextFormatter((text, id) -> removeLeadingSpaces(text));
        this.setFocused(true);
    }

    private String removeLeadingSpaces(String text) {
        return text.replaceFirst("^\\s++", "");
    }

    @Override
    public void render(int mouseX, int mouseY) {
        this.render(mouseX, mouseY, 0f);
    }

    @Override
    public boolean isVisible() {
        return super.getVisible();
    }
}
