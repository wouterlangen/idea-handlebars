package com.dmarcotte.handlebars.file;

import com.dmarcotte.handlebars.HbBundle;
import com.dmarcotte.handlebars.HbLanguage;
import com.dmarcotte.handlebars.HbTemplateHighlighter;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.encoding.EncodingManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import java.nio.charset.Charset;

public class HbFileType extends LanguageFileType {
    public static final Icon FILE_ICON = IconLoader.getIcon("/icons/handlebars_icon.png");
    public static final LanguageFileType INSTANCE = new HbFileType();
    @NonNls
    public static final String DEFAULT_EXTENSION = "handlebars;hbs;mustache";

    private HbFileType() {
        super(HbLanguage.INSTANCE);
    }

    @NotNull
    public String getName() {
        return "Handlebars";
    }

    @NotNull
    public String getDescription() {
        return HbBundle.message("hb.files.file.type.description");
    }

    @NotNull
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    public Icon getIcon() {
        return FILE_ICON;
    }

    public String getCharset(@NotNull VirtualFile file, final byte[] content) {
        Charset charset = EncodingManager.getInstance().getDefaultCharsetForPropertiesFiles(file);
        String defaultCharsetName = charset == null ? CharsetToolkit.getDefaultSystemCharset().name() : charset.name();
        return defaultCharsetName;
    }

    @Override
    public EditorHighlighter getEditorHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @NotNull EditorColorsScheme colors) {
        return new HbTemplateHighlighter(project, virtualFile, colors);
    }
}
