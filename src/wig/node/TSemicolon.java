/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import wig.analysis.*;

public final class TSemicolon extends Token
{
    public TSemicolon()
    {
        super.setText(";");
    }

    public TSemicolon(int line, int pos)
    {
        super.setText(";");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TSemicolon(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTSemicolon(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TSemicolon text.");
    }
}
