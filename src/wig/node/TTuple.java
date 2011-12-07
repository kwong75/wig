/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import wig.analysis.*;

public final class TTuple extends Token
{
    public TTuple()
    {
        super.setText("tuple");
    }

    public TTuple(int line, int pos)
    {
        super.setText("tuple");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TTuple(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTTuple(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TTuple text.");
    }
}