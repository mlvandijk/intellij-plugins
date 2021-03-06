package org.intellij.plugins.postcss.settings;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.intellij.plugins.postcss.PostCssLanguage;
import org.jetbrains.annotations.NotNull;

public class PostCssLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
  @NotNull
  @Override
  public Language getLanguage() {
    return PostCssLanguage.INSTANCE;
  }

  @Override
  public String getCodeSample(@NotNull SettingsType settingsType) {
    return "h1 {\n" +
           "  color: white;\n" +
           "}\n" +
           "h2 {\n" +
           "  color: white;\n" +
           "  @nest h3 & {\n" +
           "    color: green;\n" +
           "  }\n" +
           "  & h3 {\n" +
           "    color: white;\n" +
           "  }\n" +
           "  @media print {\n" +
           "    @page {\n" +
           "      background-color: #fff;\n" +
           "      color: #000;\n" +
           "    }\n" +
           "    .foo {\n" +
           "      font-family: \"Georgia\", \"Tahoma\", serif;\n" +
           "    }\n" +
           "  }\n" +
           "}\n";
  }

  @Override
  protected void customizeDefaults(@NotNull CommonCodeStyleSettings commonSettings,
                                   @NotNull CommonCodeStyleSettings.IndentOptions indentOptions) {
    commonSettings.LINE_COMMENT_AT_FIRST_COLUMN = false;
    commonSettings.BLOCK_COMMENT_AT_FIRST_COLUMN = false;
    indentOptions.INDENT_SIZE = 2;
  }

  @Override
  public IndentOptionsEditor getIndentOptionsEditor() {
    return new SmartIndentOptionsEditor();
  }

  @NotNull
  @Override
  public CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings baseSettings, @NotNull CodeStyleSettings modelSettings) {
    return new CodeStyleAbstractConfigurable(baseSettings, modelSettings, getConfigurableDisplayName()) {
      @Override
      protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
        return new PostCssCodeStylePanel(getCurrentSettings(), settings);
      }

      @NotNull
      @Override
      public String getHelpTopic() {
        return "reference.settingsdialog.codestyle.postcss";
      }
    };
  }
}