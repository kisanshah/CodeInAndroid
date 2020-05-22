package com.example.codeinandroid;

import com.github.ahmadaghazadeh.editor.processor.language.Language;
import com.github.ahmadaghazadeh.editor.processor.utils.text.ArrayUtils;

import java.util.regex.Pattern;

class JavaLang extends Language {

    private static final String[] ALL_KEYWORDS;
    private static final String[] BLOCK_KEYWORDS;
    private static final String[] ENTITY_KEYWORDS;
    private static final String[] GLOBAL_KEYWORDS;
    private static final String[] HOOKS_KEYWORDS;
    private static final String[] ITEM_KEYWORDS;
    private static final String[] JS_KEYWORDS;
    private static final char[] LANGUAGE_BRACKETS;
    private static final String[] LEVEL_KEYWORDS;
    private static final String[] MODPE_KEYWORDS;
    private static final String[] PLAYER_KEYWORDS;
    private static final String[] SERVER_KEYWORDS;
    private static final Pattern SYNTAX_BRACKETS;
    private static final Pattern SYNTAX_COMMENTS;
    private static final Pattern SYNTAX_KEYWORDS;
    private static final Pattern SYNTAX_KEYWORDS_ALL;
    private static final Pattern SYNTAX_METHODS;
    private static final Pattern SYNTAX_NUMBERS;
    private static final Pattern SYNTAX_STRINGS;
    private static final Pattern SYNTAX_SYMBOLS;

    static {
        SYNTAX_NUMBERS = Pattern.compile("(\\b(\\d*[.]?\\d+)\\b)");
        SYNTAX_SYMBOLS = Pattern.compile("([!+\\-*<>=?|:%&])");
        SYNTAX_BRACKETS = Pattern.compile("([(){}\\[\\]])");
        SYNTAX_KEYWORDS = Pattern.compile("(?<=\\b)((associativity)|(break)|(case)|(catch)|(class)|(continue)|(convenience)|(default)|(deinit)|(didSet)|(do)|(else)|(enum)|(extension)|(fallthrough)|(false)|(final)|(for)|(func)|(get)|(guard)|(if)|(in)|(infix)|(init)|(inout)|(internal)|(lazy)|(let)|(mutating)|(nil)|(operator)|(override)|(postfix)|(precedence)|(prefix)|(private)|(public)|(repeat)|(required)|(return)|(self)|(set)|(static)|(struct)|(subscript)|(super)|(switch)|(throws)|(true)|(try)|(var)|(weak)|(where)|(while)|(willSet)|(Array)|(Bool)|(Dictionary)|(Error)|(Int)|(Set)|(String)|(Tuple)|(UnicodeScalar)|(CharacterSet)|(NSString)|(NSString.CompareOptions)|(String.Encoding.ascii))(?=\\b)");
        SYNTAX_METHODS = Pattern.compile("(?<=(function) )(\\w+)", Pattern.CASE_INSENSITIVE);
        SYNTAX_STRINGS = Pattern.compile("\"(.*?)\"|'(.*?)'");
        SYNTAX_COMMENTS = Pattern.compile("/\\*(?:.|[\\n\\r])*?\\*/|//.*|#.*");
        LANGUAGE_BRACKETS = new char[]{'{', '[', '(', '}', ']', ')'};
        SYNTAX_KEYWORDS_ALL = Pattern.compile("(?<=\\b)((abs)|(max)|(min)|(print))(?=\\b)");
        BLOCK_KEYWORDS = new String[]{"abs", "max", "min", "print"};
        ENTITY_KEYWORDS = new String[0];
        ITEM_KEYWORDS = new String[0];
        LEVEL_KEYWORDS = new String[0];
        MODPE_KEYWORDS = new String[0];
        PLAYER_KEYWORDS = new String[0];
        SERVER_KEYWORDS = new String[0];
        HOOKS_KEYWORDS = new String[0];
        JS_KEYWORDS = new String[0];
        GLOBAL_KEYWORDS = new String[0];
        String[][] arrarrstring = new String[][]{BLOCK_KEYWORDS, ENTITY_KEYWORDS, ITEM_KEYWORDS, LEVEL_KEYWORDS, GLOBAL_KEYWORDS, MODPE_KEYWORDS, PLAYER_KEYWORDS, SERVER_KEYWORDS, HOOKS_KEYWORDS, JS_KEYWORDS};
        ALL_KEYWORDS = ArrayUtils.join(String.class, arrarrstring);
    }


    @Override
    public final String[] getAllCompletions() {
        return ALL_KEYWORDS;
    }


    @Override
    public final char[] getLanguageBrackets() {
        return LANGUAGE_BRACKETS;
    }

    @Override
    public final Pattern getSyntaxBrackets() {
        return SYNTAX_BRACKETS;
    }

    @Override
    public final Pattern getSyntaxComments() {
        return SYNTAX_COMMENTS;
    }

    @Override
    public final Pattern getSyntaxKeywords() {
        return SYNTAX_KEYWORDS;
    }

    @Override
    public final Pattern getSyntaxMethods() {
        return SYNTAX_METHODS;
    }

    @Override
    public final Pattern getSyntaxNumbers() {
        return SYNTAX_NUMBERS;
    }

    @Override
    public final Pattern getSyntaxStrings() {
        return SYNTAX_STRINGS;
    }

    @Override
    public final Pattern getSyntaxSymbols() {
        return SYNTAX_SYMBOLS;
    }
}
