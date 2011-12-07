/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.parser;

import wig.node.*;

public class ParserException extends Exception
{
    Token token;

    public ParserException(Token token, String  message)
    {
        super(message);
        this.token = token;
    }

    public Token getToken()
    {
        return token;
    }
}