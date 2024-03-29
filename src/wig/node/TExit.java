/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import wig.analysis.*;

public final class TExit extends Token
{
    public TExit()
    {
        super.setText("exit");
    }

    public TExit(int line, int pos)
    {
        super.setText("exit");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TExit(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTExit(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TExit text.");
    }
}
